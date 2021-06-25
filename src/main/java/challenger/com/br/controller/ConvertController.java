package challenger.com.br.controller;

import challenger.com.br.model.Operation;
import challenger.com.br.service.ConverterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController
@Api(value = "converter")
public class ConvertController {
    final Logger logger = LoggerFactory.getLogger(ConvertController.class);

    @Autowired
    private ConverterService service;

    @GetMapping(
            value = "/converter/user/{userId}/from/{currencyFrom}/to/{currencyTo}/amount/{amount}",
            produces = { "application/json" })
    public Mono<Operation> converterAmount(
            @ApiParam(value = "Numeric ID of the user to get",required=true) @PathVariable Integer userId,
            @ApiParam(value = "Currency identity code ex USD or BRL",required=true) @PathVariable String currencyFrom,
            @ApiParam(value = "Currency identity code ex USD or BRL",required=true) @PathVariable String currencyTo,
            @ApiParam(value = "currency to convert ",required=true) @PathVariable(value ="amount" ) BigDecimal amountFrom) {

        logger.info("Caller Converter Controller user {} currencyFrom {} currencyTo {} amountFrom {}", userId, currencyFrom, currencyTo, amountFrom);
        return service.converterAmount(userId, currencyFrom, currencyTo, amountFrom);
    }

}
