package ro.challenge.atm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private BigDecimal balance = BigDecimal.ZERO;

    protected Account() {
    }

    public Account(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Account(String firstName, String lastName, BigDecimal balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format(
                "Account[id=%d, firstName='%s', lastName='%s', balance='%f']",
                id, firstName, lastName, balance);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Account addAmount(BigDecimal amount) {
        balance = balance.add(amount);
        return this;
    }

    public Account subtractAmount(BigDecimal amount) {
        balance = balance.subtract(amount);
        return this;
    }
}
