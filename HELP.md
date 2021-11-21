# Getting Started

### To start a server use command

`docker-compose up`

### To publish new version

Build this project (generate new .jar file)

`docker build -t qbixer/trips-backend .`

`docker push qbixer/trips-backend:latest`
