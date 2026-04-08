SQL is a big topic, but I’ll give you a **complete, structured, and deep guide** to *all important SQL commands*—the way it’s usually taught in interviews + real-world use.

---

# 🧠 1. Types of SQL Commands

SQL commands are divided into 5 main categories:

| Type | Full Form                    | Purpose                          |
| ---- | ---------------------------- | -------------------------------- |
| DDL  | Data Definition Language     | Structure (tables, schema)       |
| DML  | Data Manipulation Language   | Data operations (insert, update) |
| DQL  | Data Query Language          | Fetch data                       |
| DCL  | Data Control Language        | Permissions                      |
| TCL  | Transaction Control Language | Transactions                     |

---

# 🏗️ 2. DDL (Data Definition Language)

These commands define database structure.

## 🔹 CREATE

Create database or table.

```sql
CREATE DATABASE college;

CREATE TABLE students (
    id INT PRIMARY KEY,
    name VARCHAR(50),
    age INT
);
```

---

## 🔹 ALTER

Modify table structure.

```sql
ALTER TABLE students ADD email VARCHAR(100);

ALTER TABLE students MODIFY age INT NOT NULL;

ALTER TABLE students DROP COLUMN email;
```

---

## 🔹 DROP

Delete table or database permanently.

```sql
DROP TABLE students;
DROP DATABASE college;
```

---

## 🔹 TRUNCATE

Delete all data but keep structure.

```sql
TRUNCATE TABLE students;
```

👉 Faster than DELETE, cannot rollback (in most DBs).

---

## 🔹 RENAME

Rename table.

```sql
ALTER TABLE students RENAME TO learners;
```

---

# ✏️ 3. DML (Data Manipulation Language)

Used to manipulate data.

---

## 🔹 INSERT

Add data.

```sql
INSERT INTO students (id, name, age)
VALUES (1, 'Som', 22);
```

Multiple insert:

```sql
INSERT INTO students VALUES
(2, 'Rahul', 21),
(3, 'Ankit', 23);
```

---

## 🔹 UPDATE

Modify existing data.

```sql
UPDATE students
SET age = 23
WHERE id = 1;
```

⚠️ Without WHERE → updates all rows.

---

## 🔹 DELETE

Delete rows.

```sql
DELETE FROM students WHERE id = 1;
```

---

# 🔍 4. DQL (Data Query Language)

## 🔹 SELECT (MOST IMPORTANT)

```sql
SELECT * FROM students;

SELECT name, age FROM students;
```

---

## 🔹 WHERE (Filtering)

```sql
SELECT * FROM students WHERE age > 21;
```

Operators:

* `=`, `!=`, `>`, `<`
* `BETWEEN`
* `IN`
* `LIKE`

```sql
SELECT * FROM students WHERE name LIKE 'S%';
```

---

## 🔹 ORDER BY

```sql
SELECT * FROM students ORDER BY age DESC;
```

---

## 🔹 GROUP BY

```sql
SELECT age, COUNT(*) 
FROM students 
GROUP BY age;
```

---

## 🔹 HAVING

```sql
SELECT age, COUNT(*) 
FROM students 
GROUP BY age
HAVING COUNT(*) > 1;
```

---

## 🔹 DISTINCT

```sql
SELECT DISTINCT age FROM students;
```

---

# 🔗 5. JOINS (Very Important)

## 🔹 INNER JOIN

Only matching records.

```sql
SELECT p.name, o.order_id
FROM customers p
INNER JOIN orders o
ON p.id = o.customer_id;
```

---

## 🔹 LEFT JOIN

```sql
SELECT *
FROM customers c
LEFT JOIN orders o
ON c.id = o.customer_id;
```

👉 All from left + matching right

---

## 🔹 RIGHT JOIN

```sql
SELECT *
FROM customers c
RIGHT JOIN orders o
ON c.id = o.customer_id;
```

---

## 🔹 FULL JOIN

```sql
SELECT *
FROM customers c
FULL OUTER JOIN orders o
ON c.id = o.customer_id;
```

---

# ⚙️ 6. FUNCTIONS

## 🔹 Aggregate Functions

```sql
SELECT COUNT(*) FROM students;
SELECT AVG(age) FROM students;
SELECT MAX(age), MIN(age) FROM students;
SELECT SUM(age) FROM students;
```

---

## 🔹 String Functions

```sql
SELECT UPPER(name), LOWER(name) FROM students;
SELECT LENGTH(name) FROM students;
```

---

## 🔹 Date Functions

```sql
SELECT NOW();
SELECT CURDATE();
```

---

# 🧩 7. CONSTRAINTS

Used in table creation.

```sql
CREATE TABLE students (
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    age INT CHECK (age > 0),
    email VARCHAR(100) UNIQUE
);
```

Types:

* PRIMARY KEY
* FOREIGN KEY
* UNIQUE
* NOT NULL
* CHECK
* DEFAULT

---

## 🔹 FOREIGN KEY

```sql
CREATE TABLE orders (
    order_id INT,
    student_id INT,
    FOREIGN KEY (student_id) REFERENCES students(id)
);
```

---

# 🔐 8. DCL (Data Control Language)

## 🔹 GRANT

```sql
GRANT SELECT, INSERT ON students TO user1;
```

---

## 🔹 REVOKE

```sql
REVOKE INSERT ON students FROM user1;
```

---

# 🔄 9. TCL (Transaction Control Language)

## 🔹 COMMIT

```sql
COMMIT;
```

---

## 🔹 ROLLBACK

```sql
ROLLBACK;
```

---

## 🔹 SAVEPOINT

```sql
SAVEPOINT sp1;
ROLLBACK TO sp1;
```

---

# 🧠 10. ADVANCED SQL

---

## 🔹 SUBQUERY

```sql
SELECT name 
FROM students 
WHERE age > (SELECT AVG(age) FROM students);
```

---

## 🔹 VIEW

```sql
CREATE VIEW student_view AS
SELECT name, age FROM students;
```

---

## 🔹 INDEX

```sql
CREATE INDEX idx_name ON students(name);
```

---

## 🔹 WINDOW FUNCTION

```sql
SELECT name, RANK() OVER (ORDER BY age DESC)
FROM students;
```

---

## 🔹 CASE

```sql
SELECT name,
CASE 
    WHEN age > 22 THEN 'Senior'
    ELSE 'Junior'
END
FROM students;
```

---

# 🚀 Summary (Quick Revision)

* **DDL** → CREATE, ALTER, DROP
* **DML** → INSERT, UPDATE, DELETE
* **DQL** → SELECT
* **DCL** → GRANT, REVOKE
* **TCL** → COMMIT, ROLLBACK

---

# 🎯 If You Want Next Level

I can also give you:

* 🔥 100+ SQL interview questions
* 📊 Real-world DB schema (E-commerce / Hospital)
* 🧠 Advanced topics (Normalization, Indexing, Query optimization)
* 💻 Practice problems with solutions

Just tell me 👍
