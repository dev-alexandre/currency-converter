package challenger.com.br.controller;

import challenger.com.br.exception.BadParameterException;
import challenger.com.br.exception.ThirdPartyException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ContextConfiguration
class AdviceControllerTest {
    private final String exampleMessage = "default_message";

    @Autowired
    AdviceController adviceController;

    @Test
    void badParameterException() {
        ResponseEntity<Map<String, Object>> responseEntity = adviceController.badParameterException(new BadParameterException(exampleMessage));
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().containsKey("timestamp"));
        assertTrue(responseEntity.getBody().containsKey("message"));
        assertEquals(responseEntity.getBody().get("message"),exampleMessage);
    }

    @Test
    void thirdPartyException() {
        ResponseEntity<Map<String, Object>> responseEntity = adviceController.thirdPartyException(new ThirdPartyException(exampleMessage));
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, responseEntity.getStatusCode() );
        assertTrue(responseEntity.getBody().containsKey("timestamp"));
        assertTrue(responseEntity.getBody().containsKey("message"));
        assertEquals(responseEntity.getBody().get("message"),exampleMessage);
    }

}