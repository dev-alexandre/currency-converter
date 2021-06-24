package challenger.com.br.controller;

import challenger.com.br.model.Operation;
import challenger.com.br.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;

    @GetMapping(
        value = "/user/historical/{userId}",
        produces = {"application/json"})
    public Flux<Operation> listOperationByUser(@PathVariable Integer userId){
        logger.info("Caller User Controller");
        return service.listOperationByUser(userId);
    }

}
