#environment
isDevelopment = False

TIME_DIFF = 8 * 60 * 60

#type
COMMONPROBLEM = 0
DAILYPROBLEM = 1
COMPETITIONPROBLEM = 2

DailyRank = 0
ContestRank = 1
MostRank = 5

EASYCOUNT = 0
MEDIUMCOUNT = 1
HARDCOUNT = 2

# url
leethub_root_url = 'http://localhost:8080' if isDevelopment else 'http://10.70.178.114:8080'
leethub_user_root_url = leethub_root_url + '/user'
leethub_login_url = leethub_user_root_url + '/login'
leethub_user_info_url = leethub_user_root_url + '/info'
leethub_user_update_url = leethub_user_root_url + '/update'
leethub_user_update_dailypcount_url = leethub_user_update_url + '/dailypcount'
leethub_problem_root_url = leethub_root_url + '/problem'
leethub_problem_insert_url = leethub_problem_root_url + '/insert'
leethub_rank_root_url = leethub_root_url + '/rank'
leethub_rank_insert_url = leethub_rank_root_url + '/insert'
leethub_rank_latest = leethub_rank_root_url + '/latest'
leethub_rank_select_url = leethub_rank_root_url + '/count'
leethub_rank_update_url = leethub_rank_root_url + '/update'
leethub_ptu_root_url = leethub_root_url + '/pu'
leethub_ptu_insert_url = leethub_ptu_root_url + '/insert'
leethub_score_root_url = leethub_root_url + '/score'
leethub_score_insert_url = leethub_score_root_url + '/insert'
leethub_score_cur_url = leethub_score_root_url + '/cur'

leetcode_root_url = "https://leetcode.cn"
leetcode_login_url = leetcode_root_url + '/accounts/login'
leetcode_problem_url = leetcode_root_url + "/problems/"
leetcode_all_problem_url = leetcode_root_url + "/problemset/all"
leetcode_graphql_url = leetcode_root_url + "/graphql"
leetcode_nojgo_url = leetcode_graphql_url + '/noj-go'
leetcode_points_api = leetcode_root_url + '/points/api'
leetcode_points_api_total = leetcode_points_api + '/total'



# data
gaotao_login_data = {
    'login': "17795833224",
    'password': "abjdh156423"
}
daily_problem_params = {"query":"\nquery questionOfToday {\ntodayRecord {\ndate\nquestion {\nquestionId\nfrontendQuestionId: questionFrontendId\ntitleSlug\ndifficulty\ntitleCn: translatedTitle\n}\n}\n}\n","variables":{}}

date_params = {"query":"\nquery questionOfToday {\ntodayRecord {\ndate\n}\n}\n","variables":{}}

daily_problem_count_params = {"query":"\n    query getStreakCounter {\n  problemsetStreakCounter {\n    streakCount\n    daysSkipped\n    }\n}\n    "}

leetcode_user_params = {"query":"\nquery globalData {\nuserStatus {\nusername\n}\n}\n"}

daily_accept_record_params = {
    "operationName":"userProfileQuestions",
    "variables":{
        "status":"ACCEPTED",
        "skip":0,
        "first":10,
        "sortField":"LAST_SUBMITTED_AT",
        "sortOrder":"DESCENDING"
        },
    "query":"query userProfileQuestions($status: StatusFilterEnum!, $skip: Int!, $first: Int!, $sortField: SortFieldEnum!, $sortOrder: SortingOrderEnum!) {\n  userProfileQuestions(status: $status, skip: $skip, first: $first, sortField: $sortField, sortOrder: $sortOrder) {\n    questions {\n      translatedTitle\n      frontendId\n      titleSlug\n      difficulty\n      lastSubmittedAt\n}\n    __typename\n  }\n}\n"}

def get_daily_rank_params(username):
    params = {
        "operationName":"userPublicProfile",
        "variables":{
            "userSlug": username
            },
        "query":"query userPublicProfile($userSlug: String!) {\nuserProfilePublicProfile(userSlug: $userSlug) {\nsiteRanking\n}\n}\n"
    }
    return params

def get_contest_rank_params(username):
    params = {"query":"\n    query userContestRankingInfo($userSlug: String!) {\n  userContestRanking(userSlug: $userSlug) {\n    localRanking\n}\n  userContestRankingHistory(userSlug: $userSlug) {\n    attended\n    totalProblems\n    finishTimeInSeconds\n    score\n    ranking\n    contest {\n      title\n      startTime\n    }\n}\n}\n    ","variables":{"userSlug":username}}
    return params

def get_problem_content_params(titleSlug):
    params = {
        "operationName": "questionData",
        "variables": {
            "titleSlug": titleSlug
        },
        "query": "query questionData($titleSlug: String!) {\n  question(titleSlug: $titleSlug) {\n  questionId\n    translatedContent\n}\n}\n"
    }
    return params

def get_count_params(count_type, update_time):
    params = {
        "type": count_type,
        "update_time": update_time
    }
    return params

def update_count_params(count_type, update_time, count):
    params = {
        "type": count_type,
        "update_time": update_time,
        "count": count
    }
    return params

# 请求头
user_agent = r'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36'
leetcode_headers = {'User-Agent': user_agent, 'Connection': 'keep-alive', 'Content-Type': 'application/json',
            'Referer': leetcode_login_url}
leethub_headers = {'User-Agent': user_agent, 'Connection': 'keep-alive', 'Content-Type': 'application/json',
'Referer': leethub_login_url}

def get_leetcode_headers(cookie):
    leetcode_headers = {'User-Agent': user_agent, 'Connection': 'keep-alive', 'Content-Type': 'application/json',
        'Referer': leetcode_login_url, 'cookie': cookie}
    return leetcode_headers


#file path
root_path = '/home/jhr/leetcode-shell'
last_record_time_file = './lastRecordTime.json' if isDevelopment else root_path + '/lastRecordTime.json'
leethub_user_file = root_path + '/leethub_users.json'
