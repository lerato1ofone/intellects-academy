DROP DATABASE intellectsacademy;
DROP USER admin;
DROP type user_role;
CREATE USER admin WITH PASSWORD 'password' CREATEDB;
CREATE DATABASE intellectsacademy WITH template=template0 owner=admin;
\connect intellectsacademy;
ALTER default privileges grant all on tables to admin;
ALTER default privileges grant all on sequences to admin;

create TYPE user_role as ENUM('NONE', 'ADMIN', 'TEACHER', 'STUDENT');

CREATE TABLE ia_users(
    user_id integer primary key not null,
    title varchar(5) not null,
    first_name varchar(50) not null,
    last_name varchar(20) not null,
    email varchar(30) not null,
    password text not null,
    role user_role not null,
    dob date,
    semester_mark decimal(5,4),
    office_number varchar(30),
    avatar bytea,
    notes jsonb,
    courses jsonb
);

CREATE TABLE ia_courses(
    course_id integer primary key not null,
    user_id integer not null,
    title varchar(50) not null,
    description varchar(255) not null,
    lessons jsonb
);

ALTER table ia_courses add constraint cou_teachers_fk
foreign key (user_id) references ia_users(user_id);

CREATE TABLE ia_lessons(
    lesson_id integer primary key not null,
    title varchar(50) not null,
    lesson_date date not null,
    content varchar not null,
    course_id integer not null
);
ALTER TABLE ia_lessons add constraint les_courses_fk
foreign key (course_id) references ia_courses(course_id);

CREATE TABLE ia_notes(
    note_id integer primary key not null,
    title varchar(50) not null,
    note_date date not null,
    content varchar,
    user_id integer not null,
    lesson_id integer not null
);

alter table ia_notes add constraint not_users_fk
foreign key (user_id) references ia_users(user_id);
alter table ia_notes add constraint not_lessons_fk
foreign key (lesson_id) references ia_lessons(lesson_id);

CREATE TABLE ia_assessments(
    assessment_id integer primary key not null,
    title varchar(250) not null,
    assessment_date date not null,
    course_id integer not null
);

ALTER TABLE ia_assessments add constraint ass_courses_fk
foreign key (course_id) references ia_courses(course_id);

CREATE sequence ia_users_seq increment 1 start 1;
CREATE sequence ia_notes_seq increment 1 start 1;
CREATE sequence ia_lessons_seq increment 1 start 1;
CREATE sequence ia_courses_seq increment 1 start 1;
CREATE sequence ia_assessments_seq increment 1 start 1;