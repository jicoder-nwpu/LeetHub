CREATE DATABASE IF NOT EXISTS leethub;

use leethub;

CREATE TABLE IF NOT EXISTS `User`(
   `user_id` INT UNSIGNED AUTO_INCREMENT,
   `username` VARCHAR(30) NOT NULL,
   `password` VARCHAR(15) NOT NULL,
   `signup_time` timestamp,
   PRIMARY KEY ( `user_id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `Score`(
   `score_id` INT UNSIGNED AUTO_INCREMENT,
   `score_val` INT UNSIGNED NOT NULL,
   `description` VARCHAR(20),
   `update_time` timestamp,
   `user_id` INT UNSIGNED NOT NULL,
   PRIMARY KEY ( `score_id` ),
   foreign key(`user_id`) references User(`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `Rank`(
   `rank_id` INT UNSIGNED AUTO_INCREMENT,
   `rank_val` INT UNSIGNED NOT NULL,
   `type` tinyint NOT NULL,
   `update_time` timestamp,
   `user_id` INT UNSIGNED NOT NULL,
   PRIMARY KEY ( `rank_id` ),
   foreign key(`user_id`) references User(`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `Problem`(
   `problem_id` INT UNSIGNED AUTO_INCREMENT,
   `name` VARCHAR(30),
   `difficulty` tinyint NOT NULL,
   `url` VARCHAR(50),
   `context` TEXT NOT NULL,
   PRIMARY KEY ( `problem_id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `Solution`(
   `solution_id` INT UNSIGNED AUTO_INCREMENT,
   `title` VARCHAR(30),
   `context` TEXT,
   `update_time` timestamp,
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
   `submit_time` timestamp,
   PRIMARY KEY ( `pu_id` ),
   foreign key(`user_id`) references User(`user_id`),
   foreign key(`problem_id`) references Problem(`problem_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;