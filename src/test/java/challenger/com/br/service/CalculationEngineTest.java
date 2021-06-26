package challenger.com.br.service;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculationEngineTest {

    @Test
    void calculateEqualsValues() {
        var originalValue = BigDecimal.ONE;
        var finalValue = originalValue.setScale(2, RoundingMode.HALF_DOWN);

        MonetaryAmount brl = Money.of(2, "BRL");
        MonetaryAmount usd = Money.of(2, "BRL");

        BigDecimal calculate = new CalculationEngine().calculate(brl, usd, originalValue);

        assertEquals(calculate, finalValue);
    }

    @Test
    void calculateWithZeroOnAmount(){
        var originalValue = BigDecimal.ZERO;
        var finalValue = originalValue.setScale(2, RoundingMode.HALF_DOWN);

        MonetaryAmount brl = Money.of(2, "BRL");
        MonetaryAmount usd = Money.of(2, "BRL");

        BigDecimal calculate = new CalculationEngine().calculate(brl, usd, originalValue);

        assertEquals(calculate, finalValue);
    }

    @Test
    void calculateWithZeroOnTo(){
        var originalValue = BigDecimal.ONE;
        var finalValue = originalValue.setScale(2, RoundingMode.HALF_DOWN);

        MonetaryAmount brl = Money.of(0, "BRL");
        MonetaryAmount usd = Money.of(2, "BRL");

        Exception exception = assertThrows(Exception.class, () ->
            new CalculationEngine().calculate(brl, usd, originalValue)
        );

        Assertions.assertNotNull(exception);
    }

    @Test
    void calculateWithZeroOnFrom(){
        var originalValue = BigDecimal.ZERO;
        var finalValue = originalValue.setScale(2, RoundingMode.HALF_DOWN);

        MonetaryAmount brl = Money.of(2, "BRL");
        MonetaryAmount usd = Money.of(0, "BRL");

        BigDecimal calculate = new CalculationEngine().calculate(brl, usd, originalValue);

        assertEquals(calculate, finalValue);
    }

    @Test
    void calculateRates(){
        MonetaryAmount brl = Money.of(2, "BRL");
        MonetaryAmount usd = Money.of(2, "BRL");

        BigDecimal rate = new CalculationEngine().getRate(brl, usd);

        Assertions.assertEquals(rate.setScale(1), BigDecimal.ONE.setScale(1));
    }

}