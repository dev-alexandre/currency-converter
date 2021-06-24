package challenger.com.br.repository;

import challenger.com.br.model.Operation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface OperationRepository extends ReactiveCrudRepository<Operation, Integer> {

    Flux<Operation> findAllByUserId(Integer userId);

}
