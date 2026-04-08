## Dockerfile (Detailed & Deep Explanation)

![Image](https://docs.docker.com/get-started/docker-concepts/building-images/images/container_image_layers.webp)

![Image](https://media.licdn.com/dms/image/v2/D5612AQH_MBfecK5GSw/article-cover_image-shrink_600_2000/article-cover_image-shrink_600_2000/0/1720423703954?e=2147483647\&t=SzeZVpC8LkbZ-_48TpLk9hOkO2rvM_wmOrA8olU_jIM\&v=beta)

![Image](https://miro.medium.com/0%2AHhURteVLNxudDuEt)

![Image](https://miro.medium.com/v2/resize%3Afit%3A1400/0%2AVldlDb6mpf6Ma3Kf.png)

A **Dockerfile** is a **text file containing instructions used to build a Docker image automatically**.

It defines:

* Base image
* Application dependencies
* Environment variables
* Commands to run
* Ports to expose
* Startup command

Docker reads the Dockerfile and creates an **image layer by layer**.

Dockerfile is used with the command:

```bash
docker build -t image_name .
```

---

# 1. Basic Structure of a Dockerfile

Example Dockerfile:

```dockerfile
FROM node:18

WORKDIR /app

COPY package.json .

RUN npm install

COPY . .

EXPOSE 3000

CMD ["node","server.js"]
```

This Dockerfile:

1. Uses Node.js base image
2. Creates working directory
3. Copies project files
4. Installs dependencies
5. Runs the application

---

# 2. Dockerfile Build Process

When you run:

```bash
docker build -t myapp .
```

Docker performs:

```
Step 1 → Read Dockerfile
Step 2 → Execute instructions sequentially
Step 3 → Create image layers
Step 4 → Store image locally
```

Each Dockerfile instruction creates **one layer**.

Example layers:

```
Layer 1 → Base Image
Layer 2 → Install Dependencies
Layer 3 → Copy Files
Layer 4 → Application
```

---

# 3. Important Dockerfile Instructions

Below are the **most important Dockerfile commands used in real projects**.

---

# 3.1 FROM

`FROM` defines the **base image**.

Syntax:

```dockerfile
FROM image_name:tag
```

Example:

```dockerfile
FROM ubuntu:22.04
```

Example:

```dockerfile
FROM node:18
```

You can also use minimal images:

```dockerfile
FROM alpine
```

Rules:

* Must be **first instruction**
* Defines starting environment

---

# 3.2 RUN

`RUN` executes commands during **image build time**.

Example:

```dockerfile
RUN apt update
RUN apt install -y nginx
```

Better version (combine layers):

```dockerfile
RUN apt update && apt install -y nginx
```

Example installing Python:

```dockerfile
RUN apt-get update && apt-get install -y python3
```

Important:

* Creates **new image layer**
* Runs **inside container during build**

---

# 3.3 COPY

`COPY` copies files from host to container image.

Syntax:

```dockerfile
COPY source destination
```

Example:

```dockerfile
COPY app.py /app/
```

Copy entire directory:

```dockerfile
COPY . /app
```

Used for:

* application code
* configuration files
* scripts

---

# 3.4 ADD

`ADD` is similar to COPY but with extra features.

Syntax:

```dockerfile
ADD source destination
```

Example:

```dockerfile
ADD myfile.tar.gz /app/
```

Features:

* Extracts tar files automatically
* Can download URLs

Example:

```dockerfile
ADD https://example.com/file.zip /data
```

Best practice:

```
Prefer COPY over ADD
```

---

# 3.5 WORKDIR

`WORKDIR` sets working directory inside container.

Syntax:

```dockerfile
WORKDIR /path
```

Example:

```dockerfile
WORKDIR /app
```

After this command:

```
All commands run inside /app
```

Equivalent to:

```
cd /app
```

---

# 3.6 CMD

`CMD` defines default command when container starts.

Syntax:

```dockerfile
CMD ["executable","param1"]
```

Example:

```dockerfile
CMD ["node","server.js"]
```

Example:

```dockerfile
CMD ["python","app.py"]
```

Important rules:

* Only **one CMD allowed**
* Last CMD overrides previous ones

---

# 3.7 ENTRYPOINT

`ENTRYPOINT` defines the **main container command**.

Example:

```dockerfile
ENTRYPOINT ["python","app.py"]
```

Difference:

| CMD               | ENTRYPOINT         |
| ----------------- | ------------------ |
| Default command   | Main command       |
| Can be overridden | Harder to override |

Example combining both:

```dockerfile
ENTRYPOINT ["python"]
CMD ["app.py"]
```

Run:

```
python app.py
```

---

# 3.8 ENV

`ENV` sets environment variables.

Syntax:

```dockerfile
ENV KEY=value
```

Example:

```dockerfile
ENV NODE_ENV=production
```

Example:

```dockerfile
ENV PORT=3000
```

Environment variables can be used in applications.

---

# 3.9 EXPOSE

`EXPOSE` tells Docker which port the container uses.

Syntax:

```dockerfile
EXPOSE port
```

Example:

```dockerfile
EXPOSE 3000
```

Example:

```dockerfile
EXPOSE 80
```

Important:

```
EXPOSE does not publish port
```

You still need:

```bash
docker run -p 3000:3000 image
```

---

# 3.10 VOLUME

Defines persistent storage location.

Syntax:

```dockerfile
VOLUME ["/data"]
```

Example:

```dockerfile
VOLUME /var/lib/mysql
```

Used for:

* database storage
* persistent files

---

# 3.11 USER

Specifies user to run container.

Example:

```dockerfile
USER appuser
```

Instead of running as root.

Improves security.

---

# 3.12 ARG

Defines build-time variables.

Syntax:

```dockerfile
ARG variable
```

Example:

```dockerfile
ARG VERSION=1.0
```

Build with argument:

```bash
docker build --build-arg VERSION=2.0 .
```

---

# 3.13 LABEL

Adds metadata to image.

Example:

```dockerfile
LABEL maintainer="admin@example.com"
```

Example:

```dockerfile
LABEL version="1.0"
```

Useful for:

* documentation
* automation

---

# 3.14 SHELL

Changes default shell.

Example:

```dockerfile
SHELL ["/bin/bash","-c"]
```

---

# 3.15 HEALTHCHECK

Checks container health.

Example:

```dockerfile
HEALTHCHECK CMD curl --fail http://localhost:3000 || exit 1
```

Docker monitors application health.

---

# 4. Dockerfile Example (Real Web Application)

Example Node.js app Dockerfile:

```dockerfile
FROM node:18

WORKDIR /app

COPY package.json .

RUN npm install

COPY . .

EXPOSE 3000

CMD ["node","server.js"]
```

Build image:

```bash
docker build -t nodeapp .
```

Run container:

```bash
docker run -p 3000:3000 nodeapp
```

---

# 5. Dockerfile Best Practices

### Use small base images

Better:

```dockerfile
FROM alpine
```

Instead of:

```dockerfile
FROM ubuntu
```

---

### Combine RUN commands

Bad:

```dockerfile
RUN apt update
RUN apt install nginx
```

Better:

```dockerfile
RUN apt update && apt install -y nginx
```

---

### Use `.dockerignore`

Example:

```
node_modules
.git
logs
```

Avoid copying unnecessary files.

---

### Multi-stage builds

Example:

```dockerfile
FROM node AS builder
RUN npm install

FROM nginx
COPY --from=builder /app /usr/share/nginx/html
```

Benefits:

* smaller images
* improved security

---

# 6. Dockerfile Execution Order

Docker processes instructions sequentially.

Example:

```
1 FROM
2 WORKDIR
3 COPY
4 RUN
5 COPY
6 EXPOSE
7 CMD
```

Each instruction creates a new **image layer**.

---

# 7. Dockerfile vs Container Runtime

Dockerfile is used to **build images**, not run containers.

Workflow:

```
Dockerfile
   ↓
docker build
   ↓
Docker Image
   ↓
docker run
   ↓
Docker Container
```

---

# Summary

A Dockerfile is used to **automate container image creation**.

Important instructions:

| Instruction | Purpose                  |
| ----------- | ------------------------ |
| FROM        | base image               |
| RUN         | execute commands         |
| COPY        | copy files               |
| ADD         | copy with extra features |
| WORKDIR     | set working directory    |
| CMD         | default command          |
| ENTRYPOINT  | main container command   |
| ENV         | environment variables    |
| EXPOSE      | define ports             |
| VOLUME      | persistent storage       |
| USER        | specify user             |
| ARG         | build-time variables     |

---

✅ **In simple words**

A Dockerfile is:

```
A script that automatically builds Docker images
by defining the application environment step-by-step.
```

---

If you want, I can also explain **very important advanced topics** that most DevOps courses teach next:

* **Docker image layering deeply (how caching works)**
* **Multi-stage Dockerfile builds deeply**
* **Docker networking deeply**
* **Real production Dockerfile examples used in DevOps**.
