package microplatform.adservice.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AdIdTest {

    private static final String uuidString = "3e241dfd-0e6c-422c-995a-1ef534d44d33";

    @Test
    public void AdIdOfUuid() {
        UUID uuid = UUID.randomUUID();
        AdId adId = AdId.of(uuid);

        assertEquals(adId.getId(), uuid);
        assertEquals(adId.getId().toString(), uuid.toString());
    }

    @Test
    public void AdIdUuidOfString() {
        UUID uuid = UUID.fromString(uuidString);
        AdId adId = AdId.of(uuidString);

        assertEquals(adId.getId(), uuid);
        assertEquals(adId.getId().toString(), uuid.toString());
    }

    @Test
    public void AdIdEquals() {
        AdId adId = AdId.of(uuidString);
        AdId otherAdId = AdId.of(uuidString);
        AdId diffAdId = AdId.of(UUID.randomUUID());

        assertEquals(otherAdId, adId);
        assertEquals(adId, otherAdId);
        assertNotEquals(adId, diffAdId);
    }

    @Test
    public void AdIdHashcode() {
        AdId adId = AdId.of(uuidString);
        AdId otherAdId = AdId.of(uuidString);
        AdId diffAdId = AdId.of(UUID.randomUUID());

        assertEquals(adId.hashCode(), otherAdId.hashCode());
        assertNotEquals(adId.hashCode(), diffAdId.hashCode());
    }

    @Test
    public void AdIdToString() {
        AdId adId = AdId.of(uuidString);
        AdId otherAdId = AdId.of(uuidString);

        assertEquals(adId.toString(), otherAdId.toString());
        assertEquals(String.format("AdId(id=%s)", uuidString), adId.toString());
    }

    @Test
    public void AdIUuidOfInvalidString() {
        assertThrows(
                IllegalArgumentException.class,
                () -> AdId.of("-0e6c-"));
    }

    @Test
    public void AdIdConstr() {
        assertAll(
                AdId::new,
                () -> new AdId(UUID.randomUUID())
        );
    }

    @Test
    public void AdIdImmutable() {
        assertAll(
                AdId::new,
                () -> new AdId(UUID.randomUUID())
        );
    }

}
