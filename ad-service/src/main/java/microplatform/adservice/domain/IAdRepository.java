package microplatform.adservice.domain;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface IAdRepository extends PagingAndSortingRepository<Ad, AdId> {

    Iterable<Ad> findAllBySellerId(String sellerId);
}
