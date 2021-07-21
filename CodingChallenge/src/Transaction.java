import java.time.LocalDateTime;

/**
 * A class to create a transaction when they are added to the program via the transactionRecord.
 */
public class Transaction {

    private String transactionId;
    private String fromAccountId;
    private String toAccountId;
    private LocalDateTime createAt;
    private double amount;
    private String transactionType;
    private String relatedTransaction;

    public Transaction(String transactionId, String fromAccountId, String toAccountId, LocalDateTime createAt, double amount, String transactionType, String relatedTransaction) {
        this.transactionId = transactionId;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.createAt = createAt;
        this.amount = amount;
        this.transactionType = transactionType;
        this.relatedTransaction = relatedTransaction;
    }

    public Transaction() {
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getFromAccountId() {
        return fromAccountId;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getRelatedTransaction() {
        return relatedTransaction;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setFromAccountId(String fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public void setToAccountId(String toAccountId) {
        this.toAccountId = toAccountId;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void setRelatedTransaction(String relatedTransaction) {
        this.relatedTransaction = relatedTransaction;
    }
    @Override
    public String toString() {
        return "id: "+ transactionId +" from: "+fromAccountId +" to: "+toAccountId+ "amount: "+getAmount()+" related tran: "+getRelatedTransaction();
    }

}


