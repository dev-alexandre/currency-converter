package challenger.com.br.exception;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
class BadParameterExceptionTest {
    private final String exampleMessage = "default_message";

    @Test
    void mustBeInitialize(){
         var badParameterException = new BadParameterException(exampleMessage);
        assertNotNull(badParameterException);
    }

    @Test
    void mustContainMessageCorrectly(){
         var badParameterException = new BadParameterException(exampleMessage);
         assertEquals(badParameterException.getMessage(), exampleMessage);
    }

}