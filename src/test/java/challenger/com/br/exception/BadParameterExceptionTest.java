package challenger.com.br.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BadParameterExceptionTest {
    private final String exampleMessage = "default_message";

    @Test
    void mustBeInitialize(){
        var badParameterException = new BadParameterException(exampleMessage);
        Assertions.assertNotNull(badParameterException);
    }

    @Test
    void mustContainMessageCorrectly(){
        var badParameterException = new BadParameterException(exampleMessage);
        Assertions.assertEquals(badParameterException.getMessage(), exampleMessage);
    }

}