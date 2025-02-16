package pl.furman.adcampaignmanager_backend.campaign;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @ElementCollection
    private List<String> keywords;
    private double bidAmount;
    private Double campaignFund;
    @JsonProperty("isCampaignActive")
    private boolean isCampaignActive;
    private String town;
    private int radius;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public boolean isCampaignActive() {
        return isCampaignActive;
    }

    public void setCampaignActive(boolean campaignActive) {
        isCampaignActive = campaignActive;
    }

    public Double getCampaignFund() {
        return campaignFund;
    }

    public void setCampaignFund(Double campaignFund) {
        this.campaignFund = campaignFund;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
