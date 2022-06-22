package pl.piomin.datamesh.transactions.resource;

import io.confluent.ksql.api.client.Client;
import io.confluent.ksql.api.client.ExecuteStatementResult;
import io.confluent.ksql.api.client.Row;
import io.confluent.ksql.api.client.StreamedQueryResult;
import io.confluent.ksql.api.client.exception.KsqlClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.piomin.datamesh.transactions.domain.Transaction;
import pl.piomin.datamesh.transactions.repository.TransactionRepository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/transactions")
public class TransactionResource {

    private static final Logger LOG = LoggerFactory.getLogger(TransactionResource.class);
    Client ksqlClient;

    public TransactionResource(Client ksqlClient) {
        this.ksqlClient = ksqlClient;
    }

    @GetMapping
    public List<Transaction> getTransactions() throws ExecutionException, InterruptedException {
        StreamedQueryResult sqr = ksqlClient
                .streamQuery("SELECT * FROM transactions_view;")
                .get();
        Row row;
        List<Transaction> l = new ArrayList<>();
        while ((row = sqr.poll()) != null) {
            l.add(mapRowToTransaction(row));
        }
        return l;
    }

    @GetMapping("/target/{accountId}")
    public List<Transaction> getTransactionsByTargetAccountId(@PathVariable("accountId") Long accountId)
            throws ExecutionException, InterruptedException {
        StreamedQueryResult sqr = ksqlClient
                .streamQuery("SELECT * FROM transactions_view WHERE targetAccountId=" + accountId + ";")
                .get();
        Row row;
        List<Transaction> l = new ArrayList<>();
        while ((row = sqr.poll()) != null) {
            l.add(mapRowToTransaction(row));
        }
        return l;
    }

    private Transaction mapRowToTransaction(Row row) {
        Transaction t = new Transaction();
        t.setId(row.getLong("ID"));
        t.setSourceAccountId(row.getLong("SOURCEACCOUNTID"));
        t.setTargetAccountId(row.getLong("TARGETACCOUNTID"));
        t.setAmount(row.getInteger("AMOUNT"));
        return t;
    }

}
