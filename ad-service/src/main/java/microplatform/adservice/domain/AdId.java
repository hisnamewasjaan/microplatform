package microplatform.adservice.domain;

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
public class AdId implements Serializable {

    @Column(updatable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    @NotNull
    private UUID id;

    public static AdId create() {
        return new AdId(UUID.randomUUID());
    }

    public static AdId of(UUID id){
        return new AdId(id);
    }

    public static AdId of(String id){
        return new AdId(UUID.fromString(id));
    }

}
