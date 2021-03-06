package challenger.com.br.service;

import org.springframework.stereotype.Service;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CalculationEngine {

    public BigDecimal getRate(MonetaryAmount monetaryAmountFrom , MonetaryAmount monetaryAmountTo){
        return BigDecimal.valueOf(monetaryAmountTo.divide(monetaryAmountFrom.getNumber()).getNumber().doubleValueExact());
    }

    public BigDecimal calculate(MonetaryAmount monetaryAmountFrom , MonetaryAmount monetaryAmountTo, BigDecimal amount){
        return getRate(monetaryAmountFrom, monetaryAmountTo).multiply(amount).setScale(2, RoundingMode.HALF_DOWN);
    }
}
