package pl.piomin.datamesh.transactions.startup;

import io.confluent.ksql.api.client.Client;
import io.confluent.ksql.api.client.ExecuteStatementResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class KTableCreateListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(KTableCreateListener.class);
    private Client ksqlClient;

    public KTableCreateListener(Client ksqlClient) {
        this.ksqlClient = ksqlClient;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            String sql = """
                    CREATE SOURCE TABLE IF NOT EXISTS transactions_view (
                      id BIGINT PRIMARY KEY,
                      sourceAccountId BIGINT,
                      targetAccountId BIGINT,
                      amount INT
                    ) WITH (
                      kafka_topic='transactions',
                      value_format='JSON'
                    );
                    """;
            ExecuteStatementResult result = ksqlClient.executeStatement(sql).get();
            LOG.info("Result: {}", result.queryId().orElse(null));
        } catch (ExecutionException | InterruptedException e) {
            LOG.error("Error: ", e);
        }
    }
}
