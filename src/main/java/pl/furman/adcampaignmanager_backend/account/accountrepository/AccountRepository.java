package pl.furman.adcampaignmanager_backend.account.accountrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.furman.adcampaignmanager_backend.account.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
