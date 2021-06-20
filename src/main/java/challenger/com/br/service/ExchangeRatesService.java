package challenger.com.br.service;

import challenger.com.br.adpter.LocalDateAdpter;
import challenger.com.br.adpter.LocalDateTimeAdpter;
import challenger.com.br.adpter.MapAdpter;
import challenger.com.br.config.AppConfig;
import challenger.com.br.dto.ExchangeRatesResponseDTO;
import challenger.com.br.repository.ExchangeRatesRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class ExchangeRatesService {

    /**
     * @TODO mover para appRetrofit
    * */
    private Retrofit getRetrofit(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdpter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdpter())
                .registerTypeAdapter(Map.class, new MapAdpter())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.exchangeratesapi.io/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(new OkHttpClient.Builder().build())
                .build();

        return retrofit;
    }

    public ExchangeRatesResponseDTO getTeste(){

        ExchangeRatesRepository repository = getRetrofit().create(ExchangeRatesRepository.class);
        Call<ExchangeRatesResponseDTO> callSync = repository.getExchangeRatesResponseDTO("7f7671223482746ce8247497072a30b5");

        try {

            Response<ExchangeRatesResponseDTO> response = callSync.execute();
            ExchangeRatesResponseDTO exchangeRatesResponseDTO = response.body();

            return exchangeRatesResponseDTO;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }


}
