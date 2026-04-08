## ⚡ SQL TRIGGER — Detailed & Deep Explanation

A **Trigger** is a special type of stored procedure that **automatically executes (fires)** when a specific event happens in a database.

👉 You **don’t call triggers manually** — they run **automatically**.

---

## 🔹 What is a Trigger?

👉 A **trigger** is linked to a table and is executed when:

* `INSERT` (new data added)
* `UPDATE` (data modified)
* `DELETE` (data removed)

---

## 🔹 How Trigger Works (Concept)

👉 Flow:

1. Event occurs (INSERT/UPDATE/DELETE)
2. Trigger fires automatically
3. Executes predefined logic

---

# 🔥 Types of Triggers

## 🔹 1. BEFORE Trigger ⏱️

Executes **before** the actual operation.

### ✔ Example:

```sql
CREATE TRIGGER before_insert_student
BEFORE INSERT ON students
FOR EACH ROW
SET NEW.marks = IF(NEW.marks < 0, 0, NEW.marks);
```

👉 Ensures marks are never negative before inserting.

---

## 🔹 2. AFTER Trigger ✅

Executes **after** the operation is completed.

### ✔ Example:

```sql
CREATE TRIGGER after_insert_student
AFTER INSERT ON students
FOR EACH ROW
INSERT INTO log_table VALUES ('New student added');
```

👉 Used for logging, auditing, etc.

---

## 🔹 3. INSTEAD OF Trigger 🔁

Used mainly in **views**.

👉 Replaces the actual operation.

---

# 🔥 Trigger Events

| Event  | Description               |
| ------ | ------------------------- |
| INSERT | When new row is added     |
| UPDATE | When existing row changes |
| DELETE | When row is removed       |

---

# 🔹 Row-Level vs Statement-Level

## 🔸 Row-Level Trigger

```sql
FOR EACH ROW
```

👉 Executes for **every row affected**

---

## 🔸 Statement-Level Trigger

👉 Executes **once per query**, not per row

---

# 🔹 OLD and NEW Keywords 🧠

| Keyword | Meaning                 |
| ------- | ----------------------- |
| OLD     | Old value before change |
| NEW     | New value after change  |

---

### ✔ Example:

```sql
CREATE TRIGGER update_salary
BEFORE UPDATE ON employees
FOR EACH ROW
SET NEW.salary = OLD.salary + 1000;
```

👉 Increases salary automatically

---

# 🔹 Real-Life Example 💡

## 🎓 Student System

```sql
CREATE TRIGGER prevent_delete
BEFORE DELETE ON students
FOR EACH ROW
SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = 'Delete not allowed!';
```

👉 Prevents deletion of records

---

# 🔹 Advantages of Triggers ✅

* Automatic execution
* Enforces business rules
* Maintains data integrity
* Useful for auditing/logging

---

# 🔹 Disadvantages ❌

* Hard to debug
* Hidden logic (not visible in main queries)
* Can reduce performance if overused

---

# 🔹 Trigger vs Stored Procedure

| Feature     | Trigger        | Stored Procedure |
| ----------- | -------------- | ---------------- |
| Execution   | Automatic      | Manual           |
| Event-based | Yes            | No               |
| Usage       | Data integrity | General tasks    |

---

# 🔹 Important Interview Points 🚀

* Trigger = **automatic execution**
* Works on **INSERT, UPDATE, DELETE**
* Types: BEFORE, AFTER, INSTEAD OF
* Uses **OLD & NEW values**
* Cannot be called manually

---

# 🔥 Perfect Interview Answer 🎯

👉 *“A trigger is a database object that automatically executes when a specific event like INSERT, UPDATE, or DELETE occurs on a table. It is used to enforce business rules, maintain data integrity, and perform automatic actions. Triggers can be BEFORE, AFTER, or INSTEAD OF, depending on when they execute.”*

---

If you want next:
✅ I can give **complex trigger interview questions (TCS level)**
✅ Or help you **write real project triggers step-by-step** 🚀
