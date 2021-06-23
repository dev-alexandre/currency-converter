package challenger.com.br.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRatesResponseDTO implements Serializable {

    private Integer userId;

    private String base;

    private LocalDate date;

    private LocalDateTime timestamp;

    private Map<String, Double> rates;

    private List<String> currencies;
}
