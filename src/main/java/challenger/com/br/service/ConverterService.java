package challenger.com.br.service;

import challenger.com.br.dto.ConvertQueryDTO;
import challenger.com.br.dto.ConvertResponseDTO;
import challenger.com.br.dto.ExchangeRatesResponseDTO;
import challenger.com.br.exception.BadParameterException;
import org.javamoney.moneta.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConverterService {
    Logger logger = LoggerFactory.getLogger(ConverterService.class);

    @Autowired
    private ExchangeRatesService exchangeRatesService;

    public Mono<Object> converterAmountMono(Integer userId, String currencyFrom, String currencyTo, BigDecimal amount) {

        ExchangeRatesResponseDTO exchangeRates = exchangeRatesService.getExchangeRates();
        validParameters(userId, currencyFrom, currencyTo, amount, exchangeRates);

        List<MonetaryAmount> monetaryAmounts = new ArrayList<>();

        exchangeRates.getRates().forEach((key,value) -> {

            /**
             * @TODO do this better
             * */
            if(Monetary.isCurrencyAvailable(key)){
                monetaryAmounts.add(Money.of(value, key )
                );
            }

        });

        var monetaryAmountFrom = monetaryAmounts
            .stream()
                .filter(f -> f.getCurrency().getCurrencyCode().equalsIgnoreCase(currencyFrom))
                .findFirst()
                .orElseThrow(() -> new BadParameterException(""));

        var monetaryAmountTo = monetaryAmounts
            .stream()
                .filter(f -> f.getCurrency().getCurrencyCode().equalsIgnoreCase(currencyTo))
                .findFirst()
                .orElseThrow(() -> new BadParameterException(""));

        var result = calculate( monetaryAmountFrom ,  monetaryAmountTo,  amount);

        return Mono.just(createResponseObject(currencyFrom, currencyTo, amount, exchangeRates, result));
    }

    private ConvertResponseDTO createResponseObject(String currencyFrom, String currencyTo, BigDecimal amount, ExchangeRatesResponseDTO exchangeRates, BigDecimal result) {
       return ConvertResponseDTO
        .builder()
        .success(true)
        .date(exchangeRates.getDate())
        .result(result)
        .query(
            ConvertQueryDTO
                .builder()
                .amount(amount)
                .from(currencyFrom)
                .to(currencyTo)
            .build()
        )
        .build();
    }

    public BigDecimal calculate(MonetaryAmount monetaryAmountFrom , MonetaryAmount monetaryAmountTo, BigDecimal amount){
        return BigDecimal.valueOf(monetaryAmountTo.divide(monetaryAmountFrom.getNumber()).multiply(amount).getNumber().doubleValueExact()).setScale(2, RoundingMode.HALF_DOWN);
    }


    public void validParameters(Integer userId, String currencyFrom, String currencyTo, BigDecimal amount, ExchangeRatesResponseDTO exchangeRates){
        validCurrency(currencyFrom, exchangeRates);
        validCurrency(currencyTo, exchangeRates);
        validAmount(amount);
    }


    public void validCurrency(String currency, ExchangeRatesResponseDTO exchangeRates){

        if(!exchangeRates.getRates().containsKey(currency)){
            logger.info("This currency { } is not supported yet ", currency);
            throw new BadParameterException("This currency {"+ currency + "} is not supported yet: see values accepted in /currency/accepted");
        }
    }

    public void validAmount(BigDecimal amount){

        if(amount.compareTo(BigDecimal.ZERO) == -1){
            logger.info("It is not possible to calculate a amount less than zero {}", amount.intValue() );
            throw new BadParameterException("It is not possible to calculate a amount less than zero {"+ amount.intValue() +"}");
        }

    }



    public ResponseEntity<Object> converterAmount(Integer userId, String currencyFrom, String currencyTo, BigDecimal amount) {
        ExchangeRatesResponseDTO exchangeRates = exchangeRatesService.getExchangeRates();


        List<MonetaryAmount> monetaryAmounts = new ArrayList<>();
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
