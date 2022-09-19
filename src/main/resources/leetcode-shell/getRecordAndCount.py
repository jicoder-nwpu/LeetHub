from utils import *
from config import *

def getDailyProblemRecord(leetcode_client, leethub_client, last_record_time):
    content = post(leetcode_client, leetcode_graphql_url, headers=leetcode_headers, params=daily_accept_record_params)
    records = content['data']['userProfileQuestions']['questions']

    user = get(leethub_client, leethub_user_info_url, leethub_headers)

    update_date = None
    easy = 0
    medium = 0
    hard = 0

    for record in records:
        if record['lastSubmittedAt'] <= last_record_time:
            break

        if not update_date:
            update_date = formateDate(record['lastSubmittedAt'])
        elif formateDate(record['lastSubmittedAt']) != update_date:
            update_count(leethub_client, user['username'], update_date, easy, medium, hard)
            update_date = formateDate(record['lastSubmittedAt'])
            easy = 0
            medium = 0
            hard = 0
        
        if record['difficulty'] == 'EASY':
            easy += 1
        elif record['difficulty'] == 'MEDIUM':
            medium += 1
        elif record['difficulty'] == 'HARD':
            hard += 1
        
        problem = {}
        problem['title'] = record['translatedTitle']
        problem['questionId'] = get_problem_info(leetcode_client, record['titleSlug'])['questionId']
        problem['type'] = None
        problem['difficulty'] = record['difficulty']
        problem['url'] = leetcode_problem_url + record['titleSlug']
        problem['content'] = get_problem_info(leetcode_client, record['titleSlug'])['translatedContent']
        problem['date'] = None

        problem_id = post(leethub_client, leethub_problem_insert_url, leethub_headers, problem)
        log('insert problem ' + record['translatedTitle'], str(problem_id) if type(problem_id) == int else str(problem_id['status']))
        problem['problem_id'] = problem_id

        ptu = {}
        ptu['problem'] = problem
        ptu['user'] = user
        ptu['alias'] = None
        ptu['submit_time'] = formateTime(record['lastSubmittedAt'])

        res = post(leethub_client, leethub_ptu_insert_url, leethub_headers, ptu)
        log('insert ptu ' + record['translatedTitle'] + ' ' + ptu['user']['username'], str(res) if type(res) == int else str(res['status']))

    if not update_date:
        return
    
    update_count(leethub_client, user['username'], update_date, easy, medium, hard)
    last_record_time = max(last_record_time, records[0]['lastSubmittedAt'])
    log('last_record_time', formateTime(last_record_time))
    writeLastTime(user['username'], 'lastRecordTime', str(last_record_time))


def update_count(leethub_client, username, update_date, easy, medium, hard):
    cur_easy = post(leethub_client, leethub_rank_select_url, leethub_headers, get_count_params(EASYCOUNT, update_date))
    cur_medium = post(leethub_client, leethub_rank_select_url, leethub_headers, get_count_params(MEDIUMCOUNT, update_date))
    cur_hard = post(leethub_client, leethub_rank_select_url, leethub_headers, get_count_params(HARDCOUNT, update_date))
    easy += int(cur_easy)
    medium += int(cur_medium)
    hard += int(cur_hard)
    post(leethub_client, leethub_rank_update_url, leethub_headers, update_count_params(EASYCOUNT, update_date, easy))
    post(leethub_client, leethub_rank_update_url, leethub_headers, update_count_params(MEDIUMCOUNT, update_date, medium))
    post(leethub_client, leethub_rank_update_url, leethub_headers, update_count_params(HARDCOUNT, update_date, hard))
    log('update_count to user ' + username, 'easy:' + str(easy) + ' medium:' + str(medium) + ' hard:' + str(hard))


def get_daily_count(leetcode_client, leethub_client):
    try:
        resp = post(leetcode_client, leetcode_nojgo_url, headers=leetcode_headers, params=daily_problem_count_params)

        count = resp['data']['problemsetStreakCounter']['streakCount']
        skip_days = resp['data']['problemsetStreakCounter']['daysSkipped']

        user = get(leethub_client, leethub_user_info_url, leethub_headers)
        user_id = user['user_id']
        data = {}
        data['user_id'] = user_id
        data['count'] = count
        res = post(leethub_client, leethub_user_update_dailypcount_url, leethub_headers, data)
        log('insert_daily_count to ' + user['username'] + ' : ' + str(count), str(res) if type(res) == int else str(res['status']))
    except BaseException as e:
        log('get_daily_rank', str(e))


def get_points_info(leetcode_client, leethub_client, last_score_time):
    total = get(leetcode_client, leetcode_points_api_total, leetcode_headers)['points']
    details = get(leetcode_client, leetcode_points_api, leetcode_headers)['scores']
    cur = get(leethub_client, leethub_score_cur_url, leethub_headers)
    if cur == total:
        return
    data = {}
    data['score_val'] = total
    description = []
    update_time = None
    for detail in details:
        date = detail['date'].replace('T', ' ')
        date = date.replace('Z', '')
        date = date.split('.')[0]
        cur_time = time.strptime(date, '%Y-%m-%d %H:%M:%S')   #str -> struct_time -> 时间戳 -> timestamp
        cur_time = time.mktime(cur_time)
        if cur_time <= last_score_time:
            continue
        if detail['description'] not in description:
            description.append(detail['description'])
        if not update_time or update_time < cur_time:
            update_time = cur_time
    if not update_time:
        return
    if len(description) > 5:
        description = description[0:5]
    data['description'] = ','.join(description)
    data['update_time'] = formateTime(update_time + TIME_DIFF)
    data['user'] = get(leethub_client, leethub_user_info_url, leethub_headers)
    writeLastTime(data['user']['username'], 'lastScoreTime', update_time)
    data['gap'] = 0 if cur == -1 else total - cur
    res = post(leethub_client, leethub_score_insert_url, leethub_headers, data)
    log('get_points_info to ' + data['user']['username'] + ' : ' + str(total), str(res) if type(res) == int else str(res['status']))

if __name__ == '__main__':
    requests.packages.urllib3.disable_warnings()
    users = getUsers()['user']
    last_times = getLastFile()
    for u in users:
        leetcode_client = login(leetcode_login_url, u['leetcode'])
        leethub_client = login(leethub_login_url, u['leethub'])
        getDailyProblemRecord(leetcode_client, leethub_client, int(last_times[u['leethub']['username']]['lastRecordTime']))
        get_daily_count(leetcode_client, leethub_client)
        get_points_info(leetcode_client, leethub_client, last_times[u['leethub']['username']]['lastScoreTime'])