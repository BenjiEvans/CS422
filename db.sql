drop table if exists Grad_Student Cascade;
drop table if exists Project Cascade;
drop table if exists Project_info Cascade;
drop table if exists Professor Cascade;
drop table if exists Department Cascade;

CREATE TABLE Grad_Student(
  ssn Integer primary key,  
  name varchar(32) Not Null,
  age Integer Not Null,
  gender char(1) Not Null,
  degree varchar(32) Not Null,
  adviser Integer Not Null,
  major Integer Not Null, 
  Foreign Key (adviser) References Grad_Student(ssn) ON Delete Restrict ON Update Cascade
  -- add foreign key 
  -- Foreign Key (major) References Department(dept_num) On Delete Restrict ON Update Cascade;
);


CREATE TABLE Project(
  project_num Integer Not Null,
  prof_works Integer Not Null,
  grad_works Integer Not Null,
  Primary Key(project_num,prof_works,grad_works),  
  Foreign Key (grad_works) References Grad_Student(ssn) ON Delete Restrict ON Update Cascade
  -- add foreign key 
  -- Foreign Key (project_num) References Project_info(project_num) ON Delete Cascade ON Update Cascade
  -- Foreign Key (prof_works) References Professor(ssn) ON Delete Restrict ON Update Cascade
);

CREATE TABLE Project_info(
  project_num Integer Primary key,
  start_date Date Not Null,
  end_date Date Not Null,
  budget Integer Not Null,
  sponsor varchar(32) Not Null,
  principal_invest integer Not NULL
  -- add foreign key
  -- Foreign Key (principal_invest) References Professor(ssn) ON Delete Restrict On Update Cascade
 
);

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

CREATE TABLE Department(
  dept_num Integer primary key,
  name varchar(32) Not Null,
  main_office varchar(32) Not Null,
  run_by integer Not Null,
  Foreign Key (run_by) References Professor(ssn) ON Delete Restrict On Update Cascade
);

-- Adding foreign keys
ALTER TABLE Grad_Student
ADD Foreign Key (major) References Department(dept_num) On Delete Restrict ON Update Cascade;

ALTER TABLE Project
ADD Foreign Key (project_num) References Project_info(project_num) ON Delete Cascade ON Update Cascade;

ALTER TABLE Project 
ADD Foreign Key (prof_works) References Professor(ssn) ON Delete Restrict ON Update Cascade;

ALTER TABLE Project_info
ADD Foreign Key (principal_invest) References Professor(ssn) ON Delete Restrict On Update Cascade;

ALTER TABLE Professor
ADD Foreign Key (works_in) References Department(dept_num) ON Delete Restrict ON Update Cascade;


