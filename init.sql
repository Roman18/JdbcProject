CREATE SCHEMA humaninfo;


CREATE TABLE human(
id SERIAL PRIMARY KEY,
"name" VARCHAR(30) NOT NULL,
age INT2 NOT NULL,
salary DECIMAL NOT NULL
);