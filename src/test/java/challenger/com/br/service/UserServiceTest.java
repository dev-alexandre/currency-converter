package challenger.com.br.service;

import challenger.com.br.model.Operation;
import challenger.com.br.repository.OperationRepository;
import org.junit.Test;

import static reactor.core.publisher.Mono.when;

class UserServiceTest {

    private OperationRepository repository;

    @Test
    void mustReturnOperation(){
        Operation operation = Operation.builder().build();

        when(repository.findAllByUserId(10)).thenReturn(null);
    }

}