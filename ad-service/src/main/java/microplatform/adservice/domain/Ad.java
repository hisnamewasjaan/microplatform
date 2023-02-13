package microplatform.adservice.domain;

import lombok.ToString;
import microplatform.adservice.domain.events.AdCreatedEvent;
import microplatform.adservice.domain.events.AdListedEvent;
import microplatform.adservice.domain.events.AdUnlistedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * Using JPA, you can designate any POJO class as a JPA entity–a Java object
 * whose nontransient fields should be persisted to a relational database using
 * the services of an entity manager obtained from a JPA persistence provider
 * (either within a Java EE EJB container or outside an EJB container in a
 * Java SE application).
 */
@Entity
@Table(name = "ads")
@ToString
public class Ad extends AbstractAggregateRoot<Ad> {

    private static final Logger logger = LoggerFactory.getLogger(Ad.class);

    @EmbeddedId
    private AdId id;

    @Version
    private Long version;

    @NotNull(message = "ItemForSale is mandatory")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_for_sale_id")
    private ItemForSale itemForSale;

    @NotNull(message = "Price is mandatory")
    @Embedded
    private Money price;
    private String sellerId;

    private AdStatus adStatus;

    private LocalDateTime expires;

    public static Ad newAd(ItemForSale itemForSale, BigDecimal price, String sellerId) {
        Ad ad = new Ad();
        ad.id = AdId.create();
        ad.itemForSale = itemForSale;
        ad.price = Money.ofDkk(price);
        ad.sellerId = sellerId;
        ad.adStatus = AdStatus.INACTIVE;
        ad.registerEvent(new AdCreatedEvent());
        logger.debug("New ad created <{}>", ad);
        itemForSale.setAd(ad);
        return ad;
    }
    protected Ad() {
    }

    public Ad(ItemForSale itemForSale) {
        this.itemForSale = itemForSale;
    }

    public AdId getId() {
        return id;
    }

    public void setId(AdId id) {
        this.id = id;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public Ad activate() {
        adStatus = AdStatus.ACTIVE;
        registerEvent(new AdListedEvent());
        return this;
    }

    public Ad unlist() {
        adStatus = AdStatus.INACTIVE;
        registerEvent(new AdUnlistedEvent());
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ad other = (Ad) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }

    public Money getPrice() {
        return price;
    }

    public String getSellerId() {
        return sellerId;
    }

    public AdStatus getAdStatus() {
        return adStatus;
    }

    public LocalDateTime getExpires() {
        return expires;
    }

    public String getName() {
        return itemForSale.getName();
    }

    public String getDescription() {
        return itemForSale.getDescription();
    }

    public void setItemForSale(ItemForSale itemForSale){
        this.itemForSale = itemForSale;
    }

    public ItemForSale getItemForSale(){
        return itemForSale;
    }

    public Long getVersion() {
        return version;
    }

}
