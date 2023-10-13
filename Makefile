start:
	echo "#### Building project #####"
	./gradlew clean build
	echo "#### Generating JAR #####"
	./gradlew bootJar
	echo "#### Starting docker container #####"
	docker-compose up

stop:
	echo "#### Finishing docker container #####"
	docker-compose down
	echo "#### Removing app image #####"
	docker rmi restaurant-service

restart:
	make stop
	make start