package pl.furman.adcampaignmanager_backend.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.furman.adcampaignmanager_backend.account.AccountService;
import pl.furman.adcampaignmanager_backend.campaign.campaignrepository.CampaignRepository;

import java.util.List;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final AccountService accountService;
    private static final double MIN_BID_AMOUNT = 50.0;
    private static final List<String> AVAILABLE_TOWNS = List.of("Cracow", "Warsaw", "Wroclaw", "Gdansk", "Rzeszow", "Szczecin", "Poznan", "Katowice", "Lublin");

    @Autowired
    public CampaignService(CampaignRepository campaignRepository, AccountService accountService) {
        this.campaignRepository = campaignRepository;
        this.accountService = accountService;
    }

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    public Campaign createCampaign(Campaign campaign) {
        validateCampaign(campaign);
        transferMoney(campaign);
        return campaignRepository.save(campaign);
    }

    public void deleteCampaign(Long id) {
        campaignRepository.deleteById(id);
    }

    public Campaign updateCampaign(Long id, Campaign updatedCampaign) {
        Campaign existingCampaign = campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));

        if (updatedCampaign.getCampaignFund() != null) {
            updatedCampaign.setCampaignFund(existingCampaign.getCampaignFund());
        }

        validateCampaign(updatedCampaign);
        existingCampaign.setName(updatedCampaign.getName());
        existingCampaign.setKeywords(updatedCampaign.getKeywords());
        existingCampaign.setBidAmount(updatedCampaign.getBidAmount());
        existingCampaign.setCampaignFund(updatedCampaign.getCampaignFund());
        existingCampaign.setCampaignActive(updatedCampaign.isCampaignActive());
        existingCampaign.setTown(updatedCampaign.getTown());
        existingCampaign.setRadius(updatedCampaign.getRadius());

        return campaignRepository.save(existingCampaign);
    }

    public Campaign findById(Long id) {
        return this.campaignRepository.findById(id).orElseThrow(() -> new RuntimeException("Campaign not found"));
    }

    public void validateCampaign(Campaign campaign) {
        if (campaign.getName() == null || campaign.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Campaign name is required.");
        }
        if (campaign.getKeywords() == null || campaign.getKeywords().isEmpty()) {
            throw new IllegalArgumentException("At least one keyword is required.");
        }
        if (campaign.getBidAmount() < MIN_BID_AMOUNT) {
            throw new IllegalArgumentException("Bid amount must be at least " + MIN_BID_AMOUNT);
        }
        if (campaign.getCampaignFund() > accountService.getBalance() && campaign.getCampaignFund() > 0) {
            throw new IllegalArgumentException("Not enough balance for this campaign or incorrect fund.");
        }
        if (campaign.getTown() == null || campaign.getTown().trim().isEmpty()) {
            throw new IllegalArgumentException("Town is required.");
        }
        if (campaign.getRadius() <= 0) {
            throw new IllegalArgumentException("Radius must be greater than 0.");
        }
    }

    public void transferMoney(Campaign campaign) {
        accountService.withdraw(campaign.getCampaignFund());
    }

}
