package ro.fasttrackit.crud.homework19.homework19codecrud.service;

import org.springframework.stereotype.Service;
import ro.fasttrackit.crud.homework19.homework19codecrud.exception.TransactionNotFoundException;
import ro.fasttrackit.crud.homework19.homework19codecrud.model.Transaction;
import ro.fasttrackit.crud.homework19.homework19codecrud.model.Type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private List<Transaction> transactions;

    public TransactionService(OriginalTransactions originalTransactions) {
        this.transactions = originalTransactions.getOriginalTransactions();
    }


    public List<Transaction> getTransactionByProducts(String productName) {
        if (productName != null) {
            return transactions.stream()
                    .filter(transaction -> transaction.getProduct().equalsIgnoreCase(productName))
                    .collect(Collectors.toList());
        } else {
            return transactions;
        }
    }


    public List<Transaction> getTransactionByType(Type type) {
        if (type != null) {
            return transactions.stream()
                    .filter(transaction -> transaction.getType().equals(type))
                    .collect(Collectors.toList());
        } else {
            return transactions;
        }
    }


    public List<Transaction> getHigherTransactions(double amount) {
        if (amount > 0) {
            return transactions.stream()
                    .filter(transaction -> transaction.getAmount() >= amount)
                    .collect(Collectors.toList());
        } else {
            return transactions;
        }
    }

    public List<Transaction> getLowerTransactions(double amount) {
        if (amount > 0) {
            return transactions.stream()
                    .filter(transaction -> transaction.getAmount() <= amount)
                    .collect(Collectors.toList());
        } else {
            return transactions;
        }

    }


    public Optional<Transaction> getTransaction(int id) {
        return transactions.stream()
                .filter(transaction -> transaction.getId() == id)
                .findFirst();
    }

    public Transaction addTransaction(Transaction transaction) {
        transaction.setId(calculateId());
        transactions.add(transaction);
        return transaction;
    }

    private int calculateId() {
        return transactions.stream()
                .mapToInt(Transaction::getId)
                .max()
                .orElse(0) + 1;
    }

    public Transaction replaceTransaction(int transactionId, Transaction transaction) {
        transaction.setId(transactionId);
        deleteTransaction(transactionId);
        addTransaction(transaction);
        return transaction;
    }

    public Transaction deleteTransaction(int transactionId) {
        Transaction transaction = getTransactionOrThrowError(transactionId);

        transactions.remove(transaction);
        return transaction;

    }

    public Transaction updateTransaction(int transactionId, Transaction newTransaction) {
        Transaction oldTransaction = getTransactionOrThrowError(transactionId);
        String product = oldTransaction.getProduct();
        if (newTransaction.getProduct() != null) {
            product = newTransaction.getProduct();
        }
        double amount = 3;

        return new Transaction(

                product,
                oldTransaction.getType(),
                amount
        );
    }

    public Map<Type, Double> mapTypeToSum(Type type) {
        Map<Type, Double> result = new HashMap<>();
        for (Transaction transaction : transactions) {
            if (transaction.getType().equals(type)) {
                Double sum = result.get(transaction.getType());
                if (sum == null) {
                    sum = 0.0;
                }
                sum += transaction.getAmount();
                result.put(transaction.getType(), sum);
            }

        }
        return result;
    }

    public Map<String, Double> mapProductToSum(String product) {
        Map<String, Double> result = new HashMap<>();
        for (Transaction transaction : transactions) {
            if (transaction.getProduct().equalsIgnoreCase(product)) {
                Double sum = result.get(transaction.getProduct());
                if (sum == null) {
                    sum = 0.0;
                }
                sum += transaction.getAmount();
                result.put(transaction.getProduct(), sum);
            }

        }
        return result;
    }

    private Transaction getTransactionOrThrowError(int id) {
        return getTransaction(id).orElseThrow(() -> new TransactionNotFoundException(
                "Transanction with id " + id + " cannot be found"));
    }


}
