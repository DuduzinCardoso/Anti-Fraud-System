package antifraud.controller;

import antifraud.dto.TransactionDto;
import antifraud.dto.TransactionResultDto;
import antifraud.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/antifraud/transaction")
    public ResponseEntity<TransactionResultDto> antifraudTransaction(@RequestBody TransactionDto transactionDto){
        return ResponseEntity.status(200).body(transactionService.antifraudTransaction(transactionDto));
    }
}