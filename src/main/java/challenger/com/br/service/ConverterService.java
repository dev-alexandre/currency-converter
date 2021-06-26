package challenger.com.br.service;

import challenger.com.br.config.AppEnvironment;
import challenger.com.br.dto.ExchangeRatesResponseDTO;
import challenger.com.br.exception.BadParameterException;
import challenger.com.br.model.Operation;
import org.javamoney.moneta.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConverterService {
    final Logger logger = LoggerFactory.getLogger(ConverterService.class);

    @Autowired
    private ExchangeRatesService exchangeRatesService;

    @Autowired
    private OperationService operationService;

    @Autowired
    private CalculationEngine calculationEngine;

    @Autowired
    private AppEnvironment appEnvironment;

    public Mono<Operation> converterAmount(Integer userId, String currencyFrom, String currencyTo, BigDecimal amount) {

        ExchangeRatesResponseDTO exchangeRates = exchangeRatesService.getExchangeRates();
        validParameters(currencyFrom, currencyTo, amount, exchangeRates);

        List<MonetaryAmount> monetaryAmounts = new ArrayList<>();

        exchangeRates.getRates().forEach((key,value) -> {
            if(Monetary.isCurrencyAvailable(key)){
                monetaryAmounts.add(Money.of(value, key ));
            }
        });

        var monetaryAmountFrom = monetaryAmounts
            .stream()
                .filter(f -> f.getCurrency().getCurrencyCode().equalsIgnoreCase(currencyFrom))
                .findFirst()
                .orElseThrow(() -> new BadParameterException("A currency is not supported was found"));

        var monetaryAmountTo = monetaryAmounts
            .stream()
                .filter(f -> f.getCurrency().getCurrencyCode().equalsIgnoreCase(currencyTo))
                .findFirst()
                .orElseThrow(() -> new BadParameterException("A currency not supported was found"));


        Operation responseObject =
                createResponseObject(
                        userId,
                        currencyFrom,
                        currencyTo,
                        amount,
                        exchangeRates,
                        calculationEngine.getRate(monetaryAmountFrom ,  monetaryAmountTo),
                        calculationEngine.calculate( monetaryAmountFrom ,  monetaryAmountTo,  amount));

        operationService.save(responseObject);

        return Mono.just(responseObject);
    }

    private Operation createResponseObject(Integer userId, String currencyFrom, String currencyTo, BigDecimal amount, ExchangeRatesResponseDTO exchangeRates, BigDecimal rate, BigDecimal result) {
        return Operation
            .builder()
            .userId(userId)
            .operationDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern(appEnvironment.getApiFormatDateTime())))
            .amountTo(result)
            .amountFrom(amount)
            .currencyFrom(currencyFrom)
            .rate(rate)
            .currencyTo(currencyTo)
        .build();
    }

    public void validParameters(String currencyFrom, String currencyTo, BigDecimal amount, ExchangeRatesResponseDTO exchangeRates){
        validCurrency(currencyFrom, exchangeRates);
        validCurrency(currencyTo, exchangeRates);
        validAmount(amount);
    }

    public void validCurrency(String currency, ExchangeRatesResponseDTO exchangeRates){

        if(!exchangeRates.getRates().containsKey(currency)){
            logger.info("This currency :{} is not supported yet ", currency);
            throw new BadParameterException("This currency {"+ currency + "} is not supported yet: see values accepted in {"+ exchangeRates.getCurrencies().toString() + "}");
        }
    }

    public void validAmount(BigDecimal amount){

        if(amount.compareTo(BigDecimal.ZERO) < 0){
            logger.info("It is not possible to calculate a amount less than zero :{}", amount.intValue() );
            throw new BadParameterException("It is not possible to calculate a amount less than zero {"+ amount.intValue() +"}");
        }
    }

}
