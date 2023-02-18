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

/**
 * Typed id for Ad
 *
 * inspired by https://medium.com/javarevisited/spring-boot-power-of-value-objects-a98831cfe4b8
 */
@Data
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
/* cos JPA needs it*/
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
/* no real value*/
@Immutable
/* ids must be serializable */
public class AdId implements Serializable {

    @Column(updatable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    @NotNull
    /* cannot be final cos JPA*/
    private UUID id;

    public static AdId create() {
        return new AdId(UUID.randomUUID());
    }

    public static AdId of(UUID id) {
        return new AdId(id);
    }

    public static AdId of(String id) {
        return new AdId(UUID.fromString(id));
    }

}
