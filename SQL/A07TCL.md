## 🔄 TCL (Transaction Control Language) in SQL — Deep & Detailed Explanation

**TCL** controls **transactions** in a database.

👉 A **transaction** = a group of SQL operations treated as **one unit of work**.

Example:

```sql
UPDATE accounts SET balance = balance - 1000 WHERE id = 1;
UPDATE accounts SET balance = balance + 1000 WHERE id = 2;
```

👉 This is **one transaction (money transfer)**

---

# 🔹 What is TCL?

TCL ensures:

* **Data consistency**
* **Error recovery**
* **Safe execution of multiple queries**

---

# 🔹 TCL Commands 🧠

| Command         | Purpose                      |
| --------------- | ---------------------------- |
| COMMIT          | Save changes permanently     |
| ROLLBACK        | Undo changes                 |
| SAVEPOINT       | Create checkpoints           |
| SET TRANSACTION | Control transaction behavior |

---

# 🔥 1. COMMIT (Save Changes) ✅

### 📌 Definition

`COMMIT` permanently saves all changes in the transaction.

---

### 📌 Syntax

```sql
COMMIT;
```

---

### 📌 Example

```sql
UPDATE accounts SET balance = balance - 1000 WHERE id = 1;
UPDATE accounts SET balance = balance + 1000 WHERE id = 2;

COMMIT;
```

👉 Changes are **permanent**
👉 Cannot undo after commit ❗

---

# 🔥 2. ROLLBACK (Undo Changes) ❌

### 📌 Definition

`ROLLBACK` reverses all changes since the last commit.

---

### 📌 Syntax

```sql
ROLLBACK;
```

---

### 📌 Example

```sql
UPDATE accounts SET balance = balance - 1000 WHERE id = 1;

ROLLBACK;
```

👉 Balance goes back to original value.

---

# 🔥 3. SAVEPOINT (Checkpoint) 🎯

### 📌 Definition

Creates a **temporary checkpoint** inside a transaction.

---

### 📌 Syntax

```sql
SAVEPOINT savepoint_name;
```

---

### 📌 Example

```sql
UPDATE accounts SET balance = balance - 1000 WHERE id = 1;

SAVEPOINT sp1;

UPDATE accounts SET balance = balance + 1000 WHERE id = 2;

ROLLBACK TO sp1;
```

👉 First update stays
👉 Second update is undone

---

# 🔥 4. SET TRANSACTION ⚙️

### 📌 Definition

Controls transaction properties like **read/write behavior**.

---

### 📌 Syntax

```sql
SET TRANSACTION READ ONLY;
```

or

```sql
SET TRANSACTION READ WRITE;
```

---

### 📌 Example

```sql
SET TRANSACTION READ ONLY;
SELECT * FROM students;
```

👉 You **cannot modify data**

---

# 🔹 Transaction States (Very Important) 🚀

1. **Active** → Queries are running
2. **Partially Committed** → Last query executed
3. **Committed** → Changes saved
4. **Failed** → Error occurred
5. **Aborted** → Rolled back

---

# 🔹 ACID Properties (Core Concept) 💡

TCL is based on **ACID properties**:

| Property    | Meaning                        |
| ----------- | ------------------------------ |
| Atomicity   | All or nothing                 |
| Consistency | Valid data only                |
| Isolation   | Transactions don’t interfere   |
| Durability  | Data is permanent after commit |

---

## 🔸 Atomicity (Most Important)

👉 If one query fails → entire transaction fails

---

# 🔹 Real-Life Example 💰

Bank transfer:

```sql
BEGIN;

UPDATE accounts SET balance = balance - 500 WHERE id = 1;

UPDATE accounts SET balance = balance + 500 WHERE id = 2;

COMMIT;
```

👉 If system crashes before commit:

```sql
ROLLBACK;
```

👉 No money is lost ❗

---

# 🔹 COMMIT vs ROLLBACK

| Feature  | COMMIT       | ROLLBACK     |
| -------- | ------------ | ------------ |
| Purpose  | Save changes | Undo changes |
| Effect   | Permanent    | Temporary    |
| Recovery | Not possible | Possible     |

---

# 🔹 SAVEPOINT vs ROLLBACK

| Feature | SAVEPOINT    | ROLLBACK     |
| ------- | ------------ | ------------ |
| Scope   | Partial undo | Full undo    |
| Control | More control | Less control |

---

# 🔥 Important Interview Points 🚀

* TCL manages **transactions**
* `COMMIT` → permanent save
* `ROLLBACK` → undo changes
* `SAVEPOINT` → partial rollback
* Works with **ACID properties**

---

# 🔥 Perfect Interview Answer 🎯

👉 *“TCL (Transaction Control Language) is used to manage transactions in a database. It includes COMMIT, ROLLBACK, SAVEPOINT, and SET TRANSACTION. COMMIT saves changes permanently, ROLLBACK undoes changes, and SAVEPOINT allows partial rollback. TCL ensures data integrity using ACID properties.”*

---

If you want next:
✅ I can explain **ACID properties deeply with diagrams**
✅ Or give **TCS exam-level tricky questions on TCL** 🚀
