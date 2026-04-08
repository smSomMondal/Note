# Day 4

## Table of Contents
[<- Back to Home](README.md)
### System Information
- [uname](#1-uname--system-information)
- [uptime](#2-uptime--system-running-time)
- [date](#3-date--display-or-set-system-date)

### User Information
- [who](#4-who--logged-in-users)
- [whoami](#5-whoami--current-user)
- [which](#6-which--locate-command)
- [id](#7-id--user-identity-information)

### Privilege Management
- [sudo](#8-sudo--run-as-superuser)

### System Control
- [shutdown](#9-shutdown--power-off-system)
- [reboot](#10-reboot--restart-system)

### Package Management
- [apt](#11-apt--debian/ubuntu-package-manager)
- [yum](#12-yum--package-manager-for-rhel/centos)
- [dnf](#13-dnf--modern-yum-replacement)
- [pacman](#14-pacman--arch-linux-package-manager)
- [portage](#15-portage--gentoo-package-manager)
# System Information Commands

## 1. `uname` – System Information

**Purpose:** Displays information about the system and Linux kernel like system name, version etc.

**Syntax**

```bash
uname [options]
````

Common options:

```bash
uname -a   # show all system information
uname -r   # kernel version
uname -n   # network hostname
uname -s   # kernel name
uname -m   # machine hardware
```

Example:

```bash
uname -a
```

Example output:

```
Linux ubuntu 5.15.0-67-generic x86_64 GNU/Linux
```

---

## 2. `uptime` – System Running Time

**Purpose:** Shows how long the system has been running.

```bash
uptime
```

Example output:

```
10:45:22 up 3 days, 4:12, 2 users, load average: 0.15, 0.10, 0.05
```

Explanation:

| Field        | Meaning             |
| ------------ | ------------------- |
| up 3 days    | system running time |
| 2 users      | logged in users     |
| load average | CPU load            |

---

## 3. `date` – Display or Set System Date

Shows current system date and time.

```bash
date
```

Custom format:

```bash
date "+%Y-%m-%d"
```

Example output:

```
2026-03-11
```

Set date (root only):

```bash
sudo date MMDDhhmmYYYY
```

---

## 4. `who` – Logged-in Users

Displays all users currently logged in.

```bash
who
```

Example output:

```
user1  pts/0  2026-03-11 10:20
```

---

## 5. `whoami` – Current User

Displays the current logged-in username.

```bash
whoami
```

Output:

```
som
```

---

## 6. `which` – Locate Command

Shows the path or location of a command executable.

```bash
which python
```

Example output:

```
/usr/bin/python
```

---

## 7. `id` – User Identity Information

Displays user ID and group information.

```bash
id
```

Example output:

```
uid=1000(som) gid=1000(som) groups=1000(som),27(sudo)
```

Meaning:

| Field  | Description |
| ------ | ----------- |
| uid    | user ID     |
| gid    | group ID    |
| groups | user groups |

---

## 8. `sudo` – Run as Superuser

Allows a normal user to execute commands as **root (administrator)**.

```bash
sudo command
```

Example:

```bash
sudo apt update
```

Used for system administration tasks.

---

# System Control Commands

## 9. `shutdown` – Power Off System

Shuts down the system safely.

```bash
shutdown now
```

Shutdown after 10 minutes:

```bash
shutdown +10
```

Shutdown at specific time:

```bash
shutdown 22:00
```

Cancel shutdown:

```bash
shutdown -c
```

---

## 10. `reboot` – Restart System

Restarts the computer.

```bash
reboot
```

Equivalent command:

```bash
sudo shutdown -r now
```

---

# Package Management Commands

Different Linux distributions use different package managers.

---

## 11. `apt` – Debian / Ubuntu Package Manager

Used in **Ubuntu, Debian** systems.

Update package list:

```bash
sudo apt update
```

Upgrade packages:

```bash
sudo apt upgrade
```

Install package:

```bash
sudo apt install nginx
```

Install package from Interner:

```bash
sudo apt-get install docker.io
```

Remove package:

```bash
sudo apt remove nginx
```

---

## 12. `yum` – Package Manager for RHEL/CentOS (Older)

Install package:

```bash
sudo yum install httpd
```

Update packages:

```bash
sudo yum update
```

Remove package:

```bash
sudo yum remove httpd
```

---

## 13. `dnf` – Modern Yum Replacement

Used in **Fedora, CentOS 8+, RHEL 8+**

Install package:

```bash
sudo dnf install nginx
```

Update packages:

```bash
sudo dnf update
```

Remove package:

```bash
sudo dnf remove nginx
```

---

## 14. `pacman` – Arch Linux Package Manager

Used in **Arch Linux**.

Install package:

```bash
sudo pacman -S nginx
```

Update system:

```bash
sudo pacman -Syu
```

Remove package:

```bash
sudo pacman -R nginx
```

---

## 15. `portage` – Gentoo Package Manager

Used in **Gentoo Linux**.

Install package:

```bash
sudo emerge nginx
```

Update system:

```bash
sudo emerge --sync
```

Upgrade packages:

```bash
sudo emerge -u world
```

---

# Summary Table

| Command  | Purpose                     |
| -------- | --------------------------- |
| uname    | system information          |
| uptime   | system running time         |
| date     | display date and time       |
| who      | logged in users             |
| whoami   | current user                |
| which    | command location            |
| id       | user identity               |
| sudo     | run as administrator        |
| shutdown | power off system            |
| reboot   | restart system              |
| apt      | Debian package manager      |
| yum      | RHEL/CentOS package manager |
| dnf      | Fedora package manager      |
| pacman   | Arch Linux package manager  |
| portage  | Gentoo package manager      |

```

---

If you want, I can also create **Day 4 Table of Contents links** so your README navigation works perfectly with all commands.
```
