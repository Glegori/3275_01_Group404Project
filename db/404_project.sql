DROP TABLE EXPENSE_404_project IF EXISTS;
DROP TABLE USER_404_project IF EXISTS;

CREATE TABLE USER_404_project(
id INTEGER PRIMARY KEY AUTO_INCREMENT,
USERNAME VARCHAR(30),
PASSWORD VARCHAR(50),
reportsFrom VARCHAR(20),
userType VARCHAR(20),
total double
);

CREATE TABLE EXPENSE_404_project(
id INTEGER PRIMARY KEY AUTO_INCREMENT,
expenseName VARCHAR(30),
expenseCost VARCHAR(50),
date date,
expenseType VARCHAR(20),
expenseStatus VARCHAR(2),
billImage VARCHAR(240),
user VARCHAR(30)
);

insert into USER_404_project(USERNAME, PASSWORD, REPORTSFROM, USERTYPE, TOTAL) values('Alfredo', '12345', ' ', 'admin', 0);
insert into USER_404_project(USERNAME, PASSWORD, REPORTSFROM, USERTYPE, TOTAL) values('Gregory', '12345', ' ', 'admin', 0);
insert into USER_404_project(USERNAME, PASSWORD, REPORTSFROM, USERTYPE, TOTAL) values('Francis', '12345', ' ', 'admin', 0);
insert into USER_404_project(USERNAME, PASSWORD, REPORTSFROM, USERTYPE, TOTAL) values('Shubham', '12345', ' ', 'admin', 0);

Select * from USER_404_project;