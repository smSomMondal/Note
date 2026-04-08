Below is a **complete real-world Dockerfile example** that uses most common instructions like **FROM, WORKDIR, COPY, RUN, ENV, ARG, EXPOSE, VOLUME, USER, ENTRYPOINT, CMD, LABEL, HEALTHCHECK**.

I’ll also explain **what each line does**, so you understand how a **production Dockerfile works**.

---

# Full Dockerfile Example (Production Style)

```dockerfile
# Base Image
FROM node:18-alpine

# Metadata
LABEL maintainer="devops@example.com"
LABEL version="1.0"
LABEL description="Node.js Web Application"

# Build-time variable
ARG APP_VERSION=1.0

# Environment variables
ENV NODE_ENV=production
ENV PORT=3000
ENV APP_VERSION=${APP_VERSION}

# Create application directory
WORKDIR /usr/src/app

# Copy package files
COPY package*.json ./

# Install dependencies
RUN npm install --only=production

# Copy application source code
COPY . .

# Create data directory
RUN mkdir /usr/src/app/data

# Persistent volume
VOLUME ["/usr/src/app/data"]

# Expose application port
EXPOSE 3000

# Create non-root user
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Switch to non-root user
USER appuser

# Health check
HEALTHCHECK --interval=30s --timeout=5s --start-period=10s \
CMD curl -f http://localhost:3000/health || exit 1

# Entry point
ENTRYPOINT ["node"]

# Default command
CMD ["server.js"]
```

---

# Explanation of Each Section

---

# 1. Base Image

```dockerfile
FROM node:18-alpine
```

This defines the **base image**.

* Uses Node.js version 18
* `alpine` is lightweight Linux

Benefits:

```
Smaller image size
Faster builds
Better security
```

---

# 2. Metadata Labels

```dockerfile
LABEL maintainer="devops@example.com"
LABEL version="1.0"
LABEL description="Node.js Web Application"
```

Labels store metadata.

Useful for:

* automation
* documentation
* CI/CD systems

View labels:

```bash
docker inspect image_name
```

---

# 3. Build Argument

```dockerfile
ARG APP_VERSION=1.0
```

Defines **build-time variables**.

Build command:

```bash
docker build --build-arg APP_VERSION=2.0 -t myapp .
```

Used only during build.

---

# 4. Environment Variables

```dockerfile
ENV NODE_ENV=production
ENV PORT=3000
ENV APP_VERSION=${APP_VERSION}
```

Environment variables are available **inside the container**.

Example usage in Node.js:

```javascript
process.env.PORT
```

View env variables:

```bash
docker exec container env
```

---

# 5. Working Directory

```dockerfile
WORKDIR /usr/src/app
```

Sets the working directory.

Equivalent to:

```
cd /usr/src/app
```

All next commands run inside this directory.

---

# 6. Copy Dependency Files

```dockerfile
COPY package*.json ./
```

Copies dependency files first.

This improves **Docker layer caching**.

If code changes but dependencies don't → Docker skips reinstalling.

---

# 7. Install Dependencies

```dockerfile
RUN npm install --only=production
```

Installs production dependencies.

`RUN` executes commands during **build stage**.

---

# 8. Copy Application Code

```dockerfile
COPY . .
```

Copies all application files.

Example files:

```
server.js
routes/
controllers/
config/
```

---

# 9. Create Data Directory

```dockerfile
RUN mkdir /usr/src/app/data
```

Creates folder for persistent data.

---

# 10. Define Volume

```dockerfile
VOLUME ["/usr/src/app/data"]
```

Creates **persistent storage**.

Data remains even if container is deleted.

Used for:

```
database files
uploads
logs
```

---

# 11. Expose Port

```dockerfile
EXPOSE 3000
```

Tells Docker the container uses port **3000**.

Run container:

```bash
docker run -p 3000:3000 myapp
```

Port mapping:

```
host_port : container_port
```

---

# 12. Create Non-Root User

```dockerfile
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
```

Creates secure user.

Best practice:

```
Never run containers as root
```

---

# 13. Switch User

```dockerfile
USER appuser
```

Runs container with limited privileges.

Improves security.

---

# 14. Health Check

```dockerfile
HEALTHCHECK --interval=30s --timeout=5s --start-period=10s \
CMD curl -f http://localhost:3000/health || exit 1
```

Docker checks if the application is alive.

If the health check fails:

```
container status → unhealthy
```

Used in orchestration systems like:

* Kubernetes

---

# 15. ENTRYPOINT

```dockerfile
ENTRYPOINT ["node"]
```

Defines the main executable.

---

# 16. CMD

```dockerfile
CMD ["server.js"]
```

Default command.

Final container command becomes:

```
node server.js
```

---

# Build and Run the Dockerfile

### Build image

```bash
docker build -t nodeapp .
```

---

### Run container

```bash
docker run -d -p 3000:3000 nodeapp
```

---

### Check running containers

```bash
docker ps
```

---

### View logs

```bash
docker logs container_id
```

---

# Real Production Dockerfile Flow

```
Dockerfile
   ↓
docker build
   ↓
Docker Image
   ↓
docker push (registry)
   ↓
docker run / Kubernetes deploy
   ↓
Container running application
```

---

# Best Practices Used in This Dockerfile

This example follows **DevOps production standards**:

✔ small base image
✔ non-root user
✔ health check
✔ environment variables
✔ build arguments
✔ persistent volumes
✔ proper layer caching

---

# Summary

This Dockerfile uses the most important instructions:

| Instruction | Purpose               |
| ----------- | --------------------- |
| FROM        | base image            |
| LABEL       | metadata              |
| ARG         | build-time variables  |
| ENV         | environment variables |
| WORKDIR     | working directory     |
| COPY        | copy files            |
| RUN         | execute commands      |
| VOLUME      | persistent storage    |
| EXPOSE      | define ports          |
| USER        | security              |
| HEALTHCHECK | container health      |
| ENTRYPOINT  | main command          |
| CMD         | default arguments     |

---

If you want, I can also show **something extremely useful for DevOps learning**:

1️⃣ **Advanced multi-stage Dockerfile (real production example)**
2️⃣ **Dockerfile optimization to reduce image size by 90%**
3️⃣ **Real microservice Dockerfiles (Node + MongoDB + Nginx)**
4️⃣ **Dockerfile interview questions (very common in DevOps interviews)**.
