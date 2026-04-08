[<- Back To Home](readme.md)
## Docker Architecture (Detailed Explanation)

![Image](https://docs.docker.com/get-started/images/docker-architecture.webp)

![Image](https://miro.medium.com/1%2AGoZ56yZNpG_VnGGvqhYlCQ.png)

![Image](https://miro.medium.com/0%2AG82uZfX0ozIih3-_)

![Image](https://cdn.prod.website-files.com/681e366f54a6e3ce87159ca4/687d7a52cccb7374efbbf8ca_image2-49.png)

Docker uses a **client–server architecture** that allows developers to build, run, and manage containers efficiently. The architecture mainly consists of the following components:

1. **Docker Client**
2. **Docker Host**
3. **Docker Daemon**
4. **Docker Objects (Images, Containers, Networks, Volumes)**
5. **Docker Registry**

---

# 1. Docker Client

The **Docker Client** is the primary way users interact with Docker.

### What it does

It sends commands to the Docker daemon.

Example commands:

```bash
docker build
docker pull
docker run
docker stop
docker ps
```

### How it works

When you type a command like:

```bash
docker run nginx
```

The client sends a request to the **Docker Daemon**, which performs the task.

### Communication

Docker client communicates with daemon using:

* **REST API**
* **Unix socket**
* **Network interface**

Example flow:

```
User Command → Docker Client → Docker Daemon → Container Created
```

---

# 2. Docker Host

The **Docker Host** is the machine where Docker runs.

It contains:

* Docker Daemon
* Containers
* Images
* Networks
* Volumes

A Docker Host can be:

* Physical machine
* Virtual machine
* Cloud instance

Example:

```
Your Laptop
Cloud VM (AWS / Azure)
Linux Server
```

---

# 3. Docker Daemon (dockerd)

The **Docker Daemon** is the core component of Docker.

It is responsible for:

* Building images
* Running containers
* Managing networks
* Managing volumes
* Communicating with registries

### What Docker Daemon manages

```
Docker Images
Docker Containers
Docker Networks
Docker Volumes
```

### Example workflow

When you run:

```bash
docker run ubuntu
```

The daemon will:

1. Check if the image exists locally
2. If not → pull from registry
3. Create container
4. Start container

---

# 4. Docker Objects

Docker works with several objects.

## 4.1 Docker Images

A **Docker Image** is a read-only template used to create containers.

It contains:

* Application code
* Libraries
* Dependencies
* Environment variables
* System tools

Example image:

```
nginx
ubuntu
node
mysql
```

### Image Layers

Docker images are built using **layers**.

Example Dockerfile:

```dockerfile
FROM ubuntu
RUN apt update
RUN apt install nginx
COPY . /app
```

Layers:

```
Layer 1 → Ubuntu base image
Layer 2 → apt update
Layer 3 → nginx install
Layer 4 → application code
```

Advantages:

* Faster builds
* Efficient storage
* Layer caching

---

## 4.2 Docker Containers

A **Container** is a **running instance of an image**.

Example:

```
Image → Blueprint
Container → Running machine
```

Command example:

```bash
docker run -d nginx
```

This creates a container from the nginx image.

### Container Characteristics

Containers are:

* Lightweight
* Fast
* Portable
* Isolated

Containers share the **host OS kernel**.

---

## 4.3 Docker Volumes

Volumes are used for **persistent data storage**.

Problem:
Containers are temporary.

If container stops → data may be lost.

Solution:
**Volumes store data outside containers.**

Example:

```bash
docker run -v mydata:/app/data nginx
```

Benefits:

* Persistent storage
* Backup support
* Data sharing between containers

---

## 4.4 Docker Networks

Docker allows containers to communicate using networks.

Types:

| Network Type | Description           |
| ------------ | --------------------- |
| Bridge       | Default network       |
| Host         | Uses host network     |
| None         | No networking         |
| Overlay      | Multi-host networking |

Example:

```bash
docker network create mynet
```

---

# 5. Docker Registry

A **Docker Registry** stores Docker images.

Example registries:

* Docker Hub
* Amazon Elastic Container Registry
* Google Container Registry

### Public Registry

Anyone can download images.

Example:

```bash
docker pull nginx
```

### Private Registry

Organizations store internal images.

Example:

```
company/backend:v1
company/frontend:v2
```

---

# 6. Complete Docker Workflow

Step-by-step workflow:

### Step 1 — Write Dockerfile

Example:

```dockerfile
FROM node
WORKDIR /app
COPY . .
RUN npm install
CMD ["node","app.js"]
```

---

### Step 2 — Build Image

```bash
docker build -t myapp .
```

---

### Step 3 — Store Image

Push to registry:

```bash
docker push myapp
```

---

### Step 4 — Run Container

```bash
docker run -d -p 3000:3000 myapp
```

---

### Step 5 — Application Runs

Container starts the application.

---

# 7. Docker Engine

The **Docker Engine** is the runtime environment.

It includes:

1. Docker Daemon
2. REST API
3. Docker CLI

Architecture:

```
Docker CLI
     │
     ▼
Docker REST API
     │
     ▼
Docker Daemon
     │
     ▼
Containers / Images / Networks
```

---

# 8. Container Runtime (containerd)

Docker uses **containerd** to run containers.

Responsibilities:

* Container lifecycle
* Image transfer
* Storage management
* Network interfaces

Docker → containerd → runc → Container

Example flow:

```
Docker CLI
   ↓
Docker Daemon
   ↓
containerd
   ↓
runc
   ↓
Linux Kernel
```

---

# 9. Docker vs Virtual Machines

| Feature     | Docker        | Virtual Machine |
| ----------- | ------------- | --------------- |
| Boot Time   | Seconds       | Minutes         |
| OS          | Shared Kernel | Full OS         |
| Size        | MB            | GB              |
| Performance | Faster        | Slower          |
| Isolation   | Process level | Hardware level  |

Example:

```
VM → OS + Kernel + App
Docker → App + Dependencies
```

---

# 10. Advantages of Docker Architecture

### 1 Portability

Run anywhere:

* Laptop
* Cloud
* Server

---

### 2 Fast Deployment

Containers start in seconds.

---

### 3 Scalability

Works well with orchestration tools like:

* Kubernetes
* Docker Swarm

---

### 4 Resource Efficiency

Multiple containers run on same OS.

---

# 11. Simple Real-World Example

Example: **Web Application**

Architecture:

```
User Browser
      │
      ▼
Docker Container (Nginx)
      │
      ▼
Docker Container (Node.js App)
      │
      ▼
Docker Container (MongoDB)
```

Each service runs in **separate container**.

Benefits:

* Isolation
* Easy scaling
* Easy updates

---

✅ **In short**

Docker architecture consists of:

```
Docker Client
      │
      ▼
Docker Daemon (Docker Engine)
      │
      ▼
Containers + Images + Networks + Volumes
      │
      ▼
Docker Registry
```

---

If you want, I can also explain:

* **Docker Architecture for interview (10/15 marks answer)**
* **Docker vs Kubernetes**
* **Docker internal working (namespaces + cgroups)**
* **Dockerfile deep explanation**
* **Complete Docker command cheat sheet**.
