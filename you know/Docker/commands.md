Below is a **deep and structured explanation of the most important Docker commands**. I grouped them by category so you can understand **how Docker is actually used in real projects**.

---

# Docker Commands (Detailed Guide)

Docker commands are used to manage:

* Images
* Containers
* Networks
* Volumes
* Registries
* System resources

Docker commands are executed through the **CLI provided by** Docker.

Basic syntax:

```bash
docker [object] [command] [options]
```

Example:

```bash
docker container run nginx
```

Where:

* `container` → object
* `run` → command

---

# 1. Docker Version Command

### Check Docker version

```bash
docker --version
```

Example output:

```
Docker version 24.0.5, build 123abc
```

This shows:

* Installed Docker version
* Build number

---

# 2. Docker Info Command

### View Docker system information

```bash
docker info
```

Shows:

* Containers count
* Images count
* Storage driver
* Kernel version
* CPU and memory
* Docker root directory

Example:

```
Containers: 5
Images: 12
Storage Driver: overlay2
Docker Root Dir: /var/lib/docker
```

---

# 3. Docker Help Command

### Show help

```bash
docker --help
```

Shows all Docker commands.

Example:

```
build
run
pull
push
images
container
volume
network
```

---

# 4. Docker Image Commands

Images are templates used to create containers.

---

## 4.1 List Images

```bash
docker images
```

or

```bash
docker image ls
```

Example output:

```
REPOSITORY   TAG       IMAGE ID       SIZE
nginx        latest    4f3c5b         142MB
ubuntu       22.04     45f1ab         77MB
```

Explanation:

| Field      | Meaning           |
| ---------- | ----------------- |
| Repository | Image name        |
| Tag        | Version           |
| Image ID   | Unique identifier |
| Size       | Image size        |

---

## 4.2 Pull Image

Downloads image from registry such as Docker Hub.

```bash
docker pull nginx
```

Example:

```
latest: Pulling from library/nginx
Digest: sha256:xxxx
Status: Downloaded newer image
```

---

## 4.3 Remove Image

```bash
docker rmi nginx
```

Remove image by ID:

```bash
docker rmi image_id
```

Force remove:

```bash
docker rmi -f image_id
```

---

## 4.4 Build Image

Build image from Dockerfile.

```bash
docker build -t myapp .
```

Options:

| Option | Meaning       |
| ------ | ------------- |
| `-t`   | tag image     |
| `.`    | build context |

Example:

```
Successfully built image myapp
```

---

## 4.5 Inspect Image

```bash
docker inspect nginx
```

Shows:

* layers
* configuration
* environment variables
* network settings

---

## 4.6 Image History

```bash
docker history nginx
```

Shows all image layers.

Example:

```
IMAGE      CREATED      SIZE
abc123     2 days ago   50MB
xyz456     3 days ago   20MB
```

---

# 5. Docker Container Commands

Containers are running instances of images.

---

## 5.1 Run Container

```bash
docker run nginx
```

Options:

| Option   | Meaning        |
| -------- | -------------- |
| `-d`     | detached mode  |
| `-p`     | port mapping   |
| `--name` | container name |

Example:

```bash
docker run -d -p 80:80 nginx
```

Explanation:

```
80:80
host_port : container_port
```

---

## 5.2 List Running Containers

```bash
docker ps
```

Example:

```
CONTAINER ID   IMAGE   STATUS
123abc         nginx   Up 2 minutes
```

---

## 5.3 List All Containers

```bash
docker ps -a
```

Shows:

* running
* stopped containers

---

## 5.4 Stop Container

```bash
docker stop container_id
```

Stops running container.

---

## 5.5 Start Container

```bash
docker start container_id
```

Start stopped container.

---

## 5.6 Restart Container

```bash
docker restart container_id
```

Stops and starts container again.

---

## 5.7 Remove Container

```bash
docker rm container_id
```

Force remove:

```bash
docker rm -f container_id
```

