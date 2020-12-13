package ro.fasttrackit.crud.homework19.homework19codecrud.service;

import org.springframework.stereotype.Service;
import ro.fasttrackit.crud.homework19.homework19codecrud.model.Transaction;
import ro.fasttrackit.crud.homework19.homework19codecrud.model.Type;

import java.util.List;

@Service
public class OriginalTransactions {

    public List<Transaction> getOriginalTransactions() {
        int i = 1;
        List<Transaction> transactions = List.of(new Transaction("masina", Type.BUY, 5950.99),
                new Transaction("sarmale", Type.BUY, 7.4),
                new Transaction("laptop", Type.SELL, 749.89),
                new Transaction("laptop", Type.BUY, 320.89),
                new Transaction("casa", Type.SELL, 80200),
                new Transaction("televizor", Type.SELL, 300),
                new Transaction("televizor", Type.SELL, 850),
                new Transaction("telefon", Type.BUY, 200),
                new Transaction("telefon", Type.SELL, 100),
                new Transaction("telefon", Type.BUY, 500)

        );
        for (Transaction transaction : transactions) {
            transaction.setId(i++);

        }
        return transactions;
    }
}
