DROP TABLE IF EXISTS `taskmanager`.`userproject`;
DROP TABLE IF EXISTS `taskmanager`.`usertask`;
DROP TABLE IF EXISTS `taskmanager`.`teamproject`;
DROP TABLE IF EXISTS `taskmanager`.`tasks`;
DROP TABLE IF EXISTS `taskmanager`.`projects`;
DROP TABLE IF EXISTS `taskmanager`.`teams`;
DROP TABLE IF EXISTS `taskmanager`.`categories`;
DROP TABLE IF EXISTS `taskmanager`.`users`;
CREATE TABLE `Users` (
  `user_id` varchar(50) NOT NULL DEFAULT '',
  `nickname` varchar(25) DEFAULT NULL,
  `firstname` varchar(25) DEFAULT NULL,
  `lastname` varchar(25) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `salt` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Categories` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Projects` (
  `project_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(50) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `final_deadline` date DEFAULT NULL,
  PRIMARY KEY (`project_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `Projects_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `Categories` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Tasks` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(80) DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `priority` varchar(25) DEFAULT NULL,
  `time_estimate` double DEFAULT NULL,
  `time_completed` double DEFAULT NULL,
  `status` varchar(25) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`task_id`),
  KEY `project_id` (`project_id`),
  CONSTRAINT `Tasks_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `Projects` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `UserProject` (
  `user_id` varchar(50) NOT NULL DEFAULT '',
  `project_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`,`project_id`),
  KEY `project_id` (`project_id`),
  CONSTRAINT `UserProject_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `Users` (`user_id`),
  CONSTRAINT `UserProject_ibfk_2` FOREIGN KEY (`project_id`) REFERENCES `Projects` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `UserTask` (
  `user_id` varchar(50) NOT NULL DEFAULT '',
  `task_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`,`task_id`),
  KEY `task_id` (`task_id`),
  CONSTRAINT `UserTask_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `Users` (`user_id`),
  CONSTRAINT `UserTask_ibfk_2` FOREIGN KEY (`task_id`) REFERENCES `Tasks` (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
