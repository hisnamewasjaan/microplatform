package microplatform.adservice.domain;

import microplatform.adservice.domain.events.AdCreatedEvent;
import microplatform.adservice.domain.events.AdListedEvent;
import microplatform.adservice.domain.events.AdUnlistedEvent;
import microplatform.adservice.web.AdController;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
/**
 *
 * Using JPA, you can designate any POJO class as a JPA entity–a Java object
 * whose nontransient fields should be persisted to a relational database using
 * the services of an entity manager obtained from a JPA persistence provider
 * (either within a Java EE EJB container or outside an EJB container in a
 * Java SE application).
 */
public class Ad extends AbstractAggregateRoot<Ad> {

    private static final Logger logger = LoggerFactory.getLogger(Ad.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String description;
//    @NotNull(message = "Price is mandatory")
    private BigDecimal price;
    private String sellerId;

    private AdStatus adStatus;

    private LocalDateTime expires;

    static Ad newAd(String name, String description, BigDecimal price, String sellerId) {
        Ad ad = new Ad();
        ad.name = name;
        ad.description = description;
        ad.price = price;
        ad.sellerId = sellerId;
        ad.registerEvent(new AdCreatedEvent());
        logger.debug("New ad created <{}>", ad);
        return ad;
    }
    protected Ad() {
    }

    public Ad(String name) {
        this.name = name;
    }

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
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
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

    public BigDecimal getPrice() {
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

    public String getDescription() {
        return description;
    }

}
