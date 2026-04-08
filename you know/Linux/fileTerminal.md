# **Linux Basic Commands**

# Table of Contents
- [<- Back to Home](README.md)
- [Topic 1](#topic-1)
  - [ls – List Files](#1-ls--list-files-and-directories)
  - [cd – Change Directory](#2-cd--change-directory)
  - [pwd – Print Working Directory](#3-pwd--print-working-directory)
  - [mkdir – Create Directory](#4-mkdir--create-directory)
  - [Delete Files](#5-delete-files-and-directories)
  - [cat – Display File Content](#6-cat--display-file-content)
  - [zcat – View Compressed Files](#7-zcat--view-compressed-files)
  - [touch – Create File](#8-touch--create-empty-file)
  - [head – First Lines](#9-head--show-first-lines-of-file)
  - [tail – Last Lines](#10-tail--show-last-lines-of-file)
  - [less – View Large Files](#11-less--view-large-files-page-by-page)
  - [more – View File Page by Page](#12-more--view-file-page-by-page)
- [Topic 2](#topic-2)
  - [cp – Copy Files](#1-cp--copy-files-and-directories)
  - [mv – Move or Rename Files](#2-mv--move-or-rename-files)
  - [wc – Word Count](#3-wc--word-count)
  - [vi Editor](#4-vi-editor-in-unixlinux)
  - [Hard Link and Soft Link](#5-links-in-linux)
  - [cut – Extract Text Columns](#6-cut--extract-columns-from-text)
  - [tee – Output to File and Screen](#7-tee--output-to-file-and-screen)
  - [sort – Sort Lines](#8-sort--sort-text-lines)
  - [clear – Clear Terminal](#9-clear--clear-terminal-screen)
  - [diff – Compare Files](#10-diff--compare-files)

---
# Topic 1

## 1. `ls` – List Files and Directories

**Purpose:** Shows the files and folders in the current directory.


**Example**

```bash
ls
```

Output:

```
file1.txt  file2.txt  folder1
```

**Common Options**

```bash
ls -l   # detailed list
ls -a   # show hidden files
ls -lh  # human readable size
```
**Example**

```bash
ls -l
```

Output:

```bash
OwnGruOth   links ownr grup size  lastModTim  Name
drwxr-xr-x    3   user user 4096 Mar 11 21:00 .
drwxr-xr-x   18   user user 4096 Mar 11 20:55 ..
drwxr-xr-x   18   user user 4096 Mar 11 20:55 folder       # d -> directory
-rw-r--r--    1   user user  220 Mar 10 10:10 .bash_logout   #hidden file
-rw-r--r--    1   user user 3771 Mar 10 10:10 .bashrc
-rw-r--r--    1   user user  807 Mar 10 10:10 .profile
-rw-r--r--    1   user user 1024 Mar 11 20:50 file1.txt
```
---

## 2. `cd` – Change Directory

**Purpose:** Move from one directory to another.

**Syntax**

```bash
cd directory_name
```

**Example**

```bash
cd Documents
```

**Useful Shortcuts**

```bash
cd ..    # go to parent directory
cd ~     # go to home directory
cd /     # go to root directory
```

---

## 3. `pwd` – Print Working Directory

**Purpose:** Shows the current directory path.

**Example**

```bash
pwd
```

Output:

```
/home/user/Documents
```

---

## 4. `mkdir` – Create Directory

**Purpose:** Creates a new folder.

**Syntax**

```bash
mkdir folder_name
```

**Example**

```bash
mkdir project
```

Create multiple folders:

```bash
mkdir dir1 dir2 dir3
```

---

## 5. Delete Files and Directories

### `rm` – Remove Files

```bash
rm file.txt
```

Delete multiple files:

```bash
rm file1.txt file2.txt
```

Delete directory recursively:

```bash
rm -r folder_name
```

Force delete:

```bash
rm -rf folder_name
```

---

### `rmdir` – Remove Empty Directory

```bash
rmdir folder_name
```

⚠ Works **only if directory is empty**.

---

## 6. `cat` – Display File Content

**Purpose:** Shows the content of a file.

```bash
cat file.txt
```

Combine files:

```bash
cat file1.txt file2.txt
```

Create file:

```bash
cat > file.txt
```

echo print on terminal also create file

```bash
echo "this is my file" > myfile.txt
```

---

## 7. `zcat` – View Compressed Files

Used to display `.gz` compressed files without extracting.

```bash
zcat file.txt.gz
```

---

## 8. `touch` – Create Empty File

Creates a new empty file.

```bash
touch file.txt
```

Create multiple files:

```bash
touch file1.txt file2.txt
```

---

## 9. `head` – Show First Lines of File

Shows the **first 10 lines** by default.

```bash
head file.txt
```

Specify number of lines:

```bash
head -n 5 file.txt
```

---

## 10. `tail` – Show Last Lines of File

Shows the **last 10 lines** by default.

```bash
tail file.txt
```

Show specific lines:

```bash
tail -n 5 file.txt
```

---

### `tail -f` – Live File Monitoring

Used to **monitor logs in real time**.

```bash
tail -f logfile.log
```

Very useful for **server logs**.

---

## 11. `less` – View Large Files Page by Page

Allows scrolling through file content.

```bash
less file.txt
```

Controls:

* `Space` → Next page
* `b` → Previous page
* `q` → Quit

---

## 12. `more` – View File Page by Page

Similar to `less` but simpler.

```bash
more file.txt
```

Difference:

* `more` → only forward
* `less` → forward and backward

---

✅ **Summary Table**

| Command | Purpose                |
| ------- | ---------------------- |
| ls      | List files             |
| cd      | Change directory       |
| pwd     | Show current directory |
| mkdir   | Create directory       |
| rm      | Delete file/directory  |
| rmdir   | Delete empty directory |
| cat     | Show file content      |
| zcat    | View compressed file   |
| touch   | Create file            |
| head    | First lines of file    |
| tail    | Last lines of file     |
| tail -f | Live log monitoring    |
| less    | View file page by page |
| more    | Simple file viewer     |

---


# Topic 2

## 1. `cp` – Copy Files and Directories

**Purpose:** Copies files or directories from one location to another.

**Syntax**

```bash
cp source destination
````

**Example**

```bash
cp file1.txt file2.txt
```
copy in another directory:

```bash
cp file1.txt folder2/
```

Copy directory:

```bash
cp -r folder1 folder2
```

Common options:

```bash
cp -i   # ask before overwrite
cp -r   # copy directories
cp -v   # show progress
```

---

## 2. `mv` – Move or Rename Files

**Purpose:** Moves files or renames them.

**Syntax**

```bash
mv source destination
```

**Example**

Rename file:

```bash
mv old.txt new.txt
```

Move file:

```bash
mv file.txt /home/user/Documents
```

---

## 3. `wc` – Word Count

**Purpose:** Counts lines, words, and characters in a file.

**Example**

```bash
wc file.txt
```

Output example:

```
10  50  300 file.txt
```

Meaning:

* 10 → lines
* 50 → words
* 300 → characters

Options:

```bash
wc -l file.txt   # count lines
wc -w file.txt   # count words
wc -c file.txt   # count characters
```

---

## 4. `vi` Editor in Unix/Linux

**Purpose:** Text editor used in Unix/Linux systems.

Open a file:

```bash
vi file.txt
```

Modes in `vi`

1. **Command Mode**

   * Default mode
   * Used for navigation and commands

2. **Insert Mode**

   * Used to edit text
   * Press `i` to enter

3. **Last Line Mode**

   * Used to save or quit
   * Press `:` to enter

Common commands:

```bash
i     # insert mode
Esc   # return to command mode
:w    # save
:q    # quit
:wq   # save and quit
:q!   # quit without saving
```

---

## 5. Links in Linux

```bash
ln source desrination
ln sourcePath+fileName desrinationPath+fileName
```

### Hard Link

Creates another name for the same file.

```bash
ln file1.txt hardlink.txt
```

Characteristics:

* Same inode number
* Cannot link directories
* Works only within same filesystem
* Hard link does not delete when source is deleted

---

### Soft Link (Symbolic Link)

Acts like a shortcut to another file.

```bash
ln -s file1.txt softlink.txt
```

Characteristics:

* Different inode
* Can link directories
* Works across filesystems
* Soft link deletes when source is deleted


---

## 6. `cut` – Extract Columns from Text

**Purpose:** Extracts specific parts of text from a file.

Example file:

```
name,age,city
John,25,London
```

Command:

```bash
cut -d "," -f1 file.txt
```

Output:

```
name
John
```

Options:

```bash
-b  # bite number
-d  # delimiter
-f  # field number
```

---

## 7. `tee` – Output to File and Screen

**Purpose:** Saves output to a file while displaying it.

Example:

```bash
ls | tee output.txt
```

Append output:

```bash
ls | tee -a output.txt
```

---

## 8. `sort` – Sort Text Lines

**Purpose:** Sorts lines alphabetically or numerically.

Example:

```bash
sort file.txt
```

Options:

```bash
sort -r file.txt   # reverse order
sort -n file.txt   # numeric sort
```

---

## 9. `clear` – Clear Terminal Screen

Clears all text from the terminal.

```bash
clear
```

Shortcut:

```
Ctrl + L
```

---

## 10. `diff` – Compare Files

**Purpose:** Shows differences between two files.

Example:

```bash
diff file1.txt file2.txt
```

Output shows lines that are different.

Common option:

```bash
diff -u file1.txt file2.txt
```

Used for **patches and code comparison**.

---

✅ **Summary Table**

| Command | Purpose                        |
| ------- | ------------------------------ |
| cp      | Copy files                     |
| mv      | Move or rename files           |
| wc      | Count words, lines, characters |
| vi      | Text editor                    |
| ln      | Create links                   |
| cut     | Extract columns                |
| tee     | Save and display output        |
| sort    | Sort text                      |
| clear   | Clear terminal                 |
| diff    | Compare files                  |

```
 
