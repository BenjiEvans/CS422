drop table if exists Grad_Student Cascade;
drop table if exists Project Cascade;
drop table if exists Professor Cascade;
drop table if exists Department Cascade;
drop table if exists Grad_works Cascade;
drop table if exists Works_on Cascade;

CREATE TABLE Grad_Student(
  ssn Integer primary key,  
  name varchar(32) Not Null,
  age Integer Not Null,
  gender char(1) Not Null,
  degree varchar(32) Not Null,
  adviser Integer Null Unique,
  major Integer Not Null
  -- add foreign key 
  -- Foreign Key (adviser) References Grad_Student(ssn) ON Delete Restrict ON Update Cascade
  -- Foreign Key (major) References Department(dept_num) On Delete Restrict ON Update Cascade;
);

INSERT INTO Grad_Student values(111, 'Seth', 21, 'M', 'M.S.', null, 100);
INSERT INTO Grad_Student values(222, 'Beth', 21, 'M', 'Ph.D', null, 300);
INSERT INTO Grad_Student values(333, 'Tom', 21, 'M', 'Ph.D', 222, 300);
INSERT INTO Grad_Student values(444, 'Pam', 21, 'M', 'M.S.', 111, 100);
INSERT INTO Grad_Student values(555, 'Mike', 21, 'M', 'M.S.', null, 100);
INSERT INTO Grad_Student values(666, 'Jann', 21, 'M', 'Ph.D', null, 200);
INSERT INTO Grad_Student values(777, 'Jim', 21, 'M', 'M.S.', 555, 100);
INSERT INTO Grad_Student values(888, 'Tim', 21, 'M', 'Ph.D', 666, 200);


CREATE TABLE Project(
  project_num Integer Primary key,
  start_date Date Not Null,
  end_date Date Not Null,
  budget Integer Not Null,
  sponsor varchar(32) Not Null,
  principal_invest integer Not NULL
  -- add foreign key
  -- Foreign Key (principal_invest) References Professor(ssn) ON Delete Restrict On Update Cascade
);


INSERT INTO Project values(10, '2014-01-25', '2014-04-25', 3000, 'NASA', 123);
INSERT INTO Project values(20, '2014-05-01', '2014-06-01', 4000, 'CCP', 456);
INSERT INTO Project values(30, '2014-10-30', '2014-11-30', 5000, 'NSA', 654);
INSERT INTO Project values(40, '2013-09-30', '2013-12-15', 6000, 'SpaceX', 321);




CREATE TABLE Professor(
  ssn Integer Primary key,
  name varchar(32) Not Null,
  specialty varchar(32),
  rank varchar(32),
  age Integer Not Null,
  gender char(1) Not Null,
  works_in Integer Not Null
  -- add foreign key 
  -- Foreign Key (works_in) References Department(dept_num) ON Delete Restrict ON Update Cascade;
);

INSERT INTO Professor values(123, 'Bill', 'A.I.', 'Rank 1', 55, 'M', 100);
INSERT INTO Professor values(321, 'Jill', 'Database', 'Rank 2', 57, 'F', 100);
INSERT INTO Professor values(789, 'Paul', 'Compilers', 'Rank 2', 42, 'M', 100);
INSERT INTO Professor values(456, 'Kate', 'English', 'Rank 1', 51, 'F', 200);
INSERT INTO Professor values(890, 'Jeff', 'English', 'Rank 2', 62, 'M', 200);
INSERT INTO Professor values(654, 'Lisa', 'History', 'Rank 1', 45, 'F', 300);
INSERT INTO Professor values(198, 'John', 'History', 'Rank 2', 39, 'M', 300);
INSERT INTO professor values(190, 'Juan', 'Embedded', 'Rank 2', 59, 'M', 100);
INSERT INTO professor values(291, 'Jane', 'Algorithms', 'Rank 3', 44, 'F', 100);
INSERT INTO professor values(374, 'Lane', 'History', 'Rank 3', 37, 'M', 200);
INSERT INTO professor values(697, 'Anne', 'English', 'Rank 2', 63, 'F', 200);
INSERT INTO professor values(156, 'Dave', 'History', 'Rank 2', 41, 'M', 300);
INSERT INTO professor values(784, 'Katy', 'English', 'Rank 3', 33, 'F', 300);




CREATE TABLE Department(
  dept_num Integer primary key,
  name varchar(32) Not Null,
  main_office varchar(32) Not Null,
  run_by integer Not Null
  -- add foreign key 
  -- Foreign Key (run_by) References Professor(ssn) ON Delete Restrict On Update Cascade
);

INSERT INTO Department values(100, 'Computer Science', 'Office 1', 123);
INSERT INTO Department values(200, 'History', 'Office 2', 654);
INSERT INTO Department values(300, 'English', 'Office 3', 456);


CREATE TABLE Grad_works(
 project_num Integer Not Null,
 ssn Integer Not Null,
 Primary Key(project_num,ssn)
 -- add foreign key 
 -- Foreign Key (project_num) References Project(project_num) ON Delete Cascade On Update Cascade,
 -- Foreign Key (ssn) References Grad_Student(ssn) ON Delete Cascade On Update Cascade
);

CREATE TABLE Works_on(
 project_num Integer Not Null,
 ssn Integer Not Null,
 Primary Key(project_num,ssn)
 -- add foreign key
 -- Foreign Key (project_num) References Project(project_num) ON Delete Cascade On Update Cascade,
 -- Foreign Key (ssn) References Professor(ssn) ON Delete Cascade On Update Cascade
);

INSERT INTO Grad_works values(10,111);
INSERT INTO Works_on values(10,123);
INSERT INTO Grad_works values(20,222);
INSERT INTO Works_on values(20,456);
INSERT INTO Grad_works values(30,666);
INSERT INTO Works_on values(30,654);
INSERT INTO Grad_works values(40,555);
INSERT INTO Works_on values(40,321);


-- Adding foreign keys

ALTER TABLE Grad_Student
ADD Foreign Key (major) References Department(dept_num) On Delete Restrict ON Update Cascade;
ALTER TABLE Grad_Student
ADD Foreign Key (adviser) References Grad_Student(ssn) ON Delete Restrict ON Update Cascade;
ALTER TABLE Project
ADD Foreign Key (principal_invest) References Professor(ssn) ON Delete Restrict On Update Cascade;
ALTER TABLE Professor
ADD Foreign Key (works_in) References Department(dept_num) ON Delete Restrict ON Update Cascade;
ALTER TABLE Department
ADD Foreign Key (run_by) References Professor(ssn) ON Delete Restrict On Update Cascade;
ALTER TABLE Grad_works
ADD Foreign Key (project_num) References Project(project_num) ON Delete Cascade On Update Cascade;
ALTER TABLE Grad_works
ADD Foreign Key (ssn) References Grad_Student(ssn) ON Delete Cascade On Update Cascade;
ALTER TABLE Works_on
ADD Foreign Key (project_num) References Project(project_num) ON Delete Cascade On Update Cascade;
ALTER TABLE Works_on
ADD Foreign Key (ssn) References Professor(ssn) ON Delete Cascade On Update Cascade;





