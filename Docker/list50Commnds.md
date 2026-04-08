Below are the **50 Most Important Docker Commands for DevOps Engineers**.
I grouped them by **category** so it becomes easier to remember and use in **real DevOps workflows**.

---

# 50 Most Important Docker Commands for DevOps

Docker CLI commands are executed using the tool from Docker.

Basic command syntax:

```bash
docker [object] [command] [options]
```

Example:

```bash
docker container run nginx
```

---

# 1. Basic Docker System Commands (1–6)

These commands help check Docker installation and system status.

### 1. Check Docker version

```bash
docker --version
```

### 2. Show detailed system information

```bash
docker info
```

### 3. Show Docker help

```bash
docker --help
```

### 4. Show disk usage

```bash
docker system df
```

### 5. Remove unused resources

```bash
docker system prune
```

### 6. Remove all unused images, containers, networks

```bash
docker system prune -a
```

---

# 2. Docker Image Commands (7–16)

Images are templates used to create containers.

### 7. List images

```bash
docker images
```

or

```bash
docker image ls
```

### 8. Pull image from registry (e.g., Docker Hub)

```bash
docker pull nginx
```

### 9. Build image from Dockerfile

```bash
docker build -t myapp .
```

### 10. Remove image

```bash
docker rmi image_id
```

### 11. Remove image forcefully

```bash
docker rmi -f image_id
```

### 12. Tag image

```bash
docker tag image_id username/myimage:latest
```

### 13. Push image to registry

```bash
docker push username/myimage
```

### 14. Inspect image

```bash
docker inspect nginx
```

### 15. View image layers history

```bash
docker history nginx
```

### 16. Save image to tar file

```bash
docker save -o image.tar nginx
```

---

# 3. Docker Container Commands (17–34)

Containers are running instances of images.

### 17. Run container

```bash
docker run nginx
```

### 18. Run container in background

```bash
docker run -d nginx
```

### 19. Run container with port mapping

```bash
docker run -p 80:80 nginx
```

### 20. Run container with name and network and volume

```bash
docker run --name mycontainer --network networkName -v volName:Path nginx
```

### 21. List running containers

```bash
docker ps
```

### 22. List all containers

```bash
docker ps -a
```

### 23. Start container

```bash
docker start container_id
```

### 24. Stop container

```bash
docker stop container_id
```

### 25. Restart container

```bash
docker restart container_id
```

### 26. Pause container

```bash
docker pause container_id
```

### 27. Unpause container

```bash
docker unpause container_id
```

### 28. Remove container

```bash
docker rm container_id
```

### 29. Force remove container

```bash
docker rm -f container_id
```

### 30. Execute command inside container

```bash
docker exec -it container_id bash
```

### 31. View container logs

```bash
docker logs container_id
```

### 32. Follow container logs

```bash
docker logs -f container_id
```

### 33. Copy files from container

```bash
docker cp container_id:/file.txt .
```

### 34. Show container processes

```bash
docker top container_id
```

---

# 4. Docker Network Commands (35–40)

These commands manage container networking.

### 35. List networks

```bash
docker network ls
```

### 36. Create network

```bash
docker network create --driver driverName mynetwork
```

### 37. Inspect network

```bash
docker network inspect mynetwork
```

### 38. Connect container to network

```bash
docker network connect mynetwork container_id
```

### 39. Disconnect container from network

```bash
docker network disconnect mynetwork container_id
```

### 40. Remove network

```bash
docker network rm mynetwork
```

---

# 5. Docker Volume Commands (41–45)

Volumes store persistent data.

### 41. List volumes

```bash
docker volume ls
```

### 42. Create volume

```bash
docker volume create myvolume
```

### 43. Inspect volume

```bash
docker volume inspect myvolume
```

### 44. Remove volume

```bash
docker volume rm myvolume
```

### 45. Remove unused volumes

```bash
docker volume prune
```

---

# 6. Docker Registry Commands (46–48)

Used for working with registries like Docker Hub.

### 46. Login to registry

```bash
docker login
```

### 47. Logout from registry

```bash
docker logout
```

### 48. Search images in registry

```bash
docker search nginx
```

---

# 7. Docker Compose Commands (49–50)

These commands belong to Docker Compose and manage multi-container applications.

### 49. Start services

```bash
docker compose up
```

Background mode:

```bash
docker compose up -d
```

### 50. Stop services

```bash
docker compose down
```

---

# Example DevOps Workflow Using Docker Commands

Typical workflow:

### Step 1 Build image

```bash
docker build -t myapp .
```

### Step 2 Run container

```bash
docker run -d -p 8080:80 myapp
```

### Step 3 View logs

```bash
docker logs container_id
```

### Step 4 Push image to registry

```bash
docker push username/myapp
```

### Step 5 Deploy using compose

```bash
docker compose up -d
```

---

# Most Frequently Used Commands (Top 10)

DevOps engineers use these most:

```
docker build
docker run
docker ps
docker pull
docker push
docker logs
docker exec
docker stop
docker rm
docker compose up
```

---

✅ **Summary**

Docker commands mainly fall into these categories:

| Category             | Commands               |
| -------------------- | ---------------------- |
| System               | info, system df, prune |
| Images               | build, pull, push      |
| Containers           | run, stop, start       |
| Networks             | network create         |
| Storage              | volume create          |
| DevOps orchestration | compose up             |

---

If you want, I can also show you **something very useful for DevOps learning**:

* **Complete Docker command cheat sheet (100 commands)**
* **Docker interview questions (50+ questions)**
* **Real DevOps project using Docker + CI/CD**
* **Dockerfile explained line-by-line**.
