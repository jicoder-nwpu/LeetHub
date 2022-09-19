from config import *
from utils import *

def get_daily_problem(session):
    
    try:
        content = post(session, leetcode_graphql_url, leetcode_headers, daily_problem_params)
        content = content['data']['todayRecord'][0]
        question = content['question']
        title_slug = question['titleSlug']

        info = get_problem_info(session, title_slug)['translatedContent']
        
        data = {}
        data['title'] = question['titleCn']
        data['questionId'] = question['questionId']
        data['type'] = DAILYPROBLEM
        data['difficulty'] = question['difficulty']
        data['url'] = leetcode_problem_url + question['titleSlug']
        data['content'] = info
        data['date'] = content['date']
        
        res = post(session, leethub_problem_insert_url, leethub_headers, data)
        status = None
        if type(res) == int:
            status = res
        else:
            status = res['status']
        log('get_daily_problem', str(status))
    except BaseException as e:
        log('get_daily_problem', str(e))


if __name__ == '__main__':
    requests.packages.urllib3.disable_warnings()
    session = requests.Session()
    get_daily_problem(session)