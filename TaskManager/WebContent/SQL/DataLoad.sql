DELETE FROM UserTask;
DELETE FROM Tasks;
DELETE FROM TeamProject;
DELETE FROM Projects;
DELETE FROM Categories;
DELETE FROM UserTeam;
DELETE FROM Teams;
DELETE FROM Users;

INSERT INTO Users VALUES ('sherma14@email.franklin.edu','steve','steve','sherman','steve');
INSERT INTO Users VALUES ('bernar15@email.franklin.edu','paul','paul','bernard','paul');
INSERT INTO Users VALUES ('kofod01@email.franklin.edu','andy','andy','kofod','andy');

INSERT INTO Teams VALUES (1,'Team 1');
INSERT INTO Teams VALUES (2,'Team 2');
INSERT INTO Teams VALUES (3,'Team 3');
INSERT INTO Teams VALUES (4,'Team 4');
INSERT INTO Teams VALUES (5,'Team 5');

INSERT INTO UserTeam VALUES ('sherma14@email.franklin.edu',2);
INSERT INTO UserTeam VALUES ('sherma14@email.franklin.edu',4);
INSERT INTO UserTeam VALUES ('bernar15@email.franklin.edu',1);
INSERT INTO UserTeam VALUES ('bernar15@email.franklin.edu',3);
INSERT INTO UserTeam VALUES ('kofod01@email.franklin.edu',2);
INSERT INTO UserTeam VALUES ('kofod01@email.franklin.edu',5);

INSERT INTO Categories VALUES (1,'Development');
INSERT INTO Categories VALUES (2,'Maintenance');
INSERT INTO Categories VALUES (3,'Design');
INSERT INTO Categories VALUES (4,'Training');
INSERT INTO Categories VALUES (5,'Other');

INSERT INTO Projects VALUES (1,1,'2014-12-31');
INSERT INTO Projects VALUES (2,1,'2014-09-15');
INSERT INTO Projects VALUES (3,3,'2014-11-25');
INSERT INTO Projects VALUES (4,4,'2015-04-21');
INSERT INTO Projects VALUES (5,2,'2014-12-31');
INSERT INTO Projects VALUES (6,5,'2016-02-01');

INSERT INTO TeamProject VALUES (1,5);
INSERT INTO TeamProject VALUES (2,3);
INSERT INTO TeamProject VALUES (3,1);
INSERT INTO TeamProject VALUES (4,2);

INSERT INTO Tasks VALUES (1,'Work on Development 1','2014-07-31','Medium',30.5,5.25,'Open',1);
INSERT INTO Tasks VALUES (2,'Work on Development 2','2014-08-31','Low',40.5,0,'Open',1);
INSERT INTO Tasks VALUES (3,'Work on Development 1','2014-09-10','Medium',15,5.25,'Open',2);
INSERT INTO Tasks VALUES (4,'Work on Development 2','2014-08-31','Medium',60,5.25,'Open',2);
INSERT INTO Tasks VALUES (5,'Work on Design 1','2014-10-30','Low',80.5,5.25,'Open',3);
INSERT INTO Tasks VALUES (6,'Work on Design 2','2014-08-31','Medium',45.5,5.25,'Open',3);
INSERT INTO Tasks VALUES (7,'Work on Training 1','2014-10-31','Medium',40,5.25,'Open',4);
INSERT INTO Tasks VALUES (8,'Work on Training 2','2014-12-31','Medium',75,5.25,'Open',4);
INSERT INTO Tasks VALUES (9,'Work on Maintenance 1','2014-09-30','High',20,5.25,'Open',5);
INSERT INTO Tasks VALUES (10,'Work on Maintenance 2','2014-08-15','Medium',8,5.25,'Open',5);
INSERT INTO Tasks VALUES (11,'Work on Other 1','2015-07-31','Medium',35.5,5.25,'Open',6);
INSERT INTO Tasks VALUES (12,'Work on Other 2','2014-12-31','High',55,5.25,'Open',6);

INSERT INTO UserTask VALUES ('sherma14@email.franklin.edu',1);
INSERT INTO UserTask VALUES ('sherma14@email.franklin.edu',3);
INSERT INTO UserTask VALUES ('sherma14@email.franklin.edu',5);
INSERT INTO UserTask VALUES ('bernar15@email.franklin.edu',2);
INSERT INTO UserTask VALUES ('bernar15@email.franklin.edu',4);
INSERT INTO UserTask VALUES ('bernar15@email.franklin.edu',6);
INSERT INTO UserTask VALUES ('kofod01@email.franklin.edu',7);
INSERT INTO UserTask VALUES ('kofod01@email.franklin.edu',9);
INSERT INTO UserTask VALUES ('kofod01@email.franklin.edu',11);