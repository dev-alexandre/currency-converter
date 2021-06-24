package challenger.com.br.repository.retrofit;

import challenger.com.br.dto.ExchangeRatesResponseDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ExchangeRatesRepository {

    @GET("v1/latest")
    Call<ExchangeRatesResponseDTO> getExchangeRatesResponseDTO(@Query("access_key") String accessKey);

}
