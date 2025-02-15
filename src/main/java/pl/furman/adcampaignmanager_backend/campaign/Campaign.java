package pl.furman.adcampaignmanager_backend.campaign;

import lombok.Data;

import java.util.List;

@Data
public class Campaign {
    private Long id;
    private String name;
    private List<String> keywords;
    public double bidAmount;
    public double campaignFund;
    public boolean isCampaignActive;
    public String town;
    public int radius;
}
