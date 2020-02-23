package ro.challenge.atm.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ro.challenge.atm.AmountException;
import ro.challenge.atm.model.Account;
import ro.challenge.atm.repository.AccountRepository;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class AccountServiceTest {
    private Account account;

    @MockBean
    AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        account = new Account("", "");
        AccountRepository accountRepository = mock(AccountRepository.class);
    }

    @Test
    void addOneAmount() throws AmountException {
        when(accountRepository.save(account)).thenReturn(account);
        accountService.addAmount(account, BigDecimal.ONE);
        assertEquals(BigDecimal.ONE, account.getBalance());
    }

    @Test
    void addZeroAmount() throws AmountException {
        when(accountRepository.save(account)).thenReturn(account);
        accountService.addAmount(account, BigDecimal.ZERO);
        assertEquals(BigDecimal.ZERO, account.getBalance());
    }

    @Test
    void addNullAmount() {
        when(accountRepository.save(account)).thenReturn(account);
        Assertions.assertThrows(AmountException.class, () -> {
            accountService.addAmount(account, null);
        });
    }

    @Test
    void addNegativeAmount() {
        when(accountRepository.save(account)).thenReturn(account);
        Assertions.assertThrows(AmountException.class, () -> {
            accountService.addAmount(account, BigDecimal.valueOf(-1000L));
        });
    }

    @Test
    void subtractOneAmount() {
        when(accountRepository.save(account)).thenReturn(account);
        Assertions.assertThrows(AmountException.class, () -> {
            accountService.subtractAmount(account, BigDecimal.ONE);
        });
    }

    @Test
    void subtractNegativeAmount() {
        when(accountRepository.save(account)).thenReturn(account);
        Assertions.assertThrows(AmountException.class, () -> {
            accountService.subtractAmount(account, BigDecimal.valueOf(-1000L));
        });
    }
}