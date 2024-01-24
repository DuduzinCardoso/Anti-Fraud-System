package antifraud.service;

import antifraud.dto.TransactionDto;
import antifraud.dto.TransactionEnum;
import antifraud.dto.TransactionResultDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TransactionService {

    public TransactionResultDto antifraudTransaction(TransactionDto transactionDto) {
        String transactionResult;
        if (transactionDto.getAmount() == null || transactionDto.getAmount() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        else if (transactionDto.getAmount() <= 200){
            transactionResult = String.valueOf(TransactionEnum.ALLOWED);
        } else if (transactionDto.getAmount() <= 1500) {
            transactionResult = String.valueOf(TransactionEnum.MANUAL_PROCESSING);
        } else {
            transactionResult = String.valueOf(TransactionEnum.PROHIBITED);
        }
        return new TransactionResultDto(transactionResult);
    }
}