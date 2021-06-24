package challenger.com.br.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Set;
import java.util.TimeZone;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@ContextConfiguration
class AppEnvironmentTest {

    @Autowired
    private AppEnvironment appEnvironment;

    @Test
    void notNullValuesFromAppEnvironment() {

        assertNotNull(appEnvironment.getApiExchangeRatesBaseUrl());
        assertNotNull(appEnvironment.getApiExchangeRatesBaseKey());
        assertNotNull(appEnvironment.getApiFormatDateTime());
        assertNotNull(appEnvironment.getTimeZone());
    }

    @Test
    void validTimeZone(){
        assertTrue(Set.of(TimeZone.getAvailableIDs()).contains(appEnvironment.getTimeZone()));
    }

    @Test
    void validDateTimeFormat(){

    }

}