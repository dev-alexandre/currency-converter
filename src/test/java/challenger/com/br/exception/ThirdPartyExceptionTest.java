package challenger.com.br.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThirdPartyExceptionTest {
    private final String exampleMessage = "default_message";

    @Test
    void mustBeInitialize(){
        var thirdPartyException = new ThirdPartyException(exampleMessage);
        Assertions.assertNotNull(thirdPartyException);
    }

    @Test
    void mustContainMessageCorrectly(){
        var thirdPartyException = new ThirdPartyException(exampleMessage);
        Assertions.assertEquals(thirdPartyException.getMessage(), exampleMessage);
    }

}