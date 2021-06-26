package challenger.com.br.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration
class AppCacheTest {

    @Autowired
    AppCache appCache;

    @Test
    void mustBeInitialized() {
        Assertions.assertNotNull(appCache);
    }

    @Test
    void propertyMustBeNullOnStartup(){
        Assertions.assertNull(appCache.getExchangeRatesResponseDTO());
    }

}