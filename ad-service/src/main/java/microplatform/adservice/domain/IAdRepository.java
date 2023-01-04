package microplatform.adservice.domain;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface IAdRepository extends PagingAndSortingRepository<Ad, Long> {

    Iterable<Ad> findAllBySellerId(String sellerId);
}
