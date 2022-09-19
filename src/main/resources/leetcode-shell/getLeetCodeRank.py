from config import *
from utils import *


def get_daily_rank(session, client, leetcode_name):
    try:
        user_info = get(client, leethub_user_info_url, leethub_headers)
        data = {}
        data['user'] = user_info
        resp = post(session, leetcode_graphql_url, headers=leetcode_headers, params=get_daily_rank_params(leetcode_name))
        rank = resp['data']['userProfilePublicProfile']['siteRanking']
        data['rank_val'] = rank
        data['type'] = DailyRank
        data['update_time'] = get_date(session)
        latest_rank = get(client, leethub_rank_latest + '/' + str(DailyRank), leethub_headers)
        if latest_rank == -1:
            data['gap'] = 0
        else:
            data['gap'] = latest_rank - rank
        data['description'] = '每日刷题'
        data['contest_rank'] = None
        data['score'] = None
        data['easy_count'] = 0
        data['medium_count'] = 0
        data['hard_count'] = 0
        res = post(client, leethub_rank_insert_url, headers=leethub_headers, params=data)
        status = None
        if type(res) == int:
            status = res
        else:
            status = res['status']
        log('get_daily_rank', str(status))
    except BaseException as e:
        log('get_daily_rank', str(e))


def get_contest_rank(session, client, leetcode_name, lastContestTime):
    dayOfWeek = datetime.datetime.now().weekday() + 1
    if dayOfWeek != 5 and dayOfWeek != 6:
        return
    try:
        user_info = get(client, leethub_user_info_url, leethub_headers)
        resp = post(session, leetcode_nojgo_url, headers=leetcode_headers, params=get_contest_rank_params(leetcode_name))['data']
        if resp['userContestRanking'] is None:
            return
        localRank = resp['userContestRanking']['localRanking']
        userContestRankingHistory = resp['userContestRankingHistory']
        description = []
        count = 0
        for contest in userContestRankingHistory:
            if contest['attended'] and contest['contest']['startTime'] > lastContestTime:
                count += 1
                description.append(contest['contest']['title'])
                lastContestTime = contest['contest']['startTime']
        if count == 0:
            return
        data = {}
        data['user'] = user_info
        writeLastTime(user_info['username'], 'lastContestTime', lastContestTime)
        if len(description) > 5:
            description = description[0:6]
        data['description'] = ','.join(description)
        data['rank_val'] = localRank
        data['type'] = ContestRank
        data['update_time'] = get_date(session)
        data['contest_rank'] = userContestRankingHistory[-1]['ranking']
        data['score'] = userContestRankingHistory[-1]['score']
        data['easy_count'] = None
        data['medium_count'] = None
        data['hard_count'] = None
        latest_rank = get(client, leethub_rank_latest + '/' + str(ContestRank), leethub_headers)
        if latest_rank == -1:
            data['gap'] = 0
        else:
            data['gap'] = latest_rank - localRank
        res = post(client, leethub_rank_insert_url, headers=leethub_headers, params=data)
        status = None
        if type(res) == int:
            status = res
        else:
            status = res['status']
        log('get_contest_rank', str(status))
    except BaseException as e:
        log('get_contest_rank', str(e))


if __name__ == '__main__':
    requests.packages.urllib3.disable_warnings()
    users = getUsers()["user"]
    last_times = getLastFile()
    for u in users:
        session = requests.Session()
        client = login(leethub_login_url, u['leethub'])
        get_daily_rank(session, client, u['codename'])
        get_contest_rank(session, client, u['codename'], last_times[u['leethub']['username']]['lastContestTime'])