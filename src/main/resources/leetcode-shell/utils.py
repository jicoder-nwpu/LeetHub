import requests
import time
import json
import datetime
from config import *


def login(url, data):
    client = requests.session()
    client.encoding = "utf-8"

    while True:
        try:
            client.get(url, verify=False)

            result = client.post(url, data=data, headers=dict(Referer=url))

            if result.ok:
                # print("登录成功!")
                break
        except:
            # print("登录失败，请稍后再试...")
            time.sleep(5*1000)

    return client


def check_leetcode(data, name):
    client = login(leetcode_login_url, data)
    if name != '' and get_username(client) == name:
        return 1
    return 0


def logout(client, url):
    client.get(url, verify=False)


def get_username(client):
    res = post(client, leetcode_graphql_url, headers=leetcode_headers, params=leetcode_user_params)
    return res['data']['userStatus']['username']


def get_problem_info(client, titleSlug):
    content = post(client, leetcode_graphql_url, leetcode_headers, get_problem_content_params(titleSlug))
    return content['data']['question']


def get(client, url, headers):
    resp = client.get(url, headers=headers, timeout=10)
    return resp.json()


def post(client, url, headers, params):
    json_data = json.dumps(params).encode('utf8')
    resp = client.post(url, data=json_data, headers=headers, timeout=10)  
    content = resp.json()
    return content


def get_csrftoken(session):
    cookies = session.get(leetcode_root_url).cookies
    print(cookies)
    csrftoken = ''
    for cookie in cookies:
        if cookie.name == 'csrftoken':
            csrftoken = cookie.value
    return csrftoken


def get_date(session):
    try:
        content = post(session, leetcode_graphql_url, leetcode_headers, date_params)
        content = content['data']['todayRecord'][0]
        return content['date']
    except BaseException as e:
        return None

def formateDate(data):
    date = time.localtime(int(data))
    date = time.strftime("%Y-%m-%d", date)
    return date

def formateTime(data):
    date = time.localtime(int(data))
    date = time.strftime("%Y-%m-%d %H:%M:%S", date)
    return date

def getLastFile():
    with open(last_record_time_file, 'r') as fin:
        res = fin.read()
        res = json.loads(res)
        return res

def addLastRecord(name):
    res = getLastFile()
    records = {}
    records["lastRecordTime"] = "0"
    records["lastScoreTime"] = 0
    records["lastContestTime"] = 0
    with open(last_record_time_file, 'w') as fout:
        res[name] = records
        # print(res)
        fout.write(json.dumps(res, indent = 1))

def writeLastTime(user, name, time):
    res = getLastFile()
    with open(last_record_time_file, 'w') as fin:
        res[user][name] = time
        fin.write(json.dumps(res, indent = 1))

def log(operation, status):
    print(str(datetime.datetime.now()) + ': ' + '>' * 5 + ' ' + operation +' ' + '>' * 5 + ' status: ' + status)

def getUsers():
    with open(leethub_user_file, 'r') as fin:
        res = json.loads(fin.read())
        return res

def addHubUsers(user_info):
    res = getUsers()
    res["user"].append(user_info)
    with open(leethub_user_file, 'w') as fout:
        fout.write(json.dumps(res, indent=4))