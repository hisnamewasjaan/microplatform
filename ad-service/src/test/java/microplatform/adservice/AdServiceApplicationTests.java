package microplatform.adservice;

import microplatform.adservice.web.AdController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class AdServiceApplicationTests {

    @Autowired
    private AdController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

}
