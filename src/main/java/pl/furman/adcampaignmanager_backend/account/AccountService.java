package pl.furman.adcampaignmanager_backend.account;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.furman.adcampaignmanager_backend.account.accountrepository.AccountRepository;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccount() {
        return accountRepository.findById(1L)
                .orElseGet(() -> accountRepository.save(new Account()));
    }

    public double getBalance() {
        return getAccount().getBalance();
    }

    @Transactional
    public void deposit(double amount) {
        Account account = getAccount();
        account.deposit(amount);
        accountRepository.save(account);
    }

    @Transactional
    public boolean withdraw(double amount) {
        Account account = getAccount();
        if (account.withdraw(amount)) {
            accountRepository.save(account);
            return true;
        }
        return false;
    }
}