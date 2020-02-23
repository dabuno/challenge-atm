package ro.challenge.atm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.challenge.atm.AmountException;
import ro.challenge.atm.model.Account;
import ro.challenge.atm.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> findAll() {

        Iterable<Account> it = accountRepository.findAll();

        List<Account> accounts = new ArrayList<>();
        it.forEach(e -> accounts.add(e));

        return accounts;
    }

    public Account findById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.orElseGet(() -> new Account("", "", BigDecimal.ZERO));

    }

    public void addAmount(Account account, BigDecimal amount) throws AmountException {
        if (amount == null) {
            throw new AmountException(amount + " is missing.");
        }

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new AmountException("Amount " + amount + " cannot be negative.");
        }

        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
    }

    public void subtractAmount(Account account, BigDecimal amount) throws AmountException {
        if (amount == null) {
            throw new AmountException(amount + " is missing.");
        }

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new AmountException("Amount " + amount + " cannot be negative.");
        }

        if (amount.compareTo(account.getBalance()) > 0) {
            throw new AmountException("Not enough money avaiable to withdraw "+ amount + ".");
        }

        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);
    }


}
