```sql

create database IF NOT EXISTS temp1;
drop database IF EXISTS temp1;
/*creat and drop*/

create database COLLAGE;
use collage;
/*
CREATE TABLE table_name(
collum_name_1 datatype constrain;
collum_name_2 datatype constrain;
)
*/
CREATE table student(
id INT PRIMARY KEY,
nm VARCHAR(50),
age INT NOT NULL 
);

INSERT INTO student VALUE(1,"SOM",56);
INSERT INTO student VALUE(2,"TUKU",56);
SELECT * FROM student;

SHOW DATABASES;
SHOW TABLES;

/*DEPENDING UPON SEQUENCE DATA ENTERS*/
INSERT INTO STUDENT (ID,NM,AGE) VALUE(3,"MAMON",45);
INSERT INTO STUDENT (ID,AGE,NM) VALUE(4,45,'JOY');

INSERT INTO STUDENT (ID,NM,AGE) VALUES(5,"RAM",74),(6,"SAYM",25);

/*PRYMARY  AND CONSTRAINTS*/
/*
CRATE TABLE NAME-TABLE1(
	ID INT,
	NM VARCHAR(10),
    
	cradit INT DEFAULT 8,---->SET DEFAULT VALUE AS alter8
    
	1> PRIMARY KEY (ID),-->ONLY ID IS UNICK AND NOT NULL
	2> PRIMARY KEY (ID,NM),-->ONLY COMBINATION OF ID AND NM IS UNICK AND NOT NULL
	
    cust_id INT,
    FOREIGN KEY (cust_id) REFERENCES table_name2(prymary_key_name),   
    
    CONSTRAINT age_check CHECK ( age>=18 AND city="Delhi )
)
*/
/*SECOND CLASS*/
CREATE DATABASE IF NOT EXISTS collg;
USE collg;
CREATE TABLE student(
	rollno INT PRIMARY KEY,
    name VARCHAR(30),
    marks INT NOT NULL,
    grade VARCHAR(1) CHECK (grade IN ('O','E','A','B','C','D')),
    city VARCHAR(20)
);

SELECT name,marks FROM student;
SELECT * FROM student;

SELECT DISTINCT city FROM student; /*---> DISTINK NAME OR values APPER*/

/*SELECT * FTOM table WHERE condition*/
/*
OPARATOR
1>ARETH--> + - * / %
2>COMPARE---> = != > < >= <=
3>LOGICAL--> AND OR NOT IN BETWEEN ALL LIKE ANY
*/
SELECT name,marks+10,marks FROM student;
SELECT * FROM student WHERE marks > 60;
SELECT * FROM student WHERE marks + 10 > 60;
SELECT * FROM student WHERE marks > 50 AND city = 'KOLKATA';
SELECT * FROM student WHERE marks BETWEEN 50 AND 60;
SELECT * FROM student WHERE city IN("KOLKATA","PUNE");
SELECT * FROM student WHERE city NOT IN("KOLKATA","PUNE");
SELECT * FROM student WHERE marks > 50 LIMIT 3;/*-->ONY 3 VALUE WILL APPER*/

SELECT * FROM student ORDER BY marks ASC;
SELECT * FROM student ORDER BY marks DESC LIMIT 3;

/*SECOND HIGHEST NUMBER*/
SELECT * FROM student ORDER BY marks DESC LIMIT 1 OFFSET 2; -- skip 2 row then select 1 row
SELECT * FROM student ORDER BY marks DESC LIMIT 3 OFFSET 1; -- OFFSET "N" Skip the first "N" rows

SELECT * FROM (
    SELECT 
        *,
        RANK() OVER (ORDER BY marks DESC) AS rnk
    FROM student
) student ; -- WHERE rnk = 2;

SELECT * FROM (
    SELECT 
		*,
		RANK() OVER (ORDER BY marks DESC) AS rnk,
		DENSE_RANK() OVER (ORDER BY marks DESC) AS dense_rnk,
		ROW_NUMBER() OVER (ORDER BY marks DESC) AS row_num
    FROM student
) student ; -- WHERE rnk = 2;

SELECT *,name, city,
RANK() OVER (PARTITION BY city ORDER BY marks DESC)
FROM student;

SELECT MAX(marks) FROM student WHERE marks < (SELECT MAX(marks) FROM student);

/*FUNCTION*/
SELECT MAX(marks) FROM student;
SELECT MIN(marks) FROM student;
SELECT SUM(marks) FROM student;

SELECT AVG(marks) FROM student;
SELECT ROUND(AVG(marks), 2) AS avg_marks FROM student;
SELECT FORMAT(AVG(marks), 2) AS avg_marks FROM student;

SELECT COUNT(rollno) FROM student;
SELECT * FROM student WHERE  marks > (SELECT AVG(marks) FROM student);

SELECT * FROM student WHERE  marks > AVG(marks); -- not run give error


SELECT city ,COUNT(rollno),AVG(marks) FROM student GROUP BY city;/*-->MAKE GROUP BASIS ON VALUE*/

SELECT city ,COUNT(rollno),AVG(marks) FROM student GROUP BY city HAVING AVG(marks) > (select avg(marks) from student);

SELECT city FROM student WHERE grade = "A" GROUP BY city HAVING MAX(marks) > 50;

/*
HAVING PART 1:48:00
SELECT * FROM student having MARKS > 50;
SELECT columns FROM table WHERE condition GROUP BY column HAVING condition ;
*/
use collg;
SELECT * FROM student WHERE marks > (SELECT AVG(marks) FROM student);

/*
SET SQL_SAFE_UPDATES = 0;application
*/
SET SQL_SAFE_UPDATES = 0;
SELECT * FROM student;
UPDATE student SET grade = "A" WHERE grade = "O";
/*DELETE FROM table WHERE condition*/

/*FOREGEN KEY AND CASTCADING*/
CREATE TABLE sub(
	id INT PRIMARY KEY,
    nm VARCHAR(10)
);
INSERT INTO `collg`.`sub` (`id`, `nm`) VALUES ('101', 'ENGLISH');
INSERT INTO `collg`.`sub` (`id`, `nm`) VALUES ('102', 'MATH');
SELECT * FROM sub;

CREATE TABLE teacher(
	id INT PRIMARY KEY,
    nm VARCHAR(30),
    dept_id INT,
    FOREIGN KEY(dept_id) REFERENCES sub(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);
INSERT INTO `collg`.`teacher` (`id`, `nm`, `dept_id`) VALUES ('101', 'DEMAN', '101');
INSERT INTO `collg`.`teacher` (`id`, `nm`, `dept_id`) VALUES ('102', 'HARI', '102');
INSERT INTO `collg`.`teacher` (`id`, `nm`, `dept_id`) VALUES ('103', 'SELU', '101');
SELECT * FROM TEACHER;

/*
*****ALTER*****
1>ADD COLUMN-->ALTER TABLE table_name ADD COLUMN coL_mn dataType constrains;
2>DROP COLUMN-->ALTER TABLE table_name DROP COLUMN coL_mn ;
3>RENAME TABLE-->ALTER TABLE table_name RENAME TO new_table_name;
4>CHANGE COLUMN-->ALTER TABLE table_nm CHANGE COLUMN old_nm new_nm new_dataType new_constrain; 
5>MODIFY COLUMN-->ALTER TABLE table_nm MODIFY colunm_nm new_dataType new_constrain; 
*/

/*TRUNCATE TABLE student;--->DELETE THE STUDENT TABLE DATA NOT THE STUDENT TABLE*/

CREATE TABLE `collg`.`stu1` (
  `st_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`st_id`)
  );
