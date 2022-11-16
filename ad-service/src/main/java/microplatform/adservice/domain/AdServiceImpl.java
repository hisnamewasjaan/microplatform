package microplatform.adservice.domain;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdServiceImpl implements IAdService {

    IAdRepository adRepository;

    public AdServiceImpl(IAdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @Override
    public Optional<Ad> findById(Long id) {
        return adRepository
                .findById(id)
                .or(() -> Optional.of(save(new Ad("Hotdog"))));
    }

    @Override
    public Ad save(Ad ad) {
        return adRepository.save(ad);
    }

    @Override
    public Iterable<Ad> findAll() {
        return adRepository.findAll();
    }

}
