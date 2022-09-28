use leethub;

select * from problem;
select * from prototag;
select * from tag;
select * from tag where tag_id not in (select tag_id from prototag where problem_id=49 and tag_id in (select tag_id from tag where user_id=1));
select * from tag where name='hello' and user_id=1;
select sum(count) from tag where user_id=1;
select * from user;
select * from leetrank;
select * from protouser;
select * from score;
select * from solution;
select * from leetrank order by rank_id desc limit 30;
select * from protouser where problem_id = 34;
select * from protouser where problem_id = 106 and user_id = 1 and submit_time > '2022-09-22';
select * from solution where title like '%%';

alter table problem modify column url varchar(100);
alter table score modify column description varchar(400);
alter table leetrank add `description` varchar(100);
alter table leetrank add `easy_count` int;
alter table leetrank add `medium_count` int;
alter table leetrank add `hard_count` int;
alter table leetrank add `contest_rank` int;
alter table leetrank add `score` int;
alter table leetrank drop index `update_time`;
alter table user add `dailyp_count` int;
alter table score add `gap` int;
alter table protouser auto_increment=20;
alter table solution add unique ptu_index (problem_id, user_id);
alter table solution add `type` int;
alter table user add `email` varchar(30);

update leetrank set hard_count = 2 where rank_id=104;
update protouser set problem_id=19 where pu_id=14;
update user set dailyp_count=null where username='jicoder';

insert into user (username, password) values ('jicoder', '88888888');
insert into user (username, password) values ('gaotao', '123456');
insert into leetrank (rank_val, type, update_time, user_id) values (19799, 0, '2022-07-07', 1);

delete from prototag where pt_id=28;
delete from leetrank where rank_id=74;
delete from protouser where user_id=13;
delete from leetrank where user_id=14;
delete from problem where problem_id = 21;
delete from solution where user_id = 14;
delete from score where user_id = 14;
delete from user where user_id=13;
delete from tag where tag_id=15;
truncate table solution;