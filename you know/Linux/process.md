# Process Management
[<- Back to Home](README.md)
## **Table Of Content**
- [Login Command](#1-login-related-commands)
- [ssh – Secure Shell](#ssh--secure-shell)
- [df – Disk Free](#2-df--disk-free)
- [du – Disk Usage](#3-du--disk-usage)
- [ps – Process Status](#4-ps--process-status)
- [top – Real Time Process Monitor](#5-top--real-time-process-monitor)
- [fuser – File User](#6-fuser)
- [kill – Terminate Process](#7-kill)
- [nohup – Run Process in Background](#8-nohup--run-process-in-background)
- [free – Memory Usage](#9-free--check-memory-usage)
- [vmstat – System Performance](#10-vmstat--virtual-memory-statistics)
## 1. Login Related Commands

### `login`
**Purpose:** Used to log into a Linux system.

```bash
login username
````

Example:

```bash
login root
```

---

### `ssh` – Secure Shell

**Purpose:** Connect to a remote system securely over a network.

**Syntax**

```bash
ssh username@hostname
```

Example:

```bash
ssh user@192.168.1.10
```

Options:

```bash
ssh -p 22 user@host   # connect using specific port
ssh -i key.pem user@host   # use SSH key
ssh -i "keyPath/"Privet-Key.pem UserName@EC2-address   # use SSH key
```

---

# Disk Usage Commands

## 2. `df` – Disk Free

**Purpose:** Shows disk space usage of file systems.

```bash
df
```

Human readable format:

```bash
df -h
```

Example output:

```
Filesystem   Size  Used  Avail  Use%
/dev/sda1     50G   20G   30G   40%
```

---

## 3. `du` – Disk Usage

**Purpose:** Shows space used by files or directories.

```bash
du
```

Check size of a folder:

```bash
du -sh folder_name
```

Options:

```bash
du -h   # human readable
du -s   # summary
```

---

# Process Management

## 4. `ps` – Process Status

**Purpose:** Displays currently running processes.

```bash
ps
```

Show all processes:

```bash
ps -ef
```

---

### Syntax

```bash
ps -aux
```

#### Meaning of Options

| Option | Meaning                                                                |
| ------ | ---------------------------------------------------------------------- |
| `a`    | Show processes of **all users**                                        |
| `u`    | Show **user-oriented format** (detailed info)                          |
| `x`    | Show processes **without controlling terminal** (background processes) |

So,

 `ps -aux` shows **all running processes in detailed format**.

---

### Example Output

```bash
ps -aux
```

Example:

```text
USER       PID %CPU %MEM    VSZ   RSS TTY      STAT START   TIME COMMAND
root         1  0.0  0.1 169292  1144 ?        Ss   10:00   0:01 /sbin/init
user      2354  2.5  1.3 234567 10240 pts/0    R+   21:30   0:05 firefox
user      3401  0.0  0.2  54321  2012 pts/1    S+   21:32   0:00 bash
```

---

### Explanation of Columns

| Column      | Meaning                          |
| ----------- | -------------------------------- |
| **USER**    | User who started the process     |
| **PID**     | Process ID                       |
| **%CPU**    | CPU usage percentage             |
| **%MEM**    | Memory usage percentage          |
| **VSZ**     | Virtual memory size              |
| **RSS**     | Physical memory used             |
| **TTY**     | Terminal associated with process |
| **STAT**    | Process state                    |
| **START**   | Time process started             |
| **TIME**    | CPU time used                    |
| **COMMAND** | Command that started the process |

---

### Process State (`STAT`)

| Code | Meaning               |
| ---- | --------------------- |
| `R`  | Running               |
| `S`  | Sleeping              |
| `T`  | Stopped               |
| `Z`  | Zombie                |
| `D`  | Uninterruptible sleep |

Example:

```text
R+
```

Meaning:

* `R` → Running
* `+` → Foreground process

---

## 5. `top` – Real-Time Process Monitor

**Purpose:** Displays running processes and system usage in real time.

```bash
top
```

Shows:

* CPU usage
* Memory usage
* Running processes

Useful keys:

```
q → quit
k → kill process
P → sort by CPU
M → sort by memory
```

---

## 6. `fuser`

**Purpose:** Shows which process is using a file or port.

Example:

```bash
fuser file.txt
```

Check port usage:

```bash
fuser 8080/tcp
```

Kill process using file:

```bash
fuser -k file.txt
```

---

## 7. `kill`

**Purpose:** Terminates a running process using PID.

Syntax:

```bash
kill PID
```

Example:

```bash
kill 1234
```

Force kill:

```bash
kill -9 1234
```

---

## 8. `nohup` – Run Process in Background

**Purpose:** Store the output of Runs a command that continues after logout.

Example:

```bash
nohup python app.py &
```

Output stored in:

```
nohup.out
```

---

# Memory Monitoring

## 9. `free` – Check Memory Usage

Shows RAM and swap memory usage.

```bash
free
```

Human readable:

```bash
free -h
```

Example output:

```
total   used   free
8GB     3GB    5GB
```

---

## 10. `vmstat` – Virtual Memory Statistics

Shows system performance and memory statistics.

```bash
vmstat
vmstat -a # Shows active inactive
```

Example:

```bash
vmstat 2
```

Displays system statistics every **2 seconds**.

Columns include:

| Column | Meaning           |
| ------ | ----------------- |
| r      | running processes |
| free   | free memory       |
| si     | swap in           |
| so     | swap out          |

---

# Summary Table

| Command | Purpose                     |
| ------- | --------------------------- |
| login   | login to system             |
| ssh     | remote login                |
| df      | disk space usage            |
| du      | directory space usage       |
| ps      | show running processes      |
| top     | real-time process monitor   |
| fuser   | identify process using file |
| kill    | terminate process           |
| nohup   | run process after logout    |
| free    | memory usage                |
| vmstat  | system performance stats    |

