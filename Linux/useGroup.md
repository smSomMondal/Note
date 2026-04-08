# Linux User and Group Management Commands

## Table of Contents
[<- Back to Home](README.md)
- [sudo](#1-sudo--run-command-as-superuser)
- [useradd](#2-useradd--create-new-user)
- [whoami](#3-whoami--display-current-user)
- [su](#4-su--switch-user)
- [passwd](#5-passwd--change-user-password)
- [userdel](#6-userdel--delete-user)
- [groupadd](#7-groupadd--create-new-group)
- [gpasswd](#8-gpasswd--manage-group-members)
- [groupdel](#9-groupdel--delete-group)

---

# 1. `sudo` – Run Command as Superuser

**Purpose:**  
`sudo` allows a normal user to execute commands with **root (administrator) privileges**.

Root has full control over the system.

**Syntax**

```bash
sudo command
````

Example:

```bash
sudo apt update
```

Meaning:

| Part       | Description                  |
| ---------- | ---------------------------- |
| sudo       | run command as administrator |
| apt update | update system packages       |

Common options:

```bash
sudo -i      # login as root shell
sudo -u user command   # run command as another user
```

Example:

```bash
sudo -u john ls
```

Runs `ls` as user **john**.

---

# 2. `useradd` – Create New User

**Purpose:**
Creates a new user account in Linux.

**Syntax**

```bash
sudo useradd username
```

Example:

```bash
sudo useradd som
```

Important options:

```bash
sudo useradd -m username   # create home directory
sudo useradd -d /home/user1 username  # specify home directory
sudo useradd -s /bin/bash username    # specify shell
```

Example:

```bash
sudo useradd -m -s /bin/bash som
```

After creating user, set password:

```bash
sudo passwd som
```

User information stored in:

```
/etc/passwd   -> User list
/etc/shadow
```

---

# 3. `whoami` – Display Current User

Shows the **currently logged-in user**.

```bash
whoami
```

Example output:

```
som
```

If you run:

```bash
sudo whoami
```

Output:

```
root
```

This shows the command is executed with **root privileges**.

---

# 4. `su` – Switch User

**Purpose:**
Allows switching to another user account.

**Syntax**

```bash
su username
```

Example:

```bash
su root
```

System asks for **root password**.

Switch user with login shell:

```bash
su - username
```

Example:

```bash
su - som
```

Difference:

| Command  | Meaning                |
| -------- | ---------------------- |
| su som   | switch user only       |
| su - som | full login environment |

---

# 5. `passwd` – Change User Password

Used to change user passwords.

**Syntax**

```bash
passwd
```

Change current user password.

Change another user's password (root only):

```bash
sudo passwd username
```

Example:

```bash
sudo passwd som
```

Lock user account:

```bash
sudo passwd -l username
```

Unlock account:

```bash
sudo passwd -u username
```

---

# 6. `userdel` – Delete User

Removes a user account.

**Syntax**

```bash
sudo userdel username
```

Example:

```bash
sudo userdel som
```

Delete user with home directory:

```bash
sudo userdel -r username
```

Example:

```bash
sudo userdel -r som
```

---

# 7. `groupadd` – Create New Group

Creates a new group.

Groups help manage permissions for multiple users.

**Syntax**

```bash
sudo groupadd groupname
```

Example:

```bash
sudo groupadd developers
```

Group information stored in:

```
/etc/group
```

---

# 8. `gpasswd` – Manage Group Members

Used to manage users inside groups.

### Add user to group

```bash
sudo gpasswd -a username groupname
```

Example:

```bash
sudo gpasswd -a som developers
```

### Add multiple users

```bash
sudo gpasswd -M user1,user2 groupname
```

Example:

```bash
sudo gpasswd -M som,rahul developers
```

### Remove user from group

```bash
sudo gpasswd -d username groupname
```

Example:

```bash
sudo gpasswd -d som developers
```

---

# 9. `groupdel` – Delete Group

Removes a group from the system.

**Syntax**

```bash
sudo groupdel groupname
```

Example:

```bash
sudo groupdel developers
```

---

# Summary Table

| Command  | Purpose                      |
| -------- | ---------------------------- |
| sudo     | run command as administrator |
| useradd  | create new user              |
| whoami   | show current user            |
| su       | switch user                  |
| passwd   | change password              |
| userdel  | delete user                  |
| groupadd | create group                 |
| gpasswd  | manage group members         |
| groupdel | delete group                 |

