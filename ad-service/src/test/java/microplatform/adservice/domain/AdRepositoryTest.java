package microplatform.adservice.domain;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AdRepositoryTest implements WithAssertions {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IAdRepository adRepository;


    @Test
    public void findBy() {
        String sellerId = "seller-id-adssdfds";
        Ad ad = Ad.newAd("just a test",
                "just a desc",
                BigDecimal.valueOf(1000.00),
                sellerId);
        entityManager.persist(ad);
        entityManager.flush();

        Iterable<Ad> allBySellerId = adRepository.findAllBySellerId(sellerId);

        assertThat(allBySellerId.iterator().hasNext()).isTrue();
        assertThat(allBySellerId.iterator().next().getName()).isEqualTo("just a test");
    }

}
