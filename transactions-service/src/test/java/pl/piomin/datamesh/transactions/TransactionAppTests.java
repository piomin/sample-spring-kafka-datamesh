package pl.piomin.datamesh.transactions;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Testcontainers
public class TransactionAppTests {

    private static Network NET = Network.newNetwork();

    @Container
    static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.1"))
//            .withKraft()
            .withExposedPorts(9093, 9092)
            .withCreateContainerCmdModifier(cmd -> cmd.withName("broker"))
            .withNetwork(NET);

    @Container
    static final GenericContainer ksqlServer = new GenericContainer("confluentinc/ksqldb-server:0.26.0")
            .dependsOn(kafka)
            .withExposedPorts(8088)
            .withNetwork(NET)
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
