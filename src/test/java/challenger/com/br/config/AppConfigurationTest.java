package challenger.com.br.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration
class AppConfigurationTest {

    @Autowired
    private AppConfiguration appConfiguration;

    @Autowired
    private AppEnvironment appEnvironment;

    @Test
    void testingTimezoneSetting() {
        appConfiguration.timeZoneConfiguration();

        assertEquals(TimeZone.getDefault().getID(), appEnvironment.getTimeZone());
    }

}