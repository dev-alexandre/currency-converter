package challenger.com.br.controller;

import challenger.com.br.model.Operation;
import challenger.com.br.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest(UserController.class)
class UserControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private UserService repository;

    private Flux<Operation> operationFlux;
    private final String route = "/user/history/10";

    private final Integer id = 1;
    private final Integer userId = 10;

    @PostConstruct
    void Setup(){
        operationFlux = Flux.just(
            Operation.builder()
                .id(id)
                .build()
        );

        when(repository.findAllByUserId(userId)).thenReturn(operationFlux);
    }

    @Test
    void convertControllerMustResponseCorrectlyObject(){
        webTestClient.get()
                .uri(route)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }
}