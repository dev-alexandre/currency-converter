package challenger.com.br.service;

import challenger.com.br.model.Currency;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyService {

    public ResponseEntity<List<Currency>> getSuportedCurrencies() {

        List<Currency> currencies = new ArrayList<>();
        Currency usd = new Currency();
        usd.setId(1);
        usd.setName("USD");
        currencies.add(usd);

        return new ResponseEntity<>(currencies, HttpStatus.OK);
    }
}
