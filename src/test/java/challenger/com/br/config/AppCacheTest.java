package challenger.com.br.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
@ContextConfiguration
class AppCacheTest {

    @Autowired
    AppCache appCache;

    @Test
    void mustBeInitialized() {
        assertNotNull(appCache);
    }

}