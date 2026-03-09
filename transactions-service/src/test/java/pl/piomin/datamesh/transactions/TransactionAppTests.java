package pl.piomin.datamesh.transactions;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.kafka.KafkaContainer;

@SpringBootTest(properties = "springwolf.enabled=false")
@Testcontainers
public class TransactionAppTests {



    @Container
//    @ServiceConnection
//    static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.8.6"))
    static KafkaContainer kafka = new KafkaContainer("apache/kafka-native:3.8.0")
//            .withExposedPorts(9092)
//            .withCreateContainerCmdModifier(cmd -> cmd.withName("broker"))
            .withNetwork(Network.SHARED)
            .withNetworkAliases("broker");

    @Container
    static final GenericContainer ksqlServer = new GenericContainer("confluentinc/ksqldb-server:0.26.0")
//            .dependsOn(kafka)
            .withExposedPorts(8088)
            .withNetwork(Network.SHARED)
            .withEnv("KSQL_BOOTSTRAP_SERVERS", "broker:9092")
            .withEnv("KSQL_LISTENERS", "http://0.0.0.0:8088");

    @DynamicPropertySource
    static void kafkaProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
        registry.add("spring.ksql-server.host", ksqlServer::getHost);
        registry.add("spring.ksql-server.port", () -> String.valueOf(ksqlServer.getMappedPort(8088)));
    }

    @Test
    void startup() {
        System.out.println("ok");
    }

}
