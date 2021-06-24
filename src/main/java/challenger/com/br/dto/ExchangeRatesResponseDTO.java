package challenger.com.br.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRatesResponseDTO {

    private Integer userId;

    private String base;

    private LocalDate date;

    private LocalDateTime timestamp;

    private Map<String, Double> rates;

    private List<String> currencies;
}
