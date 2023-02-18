package microplatform.adservice.web;

import microplatform.adservice.domain.Ad;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class AdDto {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private String sellerId;
    private String adStatus;
    private LocalDateTime expires;

    public static AdDto of(Ad ad) {
        AdDto adDto = new AdDto(ad.getId().getId());
        adDto.setName(ad.getItemForSale().getName());
        adDto.setDescription(ad.getItemForSale().getDescription());
        adDto.setPrice(ad.getPrice().getAmount());
        adDto.setSellerId(ad.getSellerId());
        adDto.setAdStatus(ad.getAdStatus().name());
        return adDto;

    }
    public AdDto() {
        super();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getAdStatus() {
        return adStatus;
    }

    public void setAdStatus(String adStatus) {
        this.adStatus = adStatus;
    }

    public LocalDateTime getExpires() {
        return expires;
    }

    public void setExpires(LocalDateTime expires) {
        this.expires = expires;
    }

    public AdDto(final UUID id) {
        super();
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("description", description)
                .append("price", price)
                .append("sellerId", sellerId)
                .append("adStatus", adStatus)
                .append("expires", expires)
                .toString();
    }

}
