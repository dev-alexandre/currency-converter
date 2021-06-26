package challenger.com.br.service;

import challenger.com.br.dto.ExchangeRatesResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@WebFluxTest(CurrencyService.class)
public class CurrencyServiceTest {

    @MockBean
    public ExchangeRatesService service;

    @Autowired
    CurrencyService currencyService;

    @Test
    public void mustReturnListOfStrings(){
        final int usd = 0;
        final int brl = 1;

        final String usdValue = "USD";
        final String brlValue = "BRL";

        Map <String, Double> ratesMap = new HashMap<>();
        ratesMap.put(usdValue,1D);
        ratesMap.put(brlValue,1D);

        ExchangeRatesResponseDTO exchangeRates = ExchangeRatesResponseDTO
                .builder()
                .rates(ratesMap)
                .build();

        when(service.getExchangeRates()).thenReturn(exchangeRates);

        var rates = currencyService.getAcceptedCurrencies();
        Assertions.assertNotNull(rates);
        Assertions.assertEquals(rates.block().size(), 2);

        Assertions.assertEquals(rates.block().get(usd), usdValue);
        Assertions.assertEquals(rates.block().get(brl), brlValue);
    }

}