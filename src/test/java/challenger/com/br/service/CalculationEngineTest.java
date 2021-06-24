package challenger.com.br.service;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.math.RoundingMode;

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

}