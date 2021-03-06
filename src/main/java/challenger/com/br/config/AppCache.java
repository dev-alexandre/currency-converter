package challenger.com.br.config;

import challenger.com.br.dto.ExchangeRatesResponseDTO;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Component
@Scope("singleton")
public class AppCache {

    private ExchangeRatesResponseDTO exchangeRatesResponseDTO;

}
