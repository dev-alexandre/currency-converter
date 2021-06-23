package challenger.com.br.controller;

import challenger.com.br.model.Operation;
import challenger.com.br.service.ConverterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController
@Api(value = "converter")
public class ConvertController {

    @Autowired
    private ConverterService service;

    @GetMapping(
            value = "/converter/user/{userId}/from/{currencyFrom}/to/{currencyTo}/amount/{amount}",
            produces = { "application/json" })
    public Mono<Operation> converterAmountMono(
            @ApiParam(value = "Numeric ID of the user to get",required=true) @PathVariable Integer userId,
            @ApiParam(value = "Currency identity code ex USD or BRL",required=true) @PathVariable String currencyFrom,
            @ApiParam(value = "Currency identity code ex USD or BRL",required=true) @PathVariable String currencyTo,
            @ApiParam(value = "Amount",required=true) @PathVariable BigDecimal amount) {
        return service.converterAmount(userId, currencyFrom, currencyTo, amount);
    }

}
