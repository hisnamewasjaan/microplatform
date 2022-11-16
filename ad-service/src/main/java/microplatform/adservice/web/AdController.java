package microplatform.adservice.web;

import microplatform.adservice.domain.Ad;
import microplatform.adservice.domain.IAdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/ads")
public class AdController {
    Logger logger = LoggerFactory.getLogger(AdController.class);

    IAdService adService;

    public AdController(IAdService adService){
        this.adService = adService;
    }

    @GetMapping(value = "/{id}")
    public AdDto findOne(@PathVariable Long id) {
        Ad entity = adService
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity);
    }

    @PostMapping
    public AdDto addOne(@Valid @RequestBody AdDto toAdd) {
        logger.info("addOne <{}>", toAdd);
        Ad entity = adService
                .save(convertToAd(toAdd));
        return convertToDto(entity);
    }

    @GetMapping
    public Collection<AdDto> findAll() {
        Iterable<Ad> all = adService.findAll();
        List<AdDto> dtos = new ArrayList<>();
        all.forEach(p -> dtos.add(convertToDto(p)));
        return dtos;
    }

    private AdDto convertToDto(Ad entity) {
        logger.info("convertToDto <{}>", entity);
        return new AdDto(entity.getId(), entity.getName());
    }

    private Ad convertToAd(AdDto dto) {
        logger.info("convertToAd <{}>", dto);
        return new Ad(dto.getName());
    }


    @RequestMapping("/create")
    public String create() {
        logger.trace("TRACE create");
        logger.debug("DEBUG create");
        logger.info("INFO create");
        logger.warn("WARN create");
        logger.error("ERROR create");

        String id = UUID.randomUUID().toString();
        logger.info("ad created <{}>", id);
        return id;
    }

    @GetMapping("/ads")
    public String[] getArticles() {
        return new String[] { "Article 1", "Article 2", "Article 3" };
    }

    @RequestMapping("/list")
    public String list() {
        return "listen";
    }

    @RequestMapping("/resource")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public Map<String, Object> home() {
        String id = UUID.randomUUID().toString();
        logger.info("requesting resource <{}>", id);
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("id", id);
        model.put("content", "Hello World");
        return model;
    }

//    @RequestMapping("/list")
//    public List<String> list() {
//        return List.of(
//                UUID.randomUUID().toString(),
//                UUID.randomUUID().toString()
//        );
//    }

}
