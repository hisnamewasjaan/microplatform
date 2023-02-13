package microplatform.adservice.domain;

import microplatform.adservice.web.AdController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AdServiceImpl implements IAdService {

    private Logger logger = LoggerFactory.getLogger(AdController.class);

    IAdRepository adRepository;

    public AdServiceImpl(IAdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @Override
    public Optional<Ad> findById(AdId id) {
        return adRepository.findById(id);
    }

    @Transactional
    @Override
    public Ad newAd(String sellerId, String name, String description, BigDecimal price) {
        ItemForSale itemForSale = new ItemForSale(name, description);
        Ad ad = Ad.newAd(itemForSale, price, sellerId);
        ad.getItemForSale().setDescription("hahahaha");
        return adRepository.save(ad);
    }

    @Override
    public Ad save(Ad ad) {
        return adRepository.save(ad);
    }

    @Override
    public Ad unlist(Ad ad) {
        return ad.unlist();
    }

    @Override
    public Iterable<Ad> findAll() {
        return adRepository.findAll();
    }

    @Override
    public Iterable<Ad> findAllMyAds(String sellerId) {
        logger.debug("findAllMyAds <{}>", sellerId);
        Iterable<Ad> allBySellerId = adRepository.findAllBySellerId(sellerId);
        logger.debug("found <{}>", allBySellerId);
        return allBySellerId;
    }

}
