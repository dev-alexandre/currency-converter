package challenger.com.br.api;

import challenger.com.br.model.Currency;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CurrencyController implements CurrencyApi {

    @Override
    public ResponseEntity<List<Currency>> getSuportedCurrencies() {

        List<Currency> currencies = new ArrayList<>();
        Currency usd = new Currency();
        usd.setId(1);
        usd.setName("USD");
        currencies.add(usd);

        return new ResponseEntity<>(currencies, HttpStatus.OK);
    }

}
