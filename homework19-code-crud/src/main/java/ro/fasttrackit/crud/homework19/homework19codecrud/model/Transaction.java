package ro.fasttrackit.crud.homework19.homework19codecrud.model;

import java.util.Objects;

public class Transaction {
    private final String product;
    private final Type type;
    private final double amount;
    private int id;

    public Transaction(String product, Type type, double amount) {

        this.product = product;
        this.type = type;
        this.amount = amount;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public Type getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction transaction1 = (Transaction) o;
        return id == transaction1.id &&
                Double.compare(transaction1.amount, amount) == 0 &&
                Objects.equals(product, transaction1.product) &&
                type == transaction1.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, type, amount);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", type=" + type +
                ", amount=" + amount +
                '}';
    }
}
