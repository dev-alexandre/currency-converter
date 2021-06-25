package challenger.com.br.service;

import challenger.com.br.model.Operation;
import challenger.com.br.repository.OperationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static reactor.core.publisher.Mono.when;

@RunWith(MockitoJUnitRunner.class)
class UserServiceTest {

    @Mock
    @Autowired
    private OperationRepository repository;


    @Test
    void mustReturnOperation(){
        Operation operation = Operation.builder().build();

        when(repository.findAllByUserId(10)).thenReturn(null);
    }

}