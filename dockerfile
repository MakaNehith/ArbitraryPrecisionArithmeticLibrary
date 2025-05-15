FROM openjdk:23-slim

# Install Ant and Python3
RUN apt-get update && \
    apt-get install -y ant python3 python3-pip curl && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY . .

CMD ["bash"]