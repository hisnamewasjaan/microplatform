package microplatform.adservice.domain;

import java.math.BigDecimal;
import java.util.Optional;

public interface IAdService {

    Ad newAd(String sellerId, String name, String description, BigDecimal price);

    Optional<Ad> findById(AdId id);

    Ad save(Ad ad);

    Ad unlist(Ad ad);

    Iterable<Ad> findAll();

    Iterable<Ad> findAllMyAds(String sellerId);

}
