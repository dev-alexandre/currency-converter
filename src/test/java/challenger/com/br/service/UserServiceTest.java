package challenger.com.br.service;

import challenger.com.br.model.Operation;
import challenger.com.br.repository.OperationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;

import static org.mockito.Mockito.when;

@WebFluxTest(UserService.class)
public class UserServiceTest {

    @MockBean
    private OperationRepository repository;

    @Autowired
    private UserService service;

    public UserServiceTest(){}

    @Test
    public void findUserByID(){

        Flux<Operation> findAllByUserId = Flux.just(Operation.builder().userId(1).build());

        when(repository.findAllByUserId(1)).thenReturn(findAllByUserId);

        Assertions.assertNotNull(service.findAllByUserId(1));
    }
}