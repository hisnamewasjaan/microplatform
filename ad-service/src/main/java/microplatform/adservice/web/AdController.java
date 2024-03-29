package microplatform.adservice.web;

import microplatform.adservice.domain.Ad;
import microplatform.adservice.domain.IAdService;
import microplatform.adservice.domain.AdId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/ads")
public class AdController {
    private static final Logger logger =
            LoggerFactory.getLogger(AdController.class);

    IAdService adService;

    public AdController(IAdService adService) {
        this.adService = adService;
    }

    //todo try with UUID type
    @GetMapping(value = "/{id}")
    public AdDto findOne(@PathVariable String id) {
        Ad entity = adService
                .findById(AdId.of(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity);
    }

    @PostMapping
    public AdDto create(@Valid @RequestBody AdDto toAdd) {
        logger.info("create <{}>", toAdd);
        Ad entity = adService.newAd(
                getSubject(),
                toAdd.getName(),
                toAdd.getDescription(),
                toAdd.getPrice());
        logger.info("Ad created <{}>", entity);
        return convertToDto(entity);
    }

    @GetMapping
    public Collection<AdDto> findAll() {
        Iterable<Ad> all = adService.findAll();
        List<AdDto> dtos = new ArrayList<>();
        all.forEach(p -> dtos.add(convertToDto(p)));
        return dtos;
    }

    @GetMapping(value = "myAds")
    public Collection<AdDto> myAds() {
        Iterable<Ad> all = adService.findAllMyAds(getSubject());
        List<AdDto> dtos = new ArrayList<>();
        all.forEach(p -> dtos.add(convertToDto(p)));
        return dtos;
    }

    private AdDto convertToDto(Ad entity) {
        logger.info("convertToDto <{}>", entity);
        AdDto adDto = new AdDto(entity.getId().getId());
        adDto.setName(entity.getItemForSale().getName());
        adDto.setDescription(entity.getItemForSale().getDescription());
        adDto.setPrice(entity.getPrice() != null
                ? entity.getPrice().getAmount()
                : null);
        adDto.setAdStatus(entity.getAdStatus() != null
                ? entity.getAdStatus().toString()
                : null);
        adDto.setExpires(entity.getExpires());
        adDto.setSellerId(entity.getSellerId());
        return adDto;
    }

    private String getSubject() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.info("with authentication <{}>", authentication);
        Jwt jwt = (Jwt) authentication.getPrincipal();
        logger.info("jwt subject <{}>", jwt.getSubject());
        return jwt.getSubject();
    }

}
