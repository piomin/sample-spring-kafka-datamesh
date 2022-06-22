package pl.piomin.datamesh.transactions.domain;

public class TransactionKey {
    private Long id;

    public TransactionKey() {
    }

    public TransactionKey(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
