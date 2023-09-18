package pl.piomin.datamesh.transactions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.piomin.datamesh.transactions.domain.Transaction;

import java.util.function.Consumer;

@SpringBootApplication
public class TransactionApp {

    public static void main(String[] args) {
        SpringApplication.run(TransactionApp.class, args);
    }

    private static final Logger LOG = LoggerFactory.getLogger(TransactionApp.class);

    @Bean
    public Consumer<Transaction> transactions() {
        return trx -> LOG.info("Received: {}", trx);
    }

}
