package challenger.com.br.service;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculationEngine {

    public BigDecimal calculate(MonetaryAmount monetaryAmountFrom , MonetaryAmount monetaryAmountTo, BigDecimal amount){
        return BigDecimal.valueOf(monetaryAmountTo.divide(monetaryAmountFrom.getNumber()).multiply(amount).getNumber().doubleValueExact()).setScale(2, RoundingMode.HALF_DOWN);
    }


}