---

## 5.8 Execute Command Inside Container

```bash
docker exec -it container_id bash
```

Explanation:

| Option | Meaning     |
| ------ | ----------- |
| `-i`   | interactive |
| `-t`   | terminal    |

Used to access container shell.

---

## 5.9 View Container Logs

```bash
docker logs container_id
```

Real-time logs:

```bash
docker logs -f container_id
```

---

## 5.10 Inspect Container

```bash
docker inspect container_id
```

Shows:

* network settings
* IP address
* volumes
* environment variables

---

# 6. Docker Network Commands

Docker networking connects containers.

---

## List Networks

```bash
docker network ls
```

Default networks:

```
bridge
host
none
```

---

## Create Network

```bash
docker network create mynetwork
```

---

## Inspect Network

```bash
docker network inspect mynetwork
```

---

## Remove Network

```bash
docker network rm mynetwork
```

---

# 7. Docker Volume Commands

Volumes store persistent data.

---

## List Volumes

```bash
docker volume ls
```

---

## Create Volume

```bash
docker volume create myvolume
```

---

## Inspect Volume

```bash
docker volume inspect myvolume
```

---

## Remove Volume

```bash
docker volume rm myvolume
```

---

# 8. Docker System Commands

---

## Show Disk Usage

```bash
docker system df
```

Shows space used by:

* images
* containers
* volumes

---

## Remove Unused Resources

```bash
docker system prune
```

Removes:

* stopped containers
* unused images
* unused networks

Force clean:

```bash
docker system prune -a
```

---

# 9. Docker Registry Commands

Images are stored in registries like Docker Hub.

---

## Login to Registry

```bash
docker login
```

Enter:

* username
* password

---

## Push Image

```bash
docker push username/myimage
```

Uploads image to registry.

---

## Pull Image

```bash
docker pull username/myimage
```

Downloads image.

---

# 10. Docker Compose Commands

Docker Compose manages multi-container apps using YAML files.

Commands belong to Docker Compose.

---

## Start Services

```bash
docker compose up
```

Run in background:

```bash
docker compose up -d
```

---

## Stop Services

```bash
docker compose down
```

---

## View Running Services

```bash
docker compose ps
```

---

# 11. Most Used Docker Commands (Quick List)

| Command           | Purpose                      |
| ----------------- | ---------------------------- |
| docker run        | Run container                |
| docker build      | Build image                  |
| docker pull       | Download image               |
| docker push       | Upload image                 |
| docker ps         | List running containers      |
| docker stop       | Stop container               |
| docker start      | Start container              |
| docker rm         | Remove container             |
| docker images     | List images                  |
| docker exec       | Run command inside container |
| docker logs       | View container logs          |
| docker network ls | List networks                |
| docker volume ls  | List volumes                 |

---

# 12. Real Workflow Example

Example running a web server:

Step 1 pull image

```bash
docker pull nginx
```

Step 2 run container

```bash
docker run -d -p 80:80 nginx
```

Step 3 check running containers

```bash
docker ps
```

Step 4 stop container

```bash
docker stop container_id
```

Step 5 remove container

```bash
docker rm container_id
```

---

# 13. Docker Command Structure

Full command structure:

```
docker [object] [command] [options]
```

Example:

```
docker container run nginx
docker image pull ubuntu
docker volume create data
docker network create mynet
```

---

# Summary

Docker commands allow you to manage container environments efficiently.

Main categories:

| Category             | Commands                   |
| -------------------- | -------------------------- |
| Image management     | build, pull, push, images  |
| Container management | run, stop, start, rm       |
| Networking           | network create, network ls |
| Storage              | volume create, volume ls   |
| System               | system prune, system df    |
| Registry             | login, push, pull          |

---

If you want, I can also give:

* **50 most important Docker commands for DevOps**
* **Docker commands cheat sheet (one-page)**
* **Docker interview questions with answers**
* **Real project workflow using Docker**.
