package challenger.com.br.controller;

import challenger.com.br.model.Operation;
import challenger.com.br.repository.OperationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ConvertController.class)
@Import(ConvertController.class)
class ConvertControllerTest {

    @MockBean
    OperationRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void teste(){
        Operation operation = Operation
                .builder()
                .id(1)
                .userId(10)
                .build();

        Mono<Operation> just = Mono.just(operation);

    }

}