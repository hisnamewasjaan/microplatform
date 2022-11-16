package microplatform.adservice.domain;

import java.util.Optional;

public interface IAdService {

    Optional<Ad> findById(Long id);

    Ad save(Ad ad);

    Iterable<Ad> findAll();

}
