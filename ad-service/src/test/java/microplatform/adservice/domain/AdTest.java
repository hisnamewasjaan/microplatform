package microplatform.adservice.domain;

import microplatform.adservice.domain.events.AdCreatedEvent;
import microplatform.adservice.domain.events.AdListedEvent;
import microplatform.adservice.domain.events.ItemForSaleId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringJUnitConfig
@SpringBootTest
class AdTest {

    private static final String UUID_REGEX = "[0-9a-fA-F]{8}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{12}";

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

    //    @DisplayName("price is mandatory")
//    @Test
//    void money() {
//        Ad ad1 = makeAd();
//        ad1.setPrice(null);
//
//        Assertions.assertThrows(
//                TransactionSystemException.class,
//                () -> repository.save(ad1));
//    }
//
    @DisplayName("version")
    @Test
    void version() {
        Ad ad1 = makeAd();
        assertThat(ad1.getVersion()).isNull();

        repository.save(ad1);
        assertThat(ad1.getVersion()).isNotNull();
        assertThat(ad1.getItemForSale()).isNotNull();
    }

    @DisplayName("equals")
    @Test
    void equals() {

        Ad ad1 = makeAd();
        Ad ad2 = makeAd();

        assertThat(ad1).isNotEqualTo(ad2);
        assertThat(ad1).isEqualTo(ad1);
    }

    @DisplayName("toString")
    @Test
    void verifyToString() {
        Ad ad = makeAd();

        String pattern = Pattern.quote("Ad(id=AdId(id=") +
                UUID_REGEX +
                Pattern.quote("), version=null, itemForSale=ItemForSale(id=ItemForSaleId(id=") +
                UUID_REGEX +
                Pattern.quote("), version=null, name=Testing, description=Description), price=Money(amount=1000, currency=DKK), sellerId=id10, adStatus=ACTIVE, expires=null)");

        assertThat(ad.toString()).matches(pattern);
    }

    private static Ad makeAd() {
        return makeAd(UUID.randomUUID()
                .toString());
    }

    private static Ad makeAd(String itemForSaleId) {
        ItemForSale itemForSale = ItemForSale.builder()
                .id(ItemForSaleId.of(itemForSaleId))
                .name("Testing")
                .description("Description")
                .build();

        Ad ad = Ad.newAd(
                itemForSale,
                new BigDecimal(1000),
                "id10");
        ad.activate();
        return ad;
    }

}
