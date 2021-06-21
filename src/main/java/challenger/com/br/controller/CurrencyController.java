package challenger.com.br.controller;

import challenger.com.br.service.CurrencyService;
import challenger.com.br.service.ExchangeRatesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CurrencyController  {

    Logger logger = LoggerFactory.getLogger(CurrencyController.class);

    @Autowired
    private CurrencyService service;

    @Autowired
    private ExchangeRatesService exchangeRatesService;

    @GetMapping(value = "/v1/currency/teste", produces = { "application/json" })
    public ResponseEntity<List<String>> getTesteSupportedCurrencies(){
        return service.getSuportedCurrencies();
    }

}
