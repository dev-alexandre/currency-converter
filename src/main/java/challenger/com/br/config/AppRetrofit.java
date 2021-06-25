package challenger.com.br.config;

import challenger.com.br.adapter.HashMapAdapter;
import challenger.com.br.adapter.LocalDateAdapter;
import challenger.com.br.adapter.LocalDateTimeAdapter;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class AppRetrofit {
    final Logger logger = LoggerFactory.getLogger(AppRetrofit.class);

    @Autowired
    private AppEnvironment appEnvironment;

    public Retrofit getRetrofit(){
        logger.debug("Create Retrofit instance");

        var gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(Map.class, new HashMapAdapter())
                .create();

        return new Retrofit.Builder()
                .baseUrl(appEnvironment.getApiExchangeRatesBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(new OkHttpClient.Builder().build())
                .build();
    }
}
