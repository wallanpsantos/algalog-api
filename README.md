# algalog-api
Projeto API desenvolvido durante o Mergulho Spring REST by: AlgaWorks

Java: openjdk-11

BD: mysql-server


### DOCKER

1 -  docker volume create --name algalog-volume

2 -  docker container run -d --name db-algalog-mysql -v algalog-volume:/var/lib/mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=estudo mysql:8.0.26

3 -  docker exec -it db-algalog-mysql bash

4 -  mysql -u root -p

5 -  A senha é: estudo

