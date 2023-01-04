package microplatform.adservice.domain;

import microplatform.adservice.domain.events.AdEvent;
import org.springframework.transaction.event.TransactionalEventListener;

interface TestEventHandler {

    @TransactionalEventListener
    void handleEvent(AdEvent event);

}
