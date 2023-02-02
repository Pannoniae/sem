:: the deployinator™ automates a local deployment. With docker and all.
docker network create --driver bridge se-methods
docker pull mongo
docker run -d --name mongo-dbserver --network se-methods mongo
mvn package
docker build -t se_methods .
docker run --network se-methods --name semcontainer se_methods