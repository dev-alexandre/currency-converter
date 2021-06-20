package challenger.com.br.controller;

import challenger.com.br.api.CurrencyApi;
import challenger.com.br.model.Currency;
import challenger.com.br.service.CurrencyService;
import challenger.com.br.service.ExchangeRatesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CurrencyController implements CurrencyApi {

    Logger logger = LoggerFactory.getLogger(CurrencyController.class);

    @Autowired
    private CurrencyService service;

    @Autowired
    private ExchangeRatesService exchangeRatesService;

    @Override
    public ResponseEntity<List<Currency>> getSuportedCurrencies() {
        logger.info("List all suported currencies");

        exchangeRatesService.getTeste();
        return service.getSuportedCurrencies();
    }

}
