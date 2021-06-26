package challenger.com.br.controller;

import challenger.com.br.model.Operation;
import challenger.com.br.service.ConverterService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest(ConvertController.class)
class ConvertControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ConverterService service;

    private Mono<Operation> operationMono;
    private final String route = "/converter/user/10/from/BRL/to/USD/amount/10";

    private final Integer id = 1;
    private final Integer userId = 10;
    private final String currencyFrom  = "BRL";
    private final String currencyTo = "USD";
    private final BigDecimal amount = BigDecimal.TEN;

    @PostConstruct
    void Setup(){
        operationMono = Mono.just(
            Operation.builder()
                .id(id)
                .userId(userId)
                .currencyFrom(currencyFrom)
                .currencyTo(currencyTo)
                .amountTo(amount)
            .build()
        );
        when(service.converterAmount(userId, currencyFrom, currencyTo, amount)).thenReturn(operationMono);
    }

    @Test
    void convertControllerMustResponseCorrectlyObject(){
        webTestClient.get()
                .uri(route)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Operation.class);
    }

    @Test
    void convertControllerMustResponseCorrectlyProperties(){
        webTestClient.get()
                .uri(route)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Operation.class)
                .value(op1 -> op1.getId() , equalTo(id))
                .value(op1 -> op1.getUserId() , equalTo(userId))
                .value(op1 -> op1.getCurrencyFrom() , equalTo(currencyFrom))
                .value(op1 -> op1.getCurrencyTo() , equalTo(currencyTo))
                ;
    }
}