INSERT INTO `collg`.`stu1` (`st_id`, `name`) VALUES ('101', 'som');
INSERT INTO `collg`.`stu1` (`st_id`, `name`) VALUES ('102', 'puja');
INSERT INTO `collg`.`stu1` (`st_id`, `name`) VALUES ('103', 'jpy');
INSERT INTO `collg`.`stu1` (`st_id`, `name`) VALUES ('104', 'bob');
SELECT * FROM collg.stu1;

CREATE TABLE `collg`.`cors1` (
  `c_id` INT NOT NULL,
  `nm` VARCHAR(45) NULL,
  PRIMARY KEY (`c_id`)
  );
INSERT INTO `collg`.`cors1` (`c_id`, `nm`) VALUES ('103', 'eng');
INSERT INTO `collg`.`cors1` (`c_id`, `nm`) VALUES ('104', 'bng');
INSERT INTO `collg`.`cors1` (`c_id`, `nm`) VALUES ('105', 'geo');
SELECT * FROM collg.cors1;
/*
		JOIN
SELECT *(column)
FROM table1
INNER/LEFT/RIGHT  JOIN table2 
ON table1.column = table2.column;

full join-->
SELECT *(column)
FROM table1
LEFT JOIN table2 
ON table1.column = table2.column
UNION
SELECT *(column)
FROM table1
RIGHT  JOIN table2 
ON table1.column = table2.column;


--->JOIN TWO DABLE DATA;
*/

SELECT * FROM stu1 AS X INNER JOIN cors1 AS Y ON X.st_id = Y.c_id;
SELECT * FROM stu1 AS X LEFT JOIN cors1 AS Y ON X.st_id = Y.c_id;
SELECT * FROM stu1 AS X RIGHT JOIN cors1 AS Y ON X.st_id = Y.c_id;
SELECT * FROM stu1 AS X LEFT JOIN cors1 AS Y ON X.st_id = Y.c_id UNION SELECT * FROM stu1 AS X RIGHT JOIN cors1 AS Y ON X.st_id = Y.c_id;

/*left exclusive join*/
SELECT * FROM stu1 AS X LEFT JOIN cors1 AS Y ON X.st_id = Y.c_id WHERE Y.c_id IS null;

