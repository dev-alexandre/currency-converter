package challenger.com.br.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@ContextConfiguration
class AppRetrofitTest {

    @Autowired
    private AppRetrofit appRetrofit;

    @Autowired
    private AppEnvironment appEnvironment;

    @Test
    void testingRetrofitInitializerAndOptions() {
        var retrofit = appRetrofit.getRetrofit();

        assertNotNull(retrofit);
        assertEquals(retrofit.baseUrl().toString(), appEnvironment.getApiExchangeRatesBaseUrl());
    }

}