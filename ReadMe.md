#### 项目总览

1. python flask 后端 -- 提供用户注册以及LeetCode账户验证服务
2. python 脚本          -- 提供LeetCode网站数据爬取服务
3. SpringBoot 后端   -- 提供Web APP各项功能
4. MySQL & mybatis -- 存储数据

- Thymeleaf & Bootstrap & Charts & Editor
- Jquery & AJAX

#### 环境要求

1. python后端

> Flask==2.2.2
> 
> requests==2.22.0

2. SpringBoot 后端

>jdk 1.8
>maven
>git

3. 数据库

> MySQL

#### 项目部署

##### 1. 创建数据库

> ./src/main/resources/sql/create_database.sql

##### 2. 启动SpringBoot项目

###### a 通过IDE启动

###### b 运行jar包

> windows: javaw -jar xxx.jar
> 
> linux: nohup java -jar xxx.jar >> log.txt &

##### 3. 启动flask服务

> ./src/main/resources/leetcode-shell/leethub.py
> 
> ./src/main/resources/leetcode-shell/leethub_users.json
> 
> ./src/main/resources/leetcode-shell/lastRecordTime.json
> 
> linux: 
>
> export FLASK_APP=leethub.py
> 
> nohup flask run --host=0.0.0.0 >> spider_log.txt &

##### 4. 部署爬虫脚本

> ./src/main/resources/leetcode-shell/*
>
> sudo vim /etc/crontab
>
> 1  0  * * * user python3 ./leetcode-shell/getDailyProblem.py >> ./leetcode-shell/log.txt
> 
> 10 1  * * * user python3 ./leetcode-shell/getLeetCodeRank.py >> ./leetcode-shell/log.txt
> 
> 0 0,7-23/1 * * * user python3 ./leetcode-shell/getRecordAndCount.py >> ./leetcode-shell/log.txt

每天0点1分爬取每日一题，1点10分爬取排名，上午7点到晚上0点整点爬取做题记录以及积分信息。
