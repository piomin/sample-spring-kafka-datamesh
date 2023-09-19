# Kafka with Spring Boot Advanced Demo Project [![Twitter](https://img.shields.io/twitter/follow/piotr_minkowski.svg?style=social&logo=twitter&label=Follow%20Me)](https://twitter.com/piotr_minkowski)

[![CircleCI](https://circleci.com/gh/piomin/sample-spring-kafka-datamesh.svg?style=svg)](https://circleci.com/gh/piomin/sample-spring-kafka-datamesh)

[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-black.svg)](https://sonarcloud.io/dashboard?id=piomin_sample-spring-kafka-datamesh)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=piomin_sample-spring-kafka-datamesh&metric=bugs)](https://sonarcloud.io/dashboard?id=piomin_sample-spring-kafka-datamesh)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=piomin_sample-spring-kafka-datamesh&metric=coverage)](https://sonarcloud.io/dashboard?id=piomin_sample-spring-kafka-datamesh)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=piomin_sample-spring-kafka-datamesh&metric=ncloc)](https://sonarcloud.io/dashboard?id=piomin_sample-spring-kafka-datamesh)

```shell
docker run -it --rm --name connect --network redpanda -p 8083:8083 -e BOOTSTRAP_SERVERS='rp-node-0:9092' -e GROUP_ID=1 -e CONFIG_STORAGE_TOPIC=my-connect-configs-2 -e OFFSET_STORAGE_TOPIC=my-connect-offsets-2 -e CLUSTER_ID=0 debezium/connect
```
