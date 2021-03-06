package challenger.com.br.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Getter
@Service
public class AppEnvironment {

    @Value("${api.exchange-rates-api.baseUrl}")
    private String apiExchangeRatesBaseUrl;

    @Value("${api.exchange-rates-api.key}")
    private String apiExchangeRatesBaseKey;

    @Value("${api.format.date-time}")
    private String apiFormatDateTime;

    @Value("${api.format.timezone}")
    private String timeZone;

}
