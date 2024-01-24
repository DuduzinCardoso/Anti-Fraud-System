package antifraud.dto;

public class TransactionResultDto {
    private String result;

    public TransactionResultDto(String result) {
        this.result = result;
    }

    public TransactionResultDto() {

    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}