package challenger.com.br.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Set;
import java.util.TimeZone;

@SpringBootTest
@ContextConfiguration
class AppEnvironmentTest {

    @Autowired
    private AppEnvironment appEnvironment;

    @Test
    void notNullValuesFromAppEnvironment() {
        Assertions.assertNotNull(appEnvironment.getApiExchangeRatesBaseUrl());
        Assertions.assertNotNull(appEnvironment.getApiExchangeRatesBaseKey());
        Assertions.assertNotNull(appEnvironment.getApiFormatDateTime());
        Assertions.assertNotNull(appEnvironment.getTimeZone());
    }

    @Test
    void validTimeZone(){
        Assertions.assertTrue(Set.of(TimeZone.getAvailableIDs()).contains(appEnvironment.getTimeZone()));
    }
}