package microplatform.adservice.domain;

import microplatform.adservice.domain.events.ItemForSaleId;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;

@DataJpaTest
class AdRepositoryTest implements WithAssertions {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IAdRepository adRepository;


    @Test
    public void findBy() {
        String sellerId = "seller-id-adssdfds";
        ItemForSale itemForSale = ItemForSale.builder()
                .id(ItemForSaleId.create())
                .name("just a test")
                .description("just a desc")
                .build();
        entityManager.persist(itemForSale);
        Ad ad = Ad.newAd(
                itemForSale,
                BigDecimal.valueOf(1000.00),
                sellerId);
        entityManager.persist(ad);
        entityManager.flush();

        Iterable<Ad> allBySellerId = adRepository.findAllBySellerId(sellerId);

        assertThat(allBySellerId).hasSize(1);
        assertThat(allBySellerId).anyMatch(
                it -> it.getItemForSale()
                        .getName()
                        .equals("just a test"));
    }

}
