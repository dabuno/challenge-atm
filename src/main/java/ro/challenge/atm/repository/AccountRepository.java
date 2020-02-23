package ro.challenge.atm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.challenge.atm.model.Account;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findByLastName(String lastName);

    Account findById(long id);
}
