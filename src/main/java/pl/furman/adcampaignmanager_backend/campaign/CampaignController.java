package pl.furman.adcampaignmanager_backend.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CampaignController {

    private final CampaignService campaignService;

    @Autowired
    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @GetMapping("api/get-all-campaigns")
    public List<Campaign> getAllCampaigns() {
        return campaignService.getAllCampaigns();
    }

    @PostMapping("api/create-campaign")
    public ResponseEntity<Campaign> createCampaign(@RequestBody Campaign campaign) {
        return ResponseEntity.ok(campaignService.createCampaign(campaign));
    }

    @DeleteMapping("api/delete-campaign/{id}")
    public ResponseEntity<Void> deleteCampaign(@PathVariable Long id) {
        campaignService.deleteCampaign(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("api/update-campaign/{id}")
    public Campaign updateCampaign(@PathVariable Long id, @RequestBody Campaign updatedCampaign) {
        return campaignService.updateCampaign(id, updatedCampaign);
    }

    @GetMapping("api/find-by-id/{id}")
    public Campaign findCampaignById(@PathVariable Long id) {
        return campaignService.findById(id);
    }

    @GetMapping("api/get-cities")
    public List<String> getCities() {
        return List.of("Cracow", "Warsaw", "Wroclaw", "Gdansk", "Rzeszow", "Szczecin", "Poznan", "Katowice", "Lublin");
    }

    @GetMapping("api/keywords")
    public List<String> getKeywords(@RequestParam String query) {
        List<String> allKeywords = List.of("Marketing", "SEO", "Ads", "Google", "Facebook", "Campaign");
        return allKeywords.stream()
                .filter(keyword -> keyword.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }


}
