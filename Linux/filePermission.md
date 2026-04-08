Below is a **deep explanation you can directly use in your Linux notes / README.md**.

---

# Linux File Permissions, Compression & File Transfer Commands

## Table of Contents
[<- Back to Home](README.md)
* [File Permission Commands](#file-permission-commands)

  * [umask](#1-umask--default-permission)
  * [ls](#2-ls--list-file-permissions)
  * [chmod](#3-chmod--change-file-permissions)
  * [chown](#4-chown--change-file-owner)
  * [chgrp](#5-chgrp--change-group-ownership)

* [Compression Commands](#compression-commands)

  * [gzip / gunzip](#6-gzip-and-gunzip)
  * [zip / unzip](#7-zip-and-unzip)
  * [tar / untar](#8-tar-command)

* [File Transfer Commands](#file-transfer-commands)

  * [scp](#9-scp-secure-copy)
  * [rsync](#10-rsync-command)

---

# File Permission Commands

Linux is a **multi-user operating system**, so files need permissions to control who can access them.

There are **3 types of permissions**

| Permission | Symbol | Value | Meaning     |
| ---------- | ------ | ------ | ----------- |
| Read       | r      | 4      | view file   |
| Write      | w      | 2      | modify file |
| Execute    | x      | 1      | run program |

And **3 user categories**

| Category   | Meaning       |
| ---------- | ------------- |
| Owner (u)  | file owner    |
| Group (g)  | group members |
| Others (o) | everyone else |

Example permission:

```
-rwxr-xr--
```

Breakdown:

| Part | Meaning            |
| ---- | ------------------ |
| -    | file type          |
| rwx  | owner permissions  |
| r-x  | group permissions  |
| r--  | others permissions |

---

# 1. `umask` – Default Permission

`umask` defines the **default permissions for newly created files and directories**.

### Syntax

```
umask
```

Example:

```
umask
```

Output:

```
0022
```

Meaning:

Default permissions are calculated from:

```
Files: 666
Directories: 777
```

Then subtract the umask value.

Example:

```
666 - 022 = 644
```

So new file permission:

```
rw-r--r--
```

Set new umask:

```
umask 027
```

---

# 2. `ls` – List File Permissions

`ls` shows file permissions and ownership.

### Syntax

```
ls -l
```

Example:

```
-rw-r--r-- 1 som developers 1200 Mar 10 file.txt
```

Explanation:

| Field      | Meaning           |
| ---------- | ----------------- |
| rw-r--r--  | permissions       |
| som        | owner             |
| developers | group             |
| 1200       | file size         |
| Mar 10     | modification date |

Useful options:

```
ls -l
ls -a
ls -lh
```

---

# 3. `chmod` – Change File Permissions

`chmod` modifies file permissions.

### Syntax

```
chmod permission file
```

Two methods:

---

## Numeric Method

| Permission | Number |
| ---------- | ------ |
| read       | 4      |
| write      | 2      |
| execute    | 1      |

Example:

```
chmod 755 script.sh
```

Seperate premission
```bash
chmod u=rwx,g=rx,o=r file.txt
```

Meaning:

| User   | Permission |
| ------ | ---------- |
| owner  | rwx        |
| group  | r-x        |
| others | r-x        |

Breakdown:

```
7 = 4+2+1
5 = 4+1
5 = 4+1
```

---

## Symbolic Method

Example:

Add execute permission:

```
chmod +x script.sh
```

Remove write permission:

```
chmod u-w file.txt
```

Add permission to group:

```
chmod g+r file.txt
```

---

# 4. `chown` – Change File Owner

`chown` changes **file owner**.

### Syntax

```
chown user file
```

Example:

```
sudo chown som file.txt
```

Change owner and group:

```
sudo chown som:developers file.txt
```

Recursive change:

```
sudo chown -R som folder
```

---

# 5. `chgrp` – Change Group Ownership

Changes the **group associated with a file**.

### Syntax

```
chgrp groupname file
```

Example:

```
sudo chgrp developers file.txt
```

Recursive change:

```
sudo chgrp -R developers project
```

---

# Compression Commands

Compression reduces **file size** to save storage and speed up transfer.

---

# 6. `gzip` and `gunzip`

### Compress file

```
gzip file.txt
```

Output:

```
file.txt.gz
```

### Decompress file

```
gunzip file.txt.gz
```

Or

```
gzip -d file.txt.gz
```

List compressed file info:

```
gzip -l file.txt.gz
```

---

# 7. `zip` and `unzip`

Used to compress multiple files.

### Create zip archive

```
zip archive.zip file1 file2
```

Compress folder:

```
zip -r archive.zip folder
```

### Extract zip

```
unzip archive.zip
```

Extract to directory:

```
unzip archive.zip -d folder
```

---

# 8. `tar` Command

`tar` is used to **archive multiple files**.

### Create tar archive

```
tar -cvf archive.tar folder
```

Options:

| Option | Meaning  |
| ------ | -------- |
| c      | create   |
| v      | verbose  |
| f      | filename |

---

### Extract tar archive

```
tar -xvf archive.tar
```

---

### Create compressed tar

```
tar -czvf archive.tar.gz folder
```

Extract compressed archive:

```
tar -xzvf archive.tar.gz
```

---

# File Transfer Commands

Used to transfer files between **remote servers and local systems**.

---

# 9. `scp` – Secure Copy

`scp` copies files over **SSH**.

### Copy file to remote server

```
scp file.txt user@server:/home/user
```

Example:

```
scp file.txt som@192.168.1.5:/home/som
```

---

### Copy from remote server

```
scp user@server:/home/user/file.txt .
```

Copy folder:

```
scp -r folder user@server:/home/user
```

---

# 10. `rsync` Command

`rsync` is an **advanced file transfer and synchronization tool**.

More efficient than `scp`.

### Syntax

```
rsync [options] source destination
```

Example:

```
rsync file.txt backup/
```

Remote sync:

```
rsync -avz file.txt user@server:/home/user
```

Remote sync with SSH:

```
rsync -e "ssh -i /path/to/linux-key.pem" -avz /path/to/local/folder/file.txt user@server:/home/user
```

Options:

| Option | Meaning     |
| ------ | ----------- |
| a      | archive     |
| v      | verbose     |
| z      | compression |

Example folder sync:

```
rsync -av folder/ backup/
```

---

# Summary Table

| Command | Purpose                        |
| ------- | ------------------------------ |
| umask   | default permission settings    |
| ls      | show file permissions          |
| chmod   | change file permissions        |
| chown   | change owner                   |
| chgrp   | change group                   |
| gzip    | compress file                  |
| gunzip  | decompress file                |
| zip     | create zip archive             |
| tar     | archive multiple files         |
| scp     | secure file transfer           |
| rsync   | efficient file synchronization |

---

If you want, I can also show you a **visual diagram of Linux file permissions (rwx → 755, 777, 644) that makes it extremely easy to remember for interviews and exams.**
