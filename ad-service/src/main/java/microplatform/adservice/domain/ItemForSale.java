package microplatform.adservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Table(name = "item_for_sale")
public class ItemForSale {

    @EmbeddedId
    @NonNull
    @Type(type = "org.hibernate.type.UUIDCharType")
    private ItemForSaleId id;

    @Version
    private Long version;

    @NotNull(message = "Ad is mandatory")
    @OneToOne(mappedBy = "itemForSale")
    @ToString.Exclude
    @Setter
    private Ad ad;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Setter
    private String description;

    //    @OneToMany
//    @JoinTable(name = "images")
    @ElementCollection
    @ToString.Exclude
    private List<Image> images;

}
