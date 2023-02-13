package microplatform.adservice.domain;

import lombok.ToString;
import microplatform.adservice.domain.events.ItemForSaleId;
import org.hibernate.annotations.Type;

import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@ToString
@Table(name = "item_for_sale")
public class ItemForSale {

    @EmbeddedId
    @Type(type = "org.hibernate.type.UUIDCharType")
    private ItemForSaleId id;

    @Version
    private Long version;

    @NotNull(message = "Ad is mandatory")
    @OneToOne(mappedBy = "itemForSale")
    @ToString.Exclude
    private Ad ad;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String description;

    //    @OneToMany
    @ElementCollection
//    @JoinTable(name = "images")
    private List<Image> images;

    public ItemForSale() {
    }

    public ItemForSale(String name, String description) {
        id = ItemForSaleId.create();
//        id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public ItemForSaleId getId() {
        return id;
    }

    public void setId(ItemForSaleId id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

}
