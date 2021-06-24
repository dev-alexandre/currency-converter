package challenger.com.br.exception;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

class ThirdPartyExceptionTest {
    private final String exampleMessage = "default_message";

    @Test
    void mustBeInitialize(){
        var thirdPartyException = new ThirdPartyException(exampleMessage);
        assertNotNull(thirdPartyException);
    }

    @Test
    void mustContainMessageCorrectly(){
        var thirdPartyException = new ThirdPartyException(exampleMessage);
        assertEquals(thirdPartyException.getMessage(), exampleMessage);
    }
}