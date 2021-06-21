package challenger.com.br.config;

import challenger.com.br.adapter.HashMapAdapter;
import challenger.com.br.adapter.LocalDateAdapter;
import challenger.com.br.adapter.LocalDateTimeAdapter;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class AppRetrofit {

    @Autowired
    private AppConfig appConfig;

    public Retrofit getRetrofit(){
        var gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(Map.class, new HashMapAdapter())
                .create();

        return new Retrofit.Builder()
                .baseUrl(appConfig.getApiExchangeRatesBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(new OkHttpClient.Builder().build())
                .build();
    }
}
