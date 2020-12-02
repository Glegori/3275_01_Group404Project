DROP TABLE EXPENSE_404_project IF EXISTS;
DROP TABLE USER_404_project IF EXISTS;
DROP TABLE BUDGET_404_project IF EXISTS;


CREATE TABLE USER_404_project(
id INTEGER PRIMARY KEY AUTO_INCREMENT,
USERNAME VARCHAR(30),
PASSWORD VARCHAR(50),
reportsFrom VARCHAR(20),
userType VARCHAR(20),
total double
);

CREATE TABLE BUDGET_404_PROJECT (
id INTEGER PRIMARY KEY AUTO_INCREMENT,
EXPENSETYPE VARCHAR(30),
BUDGET double);

CREATE TABLE EXPENSE_404_project(
id INTEGER PRIMARY KEY AUTO_INCREMENT,
expenseName VARCHAR(30),
expenseCost double,
date VARCHAR(20),
expenseType VARCHAR(20),
expenseStatus VARCHAR(10),
EXPENSEDESC varchar (255),
billImage VARCHAR(240),
user VARCHAR(30),
FOREIGN KEY (user) REFERENCES USER_404_project(USERNAME)

);



insert into USER_404_project(USERNAME, PASSWORD, REPORTSFROM, USERTYPE, TOTAL) values('Alfredo', '12345', '', 'admin', 0);
insert into USER_404_project(USERNAME, PASSWORD, REPORTSFROM, USERTYPE, TOTAL) values('Gregory', '12345', '', 'admin', 0);
insert into USER_404_project(USERNAME, PASSWORD, REPORTSFROM, USERTYPE, TOTAL) values('Francis', '12345', 'Alfredo', 'admin', 0);
/*insert into EXPENSE_404_project(expensename, expensecost, date, expensetype, expensestatus, billimage, user)
                            values('expenseName','expenseCost', '2020-11-11', 'Personal', 'Pending', 'billImageUrl', 'Alfredo')) ;
 */
insert into USER_404_project(USERNAME, PASSWORD, REPORTSFROM, USERTYPE, TOTAL) values('Shubham', '12345', '', 'admin', 0);
insert into BUDGET_404_PROJECT(EXPENSETYPE, BUDGET) values('FOOD', -1);
insert into BUDGET_404_PROJECT(EXPENSETYPE, BUDGET) values('PERSONAL', -1);
insert into BUDGET_404_PROJECT(EXPENSETYPE, BUDGET) values('SERVICES', -1);
insert into BUDGET_404_PROJECT(EXPENSETYPE, BUDGET) values('SUPPLIERS', -1);
insert into BUDGET_404_PROJECT(EXPENSETYPE, BUDGET) values('TRAVEL', -1);

Select * from USER_404_project;