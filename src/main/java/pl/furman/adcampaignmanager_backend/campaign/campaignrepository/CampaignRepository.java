package pl.furman.adcampaignmanager_backend.campaign.campaignrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.furman.adcampaignmanager_backend.campaign.Campaign;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
}