/*self join*/
CREATE TABLE `collg`.`emp` (
  `id` INT NOT NULL,
  `nm` VARCHAR(45) NULL,
  `m_id` VARCHAR(45) NULL,
  PRIMARY KEY (`id`)
);
INSERT INTO `collg`.`emp` (`id`, `nm`) VALUES ('101', 'som');
INSERT INTO `collg`.`emp` (`id`, `nm`, `m_id`) VALUES ('102', 'kush', '103');
INSERT INTO `collg`.`emp` (`id`, `nm`, `m_id`) VALUES ('103', 'tuku', '101');
INSERT INTO `collg`.`emp` (`id`, `nm`, `m_id`) VALUES ('105', 'puja', '102');
SELECT x.nm AS maneger,y.nm AS employee FROM emp AS X JOIN emp AS Y ON X.id = Y.m_id;/*-->self join*/

/*VARTUAL TABLR OR VIEW*/
CREATE VIEW v1 AS SELECT rollno,name,marks FROM student;
SELECT * FROM v1;
DROP VIEW v1; 

/************ STRING FUNCTION *********************/

SELECT UPPER(name) FROM student;
SELECT LOWER(name) FROM student;
SELECT LENGTH(name) FROM student;
SELECT CONCAT(name, ' from ', city) FROM student;
SELECT SUBSTRING(name, 1, 3) FROM student;
SELECT TRIM(name) FROM student;
SELECT LTRIM(name) FROM student;
SELECT RTRIM(name) FROM student;
SELECT REPLACE(name, 'A', 'x') FROM student;
SELECT INSTR(name, 'a') FROM student;
SELECT POSITION('a' IN name) FROM student;
SELECT LPAD(name, 10, '*') FROM student;
SELECT RPAD(name, 10, '*') FROM student;
SELECT LEFT(name, 2) FROM student;
SELECT RIGHT(name, 2) FROM student;
SELECT ASCII(name) FROM student;
SELECT CHAR(65);
SELECT REVERSE(name) FROM student;

UPDATE table_name 
	SET column_name = 
	CONCAT(UPPER(SUBSTRING(column_name, 1, 1)), LOWER(SUBSTRING(column_name, 2))) 
	WHERE column_name IS NOT NULL;

/**************************************************/

/************ DATE AND TIME *********************/

SELECT CURRENT_DATE;
SELECT CURRENT_TIME;
SELECT CURRENT_TIMESTAMP;
SELECT NOW();         -- MySQL / PostgreSQL
SELECT SYSDATE();     -- MySQL / Oracle

-- 🔥 2. Extract Parts from Date
SELECT EXTRACT(YEAR FROM CURRENT_DATE);
SELECT EXTRACT(MONTH FROM CURRENT_DATE);
SELECT EXTRACT(DAY FROM CURRENT_DATE);
SELECT YEAR(CURRENT_DATE);     -- MySQL
SELECT MONTH(CURRENT_DATE);    -- MySQL
SELECT DAY(CURRENT_DATE);      -- MySQL

-- 🔥 3. Date Arithmetic
SELECT CURRENT_DATE + INTERVAL 1 DAY;
SELECT CURRENT_DATE - INTERVAL 1 DAY;
SELECT DATE_ADD(CURRENT_DATE, INTERVAL 5 DAY);
SELECT DATE_SUB(CURRENT_DATE, INTERVAL 5 DAY);

-- 🔥 4. Difference Between Dates
SELECT DATEDIFF('2025-12-31', '2025-01-01');
SELECT TIMESTAMPDIFF(DAY, '2025-01-01', '2025-12-31');

-- 🔥 5. Formatting Dates
SELECT DATE_FORMAT(CURRENT_DATE, '%d-%m-%Y');   -- MySQL
SELECT TO_CHAR(CURRENT_DATE, 'DD-MM-YYYY');     -- PostgreSQL / Oracle

-- 🔥 6. Convert String ↔ Date
SELECT STR_TO_DATE('03-04-2026', '%d-%m-%Y');   -- MySQL
SELECT TO_DATE('03-04-2026', 'DD-MM-YYYY');     -- PostgreSQL / Oracle

-- 🔥 7. Day & Month Names
SELECT DAYNAME(CURRENT_DATE);   -- MySQL
SELECT MONTHNAME(CURRENT_DATE); -- MySQL

-- 🔥 8. Last / First Day Functions
SELECT LAST_DAY(CURRENT_DATE);
SELECT DATE_TRUNC('month', CURRENT_DATE);   -- PostgreSQL

-- 🔥 9. Time Functions
SELECT HOUR(CURRENT_TIME);
SELECT MINUTE(CURRENT_TIME);
SELECT SECOND(CURRENT_TIME);

-- 🔥 10. Unix Timestamp
SELECT UNIX_TIMESTAMP();     -- MySQL
SELECT FROM_UNIXTIME(1712120000);  -- MySQL

/******************************************************************************/



```