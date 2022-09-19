CREATE DATABASE IF NOT EXISTS leethub;

use leethub;

CREATE TABLE IF NOT EXISTS `User`(
   `user_id` INT UNSIGNED AUTO_INCREMENT,
   `username` VARCHAR(30) UNIQUE NOT NULL,
   `password` VARCHAR(15) NOT NULL,
   `signup_time` timestamp default current_timestamp,
   `dailyp_count` int,
   PRIMARY KEY ( `user_id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `Score`(
   `score_id` INT UNSIGNED AUTO_INCREMENT,
   `score_val` INT UNSIGNED NOT NULL,
   `description` VARCHAR(400),
   `update_time` timestamp,
   `user_id` INT UNSIGNED NOT NULL,
   `gap` int,
   PRIMARY KEY ( `score_id` ),
   foreign key(`user_id`) references User(`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `leetRank`(
   `rank_id` INT UNSIGNED AUTO_INCREMENT,
   `rank_val` INT UNSIGNED NOT NULL,
   `type` tinyint NOT NULL,
   `description` VARCHAR(100),
   `easy_count` int,
   `medium_count` int,
   `hard_count` int,
   `contest_rank` int,
   `score` int,
   `update_time` date,
   `gap` int,
   `user_id` INT UNSIGNED NOT NULL,
   PRIMARY KEY ( `rank_id` ),
   foreign key(`user_id`) references User(`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `Problem`(
   `problem_id` INT UNSIGNED AUTO_INCREMENT,
   `title` VARCHAR(30) NOT NULL,
   `questionId` VARCHAR(10) UNIQUE NOT NULL,
   `type` TINYINT,
   `difficulty` VARCHAR(10) NOT NULL,
   `url` VARCHAR(100) NOT NULL,
   `content` TEXT NOT NULL,
   `date` date,
   PRIMARY KEY ( `problem_id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `Solution`(
   `solution_id` INT UNSIGNED AUTO_INCREMENT,
   `title` VARCHAR(30),
   `context` TEXT,
   `update_time` timestamp default current_timestamp on update current_timestamp,
   `user_id` INT UNSIGNED NOT NULL,
   `problem_id` INT UNSIGNED NOT NULL,
   PRIMARY KEY ( `solution_id` ),
   foreign key(`user_id`) references User(`user_id`),
   foreign key(`problem_id`) references Problem(`problem_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `Tag`(
   `tag_id` INT UNSIGNED AUTO_INCREMENT,
   `name` VARCHAR(20),
   `count` INT,
   `user_id` INT UNSIGNED NOT NULL,
   PRIMARY KEY ( `tag_id` ),
   foreign key(`user_id`) references User(`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `ProToTag`(
   `pt_id` INT UNSIGNED AUTO_INCREMENT,
   `tag_id` INT UNSIGNED NOT NULL,
   `problem_id` INT UNSIGNED NOT NULL,
   PRIMARY KEY ( `pt_id` ),
   foreign key(`tag_id`) references Tag(`tag_id`),
   foreign key(`problem_id`) references Problem(`problem_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `ProToUser`(
   `pu_id` INT UNSIGNED AUTO_INCREMENT,
   `user_id` INT UNSIGNED NOT NULL,
   `problem_id` INT UNSIGNED NOT NULL,
   `alias` VARCHAR(20),
   `submit_time` timestamp,
   PRIMARY KEY ( `pu_id` ),
   foreign key(`user_id`) references User(`user_id`),
   foreign key(`problem_id`) references Problem(`problem_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;