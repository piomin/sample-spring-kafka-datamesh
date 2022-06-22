package pl.piomin.datamesh.transactions.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import pl.piomin.datamesh.transactions.domain.Transaction;
import pl.piomin.datamesh.transactions.domain.TransactionKey;

import java.util.Random;
import java.util.function.Supplier;

@Configuration
public class KafkaEventProducer {

    private static long transactionId = 0;
    private static final Random r = new Random();

//    @Bean
    public Supplier<Message<Transaction>> transactionsSupplier() {
        return () -> {
            Transaction t = new Transaction();
            t.setId(++transactionId);
            t.setSourceAccountId(r.nextLong(1, 100));
            t.setTargetAccountId(r.nextLong(1, 100));
            t.setAmount(r.nextInt(1, 10000));
            Message<Transaction> o = MessageBuilder
                    .withPayload(t)
                    .setHeader(KafkaHeaders.MESSAGE_KEY, new TransactionKey(t.getId()))
                    .build();
            return o;
        };
    }
}
