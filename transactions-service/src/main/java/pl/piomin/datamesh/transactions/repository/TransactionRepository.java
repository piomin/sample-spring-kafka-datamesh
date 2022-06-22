package pl.piomin.datamesh.transactions.repository;

import org.springframework.data.repository.CrudRepository;
import pl.piomin.datamesh.transactions.domain.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}
