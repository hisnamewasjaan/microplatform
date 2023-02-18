package microplatform.adservice.domain.events;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Data
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@Immutable
public class ItemForSaleId implements Serializable {

    @Column(updatable = false)
    @NotNull
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    public static ItemForSaleId create() {
        return new ItemForSaleId(UUID.randomUUID());
    }

    public static ItemForSaleId of(String id) {
        return new ItemForSaleId(UUID.fromString(id));
    }

}
