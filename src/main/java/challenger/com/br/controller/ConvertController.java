package challenger.com.br.controller;

import challenger.com.br.api.ConverterApi;
import challenger.com.br.service.ConverterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class ConvertController implements ConverterApi {

    Logger logger = LoggerFactory.getLogger(ConvertController.class);

    @Autowired
    private ConverterService service;

    @Override
    public ResponseEntity<Object> converterAmount(Integer userId, String currencyFrom, String currencyTo, BigDecimal amount) {
        logger.info("converterAmount userId {} currencyFrom {} currencyTo {} amount {} ", userId, currencyFrom, currencyTo, amount);
        return service.converterAmount(userId, currencyFrom, currencyTo, amount);
    }
}
