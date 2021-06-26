package challenger.com.br.service;

import challenger.com.br.dto.ExchangeRatesResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyService {

    @Autowired
    private ExchangeRatesService service;

    public Mono<List<String>> getAcceptedCurrencies() {
        ExchangeRatesResponseDTO exchangeRates = service.getExchangeRates();
        return Mono.just(new ArrayList<>(exchangeRates.getRates().keySet()));
    }
}
