package microplatform.adservice.domain;

import microplatform.adservice.domain.events.AdCreatedEvent;
import microplatform.adservice.domain.events.AdListedEvent;
import microplatform.adservice.domain.events.ItemForSaleId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.TransactionSystemException;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringJUnitConfig
@SpringBootTest
class AdTest {

    @MockBean
    private TestEventHandler eventHandler;
    @Autowired
    private IAdRepository repository;

    @DisplayName("given aggregate extending AbstractAggregateRoot,"
            + " when do domain operation and save,"
            + " then an event is published")
    @Test
    void domainEvents() {

        Ad ad = makeAd();
        repository.save(ad);

        verify(eventHandler, times(1)).handleEvent(any(AdCreatedEvent.class));
        verify(eventHandler, times(1)).handleEvent(any(AdListedEvent.class));
    }

    @DisplayName("price is mandatory")
    @Test
    void money() {
        Ad ad1 = makeAd();
        ad1.setPrice(null);

        Assertions.assertThrows(
                TransactionSystemException.class,
                () -> repository.save(ad1));
    }

    @DisplayName("version")
    @Test
    void version() {
        Ad ad1 = makeAd();
        Assertions.assertNull(ad1.getVersion());

        repository.save(ad1);
        Assertions.assertNotNull(ad1.getVersion());
        Assertions.assertNotNull(ad1.getItemForSale().getVersion());
    }

    @DisplayName("equals")
    @Test
    void equals() {

        Ad ad1 = makeAd();
        Ad ad2 = makeAd();

        Assertions.assertNotEquals(ad1, ad2);
        Assertions.assertEquals(ad1, ad1);
    }

    @DisplayName("toString")
    @Test
    void verifyToString() {
        String adId = "1783739c-d01d-47db-8b9b-198bb5ef1ab9";
        String itemForSaleId = "53f0c145-f982-408d-ac84-2ae4c12f0af7";
        Ad ad1 = makeAd(adId, itemForSaleId);

        Assertions.assertEquals(
                String.format(
                        "Ad(id=AdId(id=%s), version=null, itemForSale=ItemForSale(id=ItemForSaleId(id=%s), version=null, name=Testing, description=Description, images=null), price=Money(amount=1000, currency=DKK), sellerId=id10, adStatus=ACTIVE, expires=null)",
                        adId,
                        itemForSaleId),
                ad1.toString());
    }

    private static Ad makeAd() {
        return makeAd(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString());
    }

    private static Ad makeAd(String adId, String itemForSaleId) {
        ItemForSale itemForSale = new ItemForSale("Testing", "Description");

        Ad ad = Ad.newAd(
                itemForSale,
                new BigDecimal(1000), "id10");
        ad.setId(AdId.of(adId));
        ad.getItemForSale().setId(ItemForSaleId.of(itemForSaleId));
        ad.activate();
        return ad;
    }

}
