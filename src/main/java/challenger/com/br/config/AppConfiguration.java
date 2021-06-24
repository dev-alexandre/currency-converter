package challenger.com.br.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Configuration
public class AppConfiguration {
    Logger logger = LoggerFactory.getLogger(AppConfiguration.class);

    @Autowired
    private AppEnvironment appConfig;

    @PostConstruct
    public void timeZoneConfiguration() {
        logger.debug("setting timezone: {}", appConfig.getTimeZone());
        TimeZone.setDefault(TimeZone.getTimeZone(appConfig.getTimeZone()));
    }
}
