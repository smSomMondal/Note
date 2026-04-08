# Networking In Linux


# Table of Contents
[<- Back to Home](README.md)
<!-- 
1. [Introduction to Linux Networking Commands](#introduction-to-linux-networking-commands)

2. [Basic Network Connectivity Commands](#basic-network-connectivity-commands)
   - [ping](#ping)
   - [traceroute](#traceroute)
   - [tracepath](#tracepath)
   - [mtr](#mtr)

3. [Network Configuration Commands](#network-configuration-commands)
   - [ifconfig](#ifconfig)
   - [ip](#ip)
   - [iwconfig](#iwconfig)
   - [ifplugstatus](#ifplugstatus)

4. [Network Monitoring and Statistics](#network-monitoring-and-statistics)
   - [netstat](#netstat)
   - [ss](#ss)
   - [watch](#watch)

5. [DNS and Domain Query Tools](#dns-and-domain-query-tools)
   - [nslookup](#nslookup)
   - [dig](#dig)
   - [whois](#whois)

6. [Network Address and Routing Tools](#network-address-and-routing-tools)
   - [arp](#arp)
   - [route](#route)

7. [Network Scanning and Security Tools](#network-scanning-and-security-tools)
   - [nmap](#nmap)
   - [iptables](#iptables)

8. [Data Transfer and Download Tools](#data-transfer-and-download-tools)
   - [wget](#wget)
   - [curl](#curl)

9. [Network Debugging Tools](#network-debugging-tools)
   - [nc (netcat)](#nc-netcat)

10. [Comparison of curl vs wget](#comparison-of-curl-vs-wget)

11. [Advantages of Linux Networking Commands](#advantages-of-linux-networking-commands)

12. [Conclusion](#conclusion)

13. [References](#references)
 -->
1. [ping](#1-ping)  
2. [netstat](#2-netstat)  
3. [ifconfig](#3-ifconfig)  
4. [traceroute](#4-traceroute)  
5. [tracepath](#5-tracepath)  
6. [mtr](#6-mtr)  
7. [nslookup](#7-nslookup)  
8. [ip command](#8-ip-command)  
9. [iwconfig](#9-iwconfig)  
10. [ss](#10-ss)  
11. [arp](#11-arp)  
12. [dig](#12-dig)  
13. [nc (Netcat)](#13-nc-netcat)  
14. [whois](#14-whois)  
15. [ifplugstatus](#15-ifplugstatus)  
16. [route](#16-route)  
17. [nmap](#17-nmap)  
18. [wget](#18-wget)  
19. [watch](#19-watch)  
20. [iptables](#20-iptables)  
21. [curl](#21-curl)  
22. [Difference Between curl vs wget](#difference-between-curl-vs-wget) 
---

# 1. `ping`

`ping` checks **network connectivity between two devices**.

It sends **ICMP Echo Request packets** to a host and waits for **Echo Reply**.

### Syntax

```bash
ping <host>
```

### Example

```bash
ping google.com
```

### Example Output

```text
64 bytes from 142.250.190.78: icmp_seq=1 ttl=116 time=20.1 ms
```

### Fields

| Field      | Meaning                |
| ---------- | ---------------------- |
| `icmp_seq` | Packet sequence number |
| `ttl`      | Time To Live           |
| `time`     | Round trip delay       |

### Useful Options

```bash
ping -c 4 google.com
```

Send only **4 packets**.

```bash
ping -i 2 google.com
```

Send packet every **2 seconds**.

---

# 2. `netstat`

`netstat` shows **network connections, routing tables, interface statistics, and ports**.

### Syntax

```bash
netstat [options]
```

### Example

```bash
netstat -tuln
```

| Option | Meaning                |
| ------ | ---------------------- |
| `t`    | TCP connections        |
| `u`    | UDP connections        |
| `l`    | Listening ports        |
| `n`    | Show numeric addresses |

### Example Output

```text
Proto Local Address   Foreign Address State
tcp   0.0.0.0:22      0.0.0.0:*       LISTEN
```

---

# 3. `ifconfig`

Displays **network interface configuration**.

### Syntax

```bash
ifconfig
```

### Example Output

```text
eth0: flags=4163
inet 192.168.1.10
netmask 255.255.255.0
```

| Field     | Meaning     |
| --------- | ----------- |
| `inet`    | IP address  |
| `netmask` | Subnet mask |

### Enable Interface

```bash
ifconfig eth0 up
```

### Disable Interface

```bash
ifconfig eth0 down
```

*(Modern systems use `ip` instead.)*

---

# 4. `traceroute`

Shows **path packets take to reach destination**.

### Syntax

```bash
traceroute google.com
```

### Example Output

```text
1 192.168.1.1
2 10.0.0.1
3 172.217.1.46
```

Each line represents a **network hop**.

---

# 5. `tracepath`

Similar to `traceroute` but **does not require root privileges**.

```bash
tracepath google.com
```

Used to **detect MTU issues**.

---

# 6. `mtr`

`mtr` = **My Traceroute**

Combines **ping + traceroute**.

### Syntax

```bash
mtr google.com
```

Shows

* packet loss
* latency
* network hops

---

# 7. `nslookup`

Used to **query DNS servers**.

### Syntax

```bash
nslookup google.com
```

### Output

```text
Name: google.com
Address: 142.250.190.78
```

Used to find

* domain IP
* DNS records

---

# 8. `ip` command

Modern replacement for **ifconfig, route, arp**.

### Show IP address

```bash
ip addr
```

### Show routing table

```bash
ip route
```

### Show network interfaces

```bash
ip link
```

---

# 9. `iwconfig`

Used for **wireless network configuration**.

### Syntax

```bash
iwconfig
```

### Example Output

```text
wlan0 IEEE 802.11
ESSID:"WiFiName"
```

Shows

* WiFi signal
* SSID
* frequency

---

# 10. `ss`

Replacement for **netstat**.

Shows **socket statistics**.

### Syntax

```bash
ss -tuln
```

### Example

```text
Netid State Local Address:Port
tcp LISTEN 0.0.0.0:22
```

---

# 11. `arp`

Displays **ARP table** (IP → MAC mapping).

### Syntax

```bash
arp -a
```

Example output

```text
192.168.1.1 at 00:1a:2b:3c:4d:5e
```

---

# 12. `dig`

Advanced DNS lookup tool.

### Syntax

```bash
dig google.com
```

Shows

* DNS server
* IP address
* query time

Example:

```text
ANSWER SECTION:
google.com 300 IN A 142.250.190.78
```

---

# 13. `nc` (Netcat)

Called **Swiss army knife of networking**.

Used for

* port scanning
* testing connections
* transferring files

### Check Port

```bash
nc -zv google.com 80
```

---

# 14. `whois`

Shows **domain registration information**.

### Syntax

```bash
whois google.com
```

Output includes

* registrar
* domain owner
* creation date
* expiry date

---

# 15. `ifplugstatus`

Checks **network cable status**.

```bash
ifplugstatus eth0
```

Output

```text
eth0: link beat detected
```

---

# 16. `route`

Shows **routing table**.

```bash
route -n
```

Example

```text
Destination Gateway Genmask
0.0.0.0     192.168.1.1
```

---

# 17. `nmap`

Network scanning tool.

Used for

* port scanning
* vulnerability scanning
* host discovery

### Example

```bash
nmap 192.168.1.1
```

### Scan open ports

```bash
nmap -p 1-1000 192.168.1.1
```

---

# 18. `wget`

Downloads files from internet.

### Syntax

```bash
wget https://example.com/file.zip
```

Features

* resume download
* batch download
* recursive download

---

# 19. `watch`

Runs command repeatedly.

### Syntax

```bash
watch -n 2 ls
```

Runs **ls every 2 seconds**.

---

# 20. `iptables`

Linux **firewall management tool**.

Used to

* allow/block traffic
* filter packets

Example

```bash
iptables -L
```

Block IP

```bash
iptables -A INPUT -s 192.168.1.10 -j DROP
```

---

# 21. `curl`

Transfers data using URLs.

### Example

```bash
curl https://example.com
```

Used for

* API testing
* downloading data
* sending HTTP requests

---

# Difference Between `curl` and `wget`

| Feature            | curl      | wget      |
| ------------------ | --------- | --------- |
| Protocol support   | Many      | HTTP, FTP |
| Download files     | Yes       | Yes       |
| Resume download    | Limited   | Strong    |
| API requests       | Excellent | Limited   |
| Recursive download | No        | Yes       |

Example

```bash
curl https://api.github.com
```

```bash
wget https://example.com/file.zip
```

---

# Quick Summary Table

| Command    | Purpose                 |
| ---------- | ----------------------- |
| ping       | Test connectivity       |
| netstat    | Network statistics      |
| ifconfig   | Interface configuration |
| traceroute | Path of packets         |
| mtr        | Network diagnostic      |
| nslookup   | DNS query               |
| ip         | Network configuration   |
| iwconfig   | Wireless settings       |
| ss         | Socket statistics       |
| arp        | IP → MAC mapping        |
| dig        | DNS information         |
| nc         | Network debugging       |
| whois      | Domain information      |
| route      | Routing table           |
| nmap       | Network scanning        |
| wget       | File download           |
| curl       | Data transfer           |
| watch      | Run command repeatedly  |
| iptables   | Firewall management     |

---

✅ If you want, I can also give **a complete Linux Networking Command Cheat Sheet (40+ commands)** that is **very useful for exams, interviews, and system administration.**
