Below is a **README.md style explanation with Table of Contents and deep explanation** for `grep`, `sed`, and `awk`.

---

# Linux Text Processing Commands

These commands are widely used in **Linux, DevOps, and Shell scripting** for processing text files and logs.

Main commands:

* `grep` → search text patterns
* `sed` → stream editor for editing text
* `awk` → powerful text processing and reporting tool

---
[<- Back to Home](README.md)
# Table of Contents

* [grep Command](#grep-command)

  * [Syntax](#grep-syntax)
  * [Common Options](#grep-common-options)
  * [Examples](#grep-examples)

* [sed Command](#sed-command)

  * [Syntax](#sed-syntax)
  * [Common Operations](#sed-common-operations)
  * [Examples](#sed-examples)

* [awk Command](#awk-command)

  * [Syntax](#awk-syntax)
  * [Fields and Variables](#awk-fields-and-variables)
  * [Examples](#awk-examples)

---

# grep Command

`grep` stands for:

**G**lobal **R**egular **E**xpression **P**rint

It searches for **patterns inside files** and prints matching lines.

Example uses:

* searching logs
* filtering output
* finding errors in log files

---

## grep Syntax

```bash
grep [options] pattern file
```

Example:

```bash
grep error logfile.txt
```

Output:

```
Error: connection failed
Error: permission denied
```

---

## grep Common Options

| Option | Description      |
| ------ | ---------------- |
| `-i`   | ignore case      |
| `-v`   | invert match     |
| `-n`   | show line number |
| `-r`   | recursive search |
| `-l`   | show filenames   |
| `-c`   | count matches    |
| `-w`   | match whole word |

---

## grep Examples

### Case-insensitive search

```bash
grep -i linux file.txt
```

Matches:

```
Linux
LINUX
linux
```

---

### Show line numbers

```bash
grep -n error log.txt
```

Output:

```
12:error occurred
45:error connecting database
```

---

### Search recursively

```bash
grep -r "password" /etc
```

Searches **inside all files in `/etc` directory**.

---

### Count matches

```bash
grep -c "error" logfile.txt
```

Output:

```
15
```

---

### Invert match

```bash
grep -v "error" logfile.txt
```

Shows lines **not containing "error"**.

---

# sed Command

`sed` stands for:

**S**tream **Ed**itor

Used to **modify text in a stream** (files, pipes, output).

It can:

* replace text
* delete lines
* insert text
* transform content

---

## sed Syntax

```bash
sed 'command' file
```

Example:

```bash
sed 's/linux/unix/' file.txt
```

---

## sed Common Operations

| Operation | Description  |
| --------- | ------------ |
| `s`       | substitute   |
| `d`       | delete line  |
| `p`       | print        |
| `a`       | append text  |
| `i`       | insert text  |
| `c`       | replace line |

---

## sed Examples

### Replace text

```bash
sed 's/linux/unix/' file.txt
```

Only **first occurrence** per line replaced.

---

### Replace globally

```bash
sed 's/linux/unix/g' file.txt
```

`g` = global replacement.

---

### Delete line

Delete line 3

```bash
sed '3d' file.txt
```

---

### Delete blank lines

```bash
sed '/^$/d' file.txt
```

---

### Print specific lines

```bash
sed -n '1,5p' file.txt
```

Print **first 5 lines**.

---

### Edit file directly

```bash
sed -i 's/linux/unix/g' file.txt
```

`-i` = edit file **in place**

---

# awk Command

`awk` is a **powerful programming language for text processing**.

It works well with:

* structured text
* columns
* logs
* CSV files

Created by:

* Alfred Aho
* Peter Weinberger
* Brian Kernighan

---

## awk Syntax

```bash
awk 'pattern { action }' file
```

Example:

```bash
awk '{print $1}' file.txt
```

Prints **first column**.

---

## awk Fields and Variables

AWK splits each line into **fields**.

Default separator = **space**

Example line:

```
John 25 Developer
```

| Field | Value     |
| ----- | --------- |
| $1    | John      |
| $2    | 25        |
| $3    | Developer |

Special variables:

| Variable | Meaning          |
| -------- | ---------------- |
| `$0`     | entire line      |
| `$1`     | first field      |
| `$2`     | second field     |
| `NR`     | record number    |
| `NF`     | number of fields |

---

## awk Examples

### Print first column

```bash
awk '{print $1}' file.txt
```

Output:

```
John
Alice
Mike
```

---

### Print multiple columns

```bash
awk '{print $1,$3}' file.txt
```

---

### Print lines matching pattern

```bash
awk '/error/ {print}' log.txt
```

---

### Print line numbers

```bash
awk '{print NR,$0}' file.txt
```

Example output:

```
1 John 25
2 Alice 30
```

---

### Count lines

```bash
awk 'END {print NR}' file.txt
```

---

### Sum column values

Example file:

```
apple 10
banana 20
mango 15
```

Command:

```bash
awk '{sum += $2} END {print sum}' file.txt
```

Output:

```
45
```

---

# grep vs sed vs awk

| Command | Use                    |
| ------- | ---------------------- |
| `grep`  | search text            |
| `sed`   | edit/replace text      |
| `awk`   | process columns & data |

Example pipeline:

```bash
cat logfile.txt | grep error | awk '{print $2}' | sort
```

Flow:

```
file → grep filter → awk extract → sort result
```

---

✅ These three commands are **core tools for Linux administrators, DevOps engineers, and shell scripting**.

---

If you want, I can also show you **15 advanced `grep`, `sed`, and `awk` tricks used in DevOps interviews and real production servers.**
