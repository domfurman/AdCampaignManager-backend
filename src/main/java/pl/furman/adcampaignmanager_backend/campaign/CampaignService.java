package pl.furman.adcampaignmanager_backend.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.furman.adcampaignmanager_backend.campaign.campaignrepository.CampaignRepository;

import java.util.List;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;

    @Autowired
    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    public Campaign createCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    public void deleteCampaign(Long id) {
        campaignRepository.deleteById(id);
    }

    public Campaign updateCampaign(Long id, Campaign updatedCampaign) {
        Campaign existingCampaign = campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
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

}
