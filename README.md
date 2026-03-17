# SDJ Demo Project

## Project Information
- Java version: 21
- Maven version (wrapper): 3.9.12
- Spring Boot version: 4.0.3
- Home URL: http://localhost:8081
- Swagger UI : http://localhost:8081/swagger-ui/index.html

## Run MySQL With Docker
This project includes a Docker Compose file for MySQL at `docker/docker-compose.yml`.

### MySQL container settings
- Image: `mysql:8.4`
- Container name: `sdj-demo-mysql`
- Host port: `3006`
- Container port: `3306`
- Root username: `root`
- Root password: `root`

### Start MySQL
```bash
docker compose -f docker/docker-compose.yml up -d
```

### Stop MySQL
```bash
docker compose -f docker/docker-compose.yml down
```
