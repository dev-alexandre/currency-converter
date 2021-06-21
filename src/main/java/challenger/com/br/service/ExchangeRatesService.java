package challenger.com.br.service;

import challenger.com.br.config.AppConfig;
import challenger.com.br.config.AppRetrofit;
import challenger.com.br.dto.ExchangeRatesResponseDTO;
import challenger.com.br.exception.ThirdPartyException;
import challenger.com.br.repository.ExchangeRatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

@Service
public class ExchangeRatesService {

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private AppRetrofit retrofit;

    public ExchangeRatesResponseDTO getExchangeRates(){

        ExchangeRatesRepository repository = retrofit.getRetrofit().create(ExchangeRatesRepository.class);
        Call<ExchangeRatesResponseDTO> callSync = repository.getExchangeRatesResponseDTO(appConfig.getApiExchangeRatesBaseKey());

        try {

            Response<ExchangeRatesResponseDTO> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ThirdPartyException("A third party service is not working as it should, wait a while and try again in a few minutes");
        }
    }


}
