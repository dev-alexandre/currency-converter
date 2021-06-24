package challenger.com.br.service;

import challenger.com.br.dto.ExchangeRatesResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyService {

    @Autowired
    private ExchangeRatesService exchangeRatesService;

    public Mono<List<String>> getAcceptedCurrencies() {
        ExchangeRatesResponseDTO exchangeRates = exchangeRatesService.getExchangeRates();
        return Mono.just(exchangeRates.getRates().keySet().stream().collect(Collectors.toList()));
    }
}
