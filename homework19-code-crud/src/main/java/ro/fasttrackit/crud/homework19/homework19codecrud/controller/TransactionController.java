package ro.fasttrackit.crud.homework19.homework19codecrud.controller;

import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.crud.homework19.homework19codecrud.exception.TransactionNotFoundException;
import ro.fasttrackit.crud.homework19.homework19codecrud.model.Transaction;
import ro.fasttrackit.crud.homework19.homework19codecrud.model.Type;
import ro.fasttrackit.crud.homework19.homework19codecrud.service.TransactionService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    List<Transaction> getAllTransactionsByProducts(@RequestParam(required = false) String transaction) {
        return transactionService.getTransactionByProducts(transaction);
    }

    @GetMapping
    List<Transaction> getHigherTransactions(@RequestParam(required = false) double min) {
        return transactionService.getHigherTransactions(min);
    }

    @GetMapping
    List<Transaction> getLowerTransactions(@RequestParam(required = false) double max) {
        return transactionService.getLowerTransactions(max);
    }

    @GetMapping
    List<Transaction> getAllTransactionsByType(@RequestParam(required = false) Type type) {
        return transactionService.getTransactionByType(type);
    }

    @GetMapping("/{transactionId}")
    Transaction getTransaction(@PathVariable int transactionId) {
        return transactionService.getTransaction(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction with id " + transactionId + " cannot be found!"));
    }

    @PostMapping
    Transaction addTransaction(@RequestBody Transaction transaction) {
        return transactionService.addTransaction(transaction);
    }

    @PutMapping("{transactionId}")
    Transaction replaceTransaction(@PathVariable int transactionId, @RequestBody Transaction transaction) {
        return transactionService.replaceTransaction(transactionId, transaction);
    }


    @PatchMapping("{transactionId}")
    Transaction updateTransaction(@PathVariable int transactionId, Transaction transaction) {
        return transactionService.updateTransaction(transactionId, transaction);
    }

    @DeleteMapping("{transactionId}")
    Transaction deleteTransaction(@PathVariable int transactionId) {
        return transactionService.deleteTransaction(transactionId);
    }

    @GetMapping("/reports/{type}")
    Map<Type, Double> mapTypeToSum(@PathVariable Type type) {
        return transactionService.mapTypeToSum(type);
    }

    @GetMapping("/reports/{product}")
    Map<String, Double> mapProductToSum(@PathVariable String product) {
        return transactionService.mapProductToSum(product);
    }
}
