# Linux Basic File System Structure

Linux follows a **hierarchical directory structure** that starts from a single root directory `/`.

Everything in Linux (files, devices, processes) is organized under this structure.

Example structure:

```
/
├── bin
├── boot
├── dev
├── etc
├── home
├── lib
├── media
├── mnt
├── opt
├── proc
├── root
├── run
├── sbin
├── srv
├── sys
├── tmp
├── usr
└── var
```

## Table of Contents
[<- Back to Home](README.md)
- [Linux Basic File System Structure](#linux-basic-file-system-structure)
- [Root Directory `/`](#1--root-directory)
- [`/bin` – Binary Executables](#2-bin--binary-executables)
- [`/boot` – Boot Files](#3-boot)
- [`/dev` – Device Files](#4-dev--device-files)
- [`/etc` – Configuration Files](#5-etc--configuration-files)
- [`/home` – User Home Directories](#6-home)
- [`/lib` – System Libraries](#7-lib)
- [`/media` – Removable Media](#8-media)
- [`/mnt` – Temporary Mount Point](#9-mnt)
- [`/opt` – Optional Software](#10-opt)
- [`/proc` – Process Information](#11-proc)
- [`/root` – Root User Home Directory](#12-root)
- [`/run` – Runtime Data](#13-run)
- [`/sbin` – System Binaries](#14-sbin)
- [`/srv` – Service Data](#15-srv)
- [`/sys` – Kernel Interface](#16-sys)
- [`/tmp` – Temporary Files](#17-tmp)
- [`/usr` – User Programs](#18-usr)
- [`/var` – Variable Data](#19-var)
- [Summary Table](#quick-summary-table)
# 1. `/` (Root Directory)

This is the **top-level directory** of the Linux file system.

All directories and files start from here.

Example:

```
/home/user
/etc/passwd
/var/log
```

---

# 2. `/bin` (Binary Executables)

Contains **essential command binaries** used by all users.

Examples:

```
/bin/ls
/bin/cp
/bin/mv
/bin/cat
/bin/bash
```

These commands are required for **basic system operations**.

---

# 3. `/boot`

Contains files required to **boot the system**.

Examples:

```
/boot/vmlinuz
/boot/grub
```

Important files:

| File    | Purpose                  |
| ------- | ------------------------ |
| vmlinuz | Linux kernel             |
| initrd  | initial RAM disk         |
| grub    | bootloader configuration |

---

# 4. `/dev` (Device Files)

Contains **device files representing hardware**.

Examples:

```
/dev/sda
/dev/sda1
/dev/tty
/dev/null
```

Examples explained:

| Device    | Meaning        |
| --------- | -------------- |
| /dev/sda  | hard disk      |
| /dev/sda1 | disk partition |
| /dev/null | null device    |
| /dev/tty  | terminal       |

Linux treats hardware as **files**.

---

# 5. `/etc` (Configuration Files)

Contains **system configuration files**.

Examples:

```
/etc/passwd
/etc/shadow
/etc/hosts
/etc/fstab
/etc/ssh
```

Important files:

| File   | Purpose                   |
| ------ | ------------------------- |
| passwd | user account information  |
| shadow | encrypted passwords       |
| hosts  | hostname mapping          |
| fstab  | filesystem mount settings |

---

# 6. `/home`

Contains **user home directories**.

Example:

```
/home/som
/home/john
```

Inside a user home directory:

```
/home/som/Documents
/home/som/Downloads
/home/som/Desktop
```

Users store **personal files here**.

---

# 7. `/lib`

Contains **shared libraries required by system programs**.

Example:

```
/lib/libc.so
/lib/modules
```

Libraries are similar to **DLL files in Windows**.

---

# 8. `/media`

Used for **automatically mounted removable devices**.

Example:

```
/media/usb
/media/cdrom
```

When you insert a **USB drive**, Linux mounts it here.

---

# 9. `/mnt`

Used for **temporary mount points**.

Example:

```
/mnt/backup
/mnt/disk
```

System administrators mount disks manually here.

---

# 10. `/opt`

Contains **optional software packages**.

Example:

```
/opt/google
/opt/docker
```

Used by **third-party applications**.

---

# 11. `/proc`

A **virtual filesystem** containing information about system processes.

Example:

```
/proc/cpuinfo
/proc/meminfo
/proc/1234
```

Examples:

| File        | Meaning           |
| ----------- | ----------------- |
| cpuinfo     | CPU details       |
| meminfo     | memory usage      |
| pid folders | running processes |

---

# 12. `/root`

Home directory of the **root (administrator) user**.

Example:

```
/root
```

Different from `/home`.

```
/home/som
/root
```

---

# 13. `/run`

Contains **runtime system information**.

Example:

```
/run/systemd
/run/lock
```

Stores **temporary data for running services**.

---

# 14. `/sbin`

Contains **system administration binaries**.

Examples:

```
/sbin/reboot
/sbin/shutdown
/sbin/fsck
```

Used mostly by **root user**.

---

# 15. `/srv`

Stores **data served by services**.

Example:

```
/srv/www
/srv/ftp
```

Used for **web servers and FTP servers**.

---

# 16. `/sys`

Virtual filesystem providing **kernel and hardware information**.

Example:

```
/sys/class
/sys/devices
```

Used for **device management and kernel interaction**.

---

# 17. `/tmp`

Temporary files.

Example:

```
/tmp/test.txt
```

Characteristics:

* files deleted automatically
* used by applications

---

# 18. `/usr`

Contains **user applications and utilities**.

Examples:

```
/usr/bin
/usr/lib
/usr/share
/usr/local
```

Examples:

| Directory  | Purpose       |
| ---------- | ------------- |
| /usr/bin   | user commands |
| /usr/lib   | libraries     |
| /usr/share | shared data   |

---

# 19. `/var`

Contains **variable data** like logs and databases.

Examples:

```
/var/log
/var/mail
/var/spool
```

Important folders:

| Directory  | Purpose     |
| ---------- | ----------- |
| /var/log   | system logs |
| /var/mail  | user mail   |
| /var/spool | print jobs  |

---

# Quick Summary Table

| Directory | Purpose                |
| --------- | ---------------------- |
| /         | root directory         |
| /bin      | basic commands         |
| /boot     | boot files             |
| /dev      | device files           |
| /etc      | configuration files    |
| /home     | user directories       |
| /lib      | libraries              |
| /media    | removable media        |
| /mnt      | mount points           |
| /opt      | optional software      |
| /proc     | process information    |
| /root     | root user home         |
| /run      | runtime data           |
| /sbin     | system commands        |
| /srv      | service data           |
| /sys      | kernel interface       |
| /tmp      | temporary files        |
| /usr      | user programs          |
| /var      | logs and variable data |

---

✅ If you want, I can also give you **a visual Linux filesystem diagram (like used in DevOps and Linux interview preparation)** which makes this **10× easier to remember.**
