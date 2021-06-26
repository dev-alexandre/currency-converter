package challenger.com.br.service;

import challenger.com.br.model.Operation;
import challenger.com.br.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationService {

    @Autowired
    private OperationRepository operationRepository;

    public void save(Operation operation){
       operationRepository.save(operation).subscribe();
    }
}
