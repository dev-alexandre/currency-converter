package challenger.com.br.service;

import challenger.com.br.dto.ExchangeRatesResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyService {

    @Autowired
    private ExchangeRatesService exchangeRatesService;



    public ResponseEntity<List<String>> getSuportedCurrencies() {

        ExchangeRatesResponseDTO exchangeRates = exchangeRatesService.getExchangeRates();
        List<String> collect = exchangeRates.getRates().keySet().stream().collect(Collectors.toList());

        return new ResponseEntity<>(collect, HttpStatus.OK);
    }
}
