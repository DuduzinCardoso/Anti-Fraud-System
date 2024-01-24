package antifraud.dto;

public class TransactionDto {
    private Long amount;

    public TransactionDto(Long amount) {
        this.amount = amount;
    }

    public TransactionDto() {
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}