package microplatform.adservice.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController

public class AdController {

    Logger logger = LoggerFactory.getLogger(AdController.class);

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
