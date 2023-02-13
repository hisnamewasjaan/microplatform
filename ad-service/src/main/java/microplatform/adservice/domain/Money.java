package microplatform.adservice.domain;

import lombok.ToString;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.Currency;

@Embeddable
@ToString
public class Money {

    private BigDecimal amount;

    /**
     * ISO 4217 currency value
     * Eg. DKK for danish kroner
     */
    private Currency currency;

    public static Money ofDkk(BigDecimal amount) {
        return new Money(amount, Currency.getInstance("DKK"));
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Money() {
    }

    private Money(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

}
