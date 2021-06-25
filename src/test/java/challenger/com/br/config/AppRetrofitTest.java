package challenger.com.br.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

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
        Assertions.assertNotNull(retrofit);
        Assertions.assertEquals(retrofit.baseUrl().toString(), appEnvironment.getApiExchangeRatesBaseUrl());
    }

}