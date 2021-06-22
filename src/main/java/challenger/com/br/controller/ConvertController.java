package challenger.com.br.controller;

import challenger.com.br.api.ConverterApi;
import challenger.com.br.service.ConverterService;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController
public class ConvertController implements ConverterApi {
    Logger logger = LoggerFactory.getLogger(ConvertController.class);

    @Autowired
    private ConverterService service;

    @Override
    public ResponseEntity<Object> converterAmount(Integer userId, String currencyFrom, String currencyTo, BigDecimal amount) {
        return service.converterAmount(userId, currencyFrom, currencyTo, amount);
    }

    @RequestMapping(
            value = "/converter/mono/user/{userId}/from/{currencyFrom}/to/{currencyTo}/amount/{amount}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    public Mono<Object> converterAmountMono(
            @ApiParam(value = "Numeric ID of the user to get",required=true) @PathVariable Integer userId,
            @ApiParam(value = "Currency identity code ex USD or BRL",required=true) @PathVariable String currencyFrom,
            @ApiParam(value = "Currency identity code ex USD or BRL",required=true) @PathVariable String currencyTo,
            @ApiParam(value = "Amount",required=true) @PathVariable BigDecimal amount) {
        return service.converterAmountMono(userId, currencyFrom, currencyTo, amount);
    }

}
