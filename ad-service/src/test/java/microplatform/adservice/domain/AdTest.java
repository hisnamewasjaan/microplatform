package microplatform.adservice.domain;

import microplatform.adservice.domain.events.AdCreatedEvent;
import microplatform.adservice.domain.events.AdListedEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@SpringJUnitConfig
@SpringBootTest
class AdTest {

    @MockBean
    private TestEventHandler eventHandler;
    @Autowired
    private IAdRepository repository;

    @DisplayName("given aggregate extending AbstractAggregateRoot,"
            + " when do domain operation and save,"
            + " then an event is published")
    @Test
    void domainEvents() {
        Ad ad = Ad.newAd("Testing", "Description", new BigDecimal(1000), "id10");
        ad.activate();
        repository.save(ad);

        verify(eventHandler, times(1)).handleEvent(any(AdCreatedEvent.class));
        verify(eventHandler, times(1)).handleEvent(any(AdListedEvent.class));
    }

}
