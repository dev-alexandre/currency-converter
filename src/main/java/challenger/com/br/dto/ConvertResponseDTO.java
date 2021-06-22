package challenger.com.br.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConvertResponseDTO {

    private Integer userId;
    private Boolean success;
    private LocalDate date;
    private BigDecimal result;
    private ConvertQueryDTO query;

}
