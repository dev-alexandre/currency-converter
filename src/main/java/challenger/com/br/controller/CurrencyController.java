package challenger.com.br.controller;

import challenger.com.br.service.CurrencyService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController  {
    Logger logger = LoggerFactory.getLogger(CurrencyController.class);

    @Autowired
    private CurrencyService service;

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "", response = Object.class) }
    )
    @GetMapping(value = "/accepted", produces = "application/json")
    public Mono<List<String>> acceptedCurrencies() {
        return service.getAcceptedCurrencies();
    }



}
