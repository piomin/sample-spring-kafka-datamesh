
```shell
docker run -it --rm --name connect --network redpanda -p 8083:8083 -e BOOTSTRAP_SERVERS='rp-node-0:9092' -e GROUP_ID=1 -e CONFIG_STORAGE_TOPIC=my-connect-configs-2 -e OFFSET_STORAGE_TOPIC=my-connect-offsets-2 -e CLUSTER_ID=0 debezium/connect
```
