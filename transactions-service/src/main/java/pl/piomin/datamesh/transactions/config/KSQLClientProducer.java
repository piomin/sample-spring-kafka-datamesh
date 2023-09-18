package pl.piomin.datamesh.transactions.config;

import io.confluent.ksql.api.client.Client;
import io.confluent.ksql.api.client.ClientOptions;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ConfigurationProperties("spring.ksql-server")
public class KSQLClientProducer {

    private String host;
    private int port;

    @Bean
    @Scope("prototype")
    Client ksqlClient() {
        ClientOptions options = ClientOptions.create()
                .setHost(host)
                .setPort(port);
        return Client.create(options);
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
