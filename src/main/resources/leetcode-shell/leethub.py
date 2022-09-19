from flask import Flask, request
from utils import *
from config import *
from getLeetCodeRank import *
from getRecordAndCount import *

app = Flask(__name__)


@app.route('/')
def hello_world():
    return 'hello, world!'

@app.route('/adduser', methods=['GET'])
def adduser():
    #接收参数
    callback = request.args.get('callback')
    username = request.args.get('username')
    hub_password = request.args.get('hub_password')
    codename = request.args.get('codename')
    login_name = request.args.get('login')
    code_password = request.args.get('code_password')
    # print(username, hub_password, codename, login_name, code_password)
    #添加更新时间记录
    addLastRecord(username)
    requests.packages.urllib3.disable_warnings()
    session = requests.Session()
    #构建系统登录参数
    hub_account = {}
    hub_account["username"] = username
    hub_account["password"] = hub_password
    code_account = {}
    code_account["login"] = login_name
    code_account["password"] = code_password
    #获取排名
    leethub_client = login(leethub_login_url, hub_account)
    get_daily_rank(session, leethub_client, codename)
    get_contest_rank(session, leethub_client, codename, getLastFile()[username]['lastContestTime'])
    #获取答题记录
    leetcode_client = login(leetcode_login_url, code_account)
    getDailyProblemRecord(leetcode_client, leethub_client, int(getLastFile()[username]['lastRecordTime']))
    get_daily_count(leetcode_client, leethub_client)
    get_points_info(leetcode_client, leethub_client, getLastFile()[username]['lastScoreTime'])
    #添加用户
    user = {}
    user["leethub"] = hub_account
    user["leetcode"] = code_account
    user["codename"] = codename
    addHubUsers(user)
    data = {
        "msg": "ok"
    }
    return callback + "(" + json.dumps(data) + ")"

@app.route('/check', methods=['GET'])
def check():
    callback = request.args.get('callback')
    login_name = request.args.get('login')
    code_password = request.args.get('password')
    name = request.args.get('name')
    data = {}
    data['login'] = login_name
    data['password'] = code_password
    requests.packages.urllib3.disable_warnings()
    res = check_leetcode(data, name)
    if res != 1:
        data = {
            "msg": "no"
            }
        return callback + "(" + json.dumps(data) + ")"
    else:
        data = {
            "msg": "ok"
        }
        return callback + "(" + json.dumps(data) + ")"