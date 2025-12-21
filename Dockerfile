FROM node:22-bullseye

# 1. Install alat kompilasi untuk node-pty
RUN apt-get update && apt-get install -y \
    python3 \
    make \
    g++ \
    build-essential \
    curl \
    gnupg \
    lsb-release

# 2. Install Docker CLI (Memperbaiki error tee/directory)
RUN mkdir -p /etc/apt/keyrings && \
    curl -fsSL https://download.docker.com/linux/debian/gpg | gpg --dearmor -o /etc/apt/keyrings/docker.gpg && \
    echo "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/debian $(lsb_release -cs) stable" > /etc/apt/sources.list.d/docker.list && \
    apt-get update && apt-get install -y docker-ce-cli

RUN npm install -g nodemon

WORKDIR /app

# 3. Handle dependencies
# Kita copy package.json dulu agar npm install di-cache oleh Docker
COPY package*.json ./
RUN npm install

# 4. Copy source code sisanya
COPY . .

RUN mkdir -p /app/temp && chmod -R 777 /app/temp

EXPOSE 5000

CMD ["nodemon", "-L", "index.js"]