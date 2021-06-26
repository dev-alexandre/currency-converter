package challenger.com.br.service;

import challenger.com.br.config.AppCache;
import challenger.com.br.config.AppEnvironment;
import challenger.com.br.config.AppRetrofit;
import challenger.com.br.dto.ExchangeRatesResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest(ExchangeRatesService.class)
class ExchangeRatesServiceTest {

    @MockBean
    private AppEnvironment appConfig;

    @MockBean
    private AppRetrofit appRetrofit;

    @MockBean
    private AppCache appCache;

    @Autowired
    private  ExchangeRatesService exchangeRatesService;

    @MockBean
    private DataUtil dataUtil;

    private ExchangeRatesResponseDTO exampleData;

    @PostConstruct
    public void setup(){
        final String usdValue = "USD";
        final String brlValue = "BRL";

        Map<String, Double> ratesMap = new HashMap<>();
        ratesMap.put(usdValue,1D);
        ratesMap.put(brlValue,1D);

        ExchangeRatesResponseDTO exampleData = ExchangeRatesResponseDTO
                .builder()
                .rates(ratesMap)
                .date(LocalDate.of(2000,1,1))
                .build();

        when(appCache.getExchangeRatesResponseDTO()).thenReturn(exampleData);
    }

    @Test
    public void mustReturnCacheWhenHaveSameDate(){
        when(dataUtil.getLocalDate()).thenReturn(LocalDate.of(2000,1,1));
        Assertions.assertNotNull(exchangeRatesService.getExchangeRates());
    }

}