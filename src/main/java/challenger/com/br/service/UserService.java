package challenger.com.br.service;

import challenger.com.br.model.Operation;
import challenger.com.br.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class UserService {

    @Autowired
    private OperationRepository repository;

    public Flux<Operation> findAllByUserId(Integer userId){
        return repository.findAllByUserId(userId);
    }
}
