package challenger.com.br.service;

import challenger.com.br.dto.ConvertQueryDTO;
import challenger.com.br.dto.ConvertResponseDTO;
import challenger.com.br.dto.ExchangeRatesResponseDTO;
import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConverterService {

    @Autowired
    private ExchangeRatesService exchangeRatesService;

    public ResponseEntity<Object> converterAmount(Integer userId, String currencyFrom, String currencyTo, BigDecimal amount) {

        List<MonetaryAmount> monetaryAmounts = new ArrayList<>();
        ExchangeRatesResponseDTO exchangeRates = exchangeRatesService.getExchangeRates();

        exchangeRates.getRates().forEach((key,value) -> {
            if(Monetary.isCurrencyAvailable(key)){
                monetaryAmounts.add(Money.of(value, key )
                );
            }
        });

        var monetaryAmountFrom = monetaryAmounts.stream().filter(f -> f.getCurrency().getCurrencyCode().equalsIgnoreCase(currencyFrom)).findAny().get();
        var monetaryAmountTo = monetaryAmounts.stream().filter(f -> f.getCurrency().getCurrencyCode().equalsIgnoreCase(currencyTo)).findAny().get();
        var amount1 = monetaryAmountTo.divide(monetaryAmountFrom.getNumber()).multiply(amount);
        var bigDecimal = BigDecimal.valueOf(amount1.getNumber().doubleValueExact());

        ConvertResponseDTO response =
            ConvertResponseDTO
                .builder()
                    .success(true)
                    .date(exchangeRates.getDate())
                    .result(bigDecimal.setScale(2, RoundingMode.HALF_DOWN))
                    .query(
                        ConvertQueryDTO
                            .builder()
                            .amount(amount)
                            .from(currencyFrom)
                            .to(currencyTo)
                            .build()
                    )
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
