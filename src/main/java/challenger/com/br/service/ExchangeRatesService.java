package challenger.com.br.service;

import challenger.com.br.config.AppCache;
import challenger.com.br.config.AppEnvironment;
import challenger.com.br.config.AppRetrofit;
import challenger.com.br.dto.ExchangeRatesResponseDTO;
import challenger.com.br.exception.ThirdPartyException;
import challenger.com.br.repository.retrofit.ExchangeRatesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ExchangeRatesService {
    final Logger logger = LoggerFactory.getLogger(ExchangeRatesService.class);

    @Autowired
    private AppEnvironment appConfig;

    @Autowired
    private AppRetrofit retrofit;

    @Autowired
    private AppCache appCache;

    public ExchangeRatesResponseDTO getExchangeRates(){


        if(appCache.getExchangeRatesResponseDTO() != null && LocalDate.now().isEqual(appCache.getExchangeRatesResponseDTO().getDate())){
            logger.info("Getting exchange rates from cache");
            return appCache.getExchangeRatesResponseDTO();
        }

        return loadExchangeRatesRepository();
    }

    public ExchangeRatesResponseDTO loadExchangeRatesRepository(){
        logger.info("Calling exchange rates api");

        ExchangeRatesRepository repository = retrofit.getRetrofit().create(ExchangeRatesRepository.class);
        Call<ExchangeRatesResponseDTO> callSync = repository.getExchangeRatesResponseDTO(appConfig.getApiExchangeRatesBaseKey());

        try {

            Response<ExchangeRatesResponseDTO> response = callSync.execute();
            ExchangeRatesResponseDTO result = response.body();
            result.setCurrencies(new ArrayList<>(Optional.ofNullable(result.getRates().keySet()).orElseThrow()));
            appCache.setExchangeRatesResponseDTO(result);

            return result;

        } catch (Exception ex) {
            logger.error("Error calling exchange rates api", ex);
            throw new ThirdPartyException("A third party service is not working as it should, wait a while and try again in a few minutes");
        }
    }
}
