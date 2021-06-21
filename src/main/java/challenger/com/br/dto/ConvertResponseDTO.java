package challenger.com.br.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConvertResponseDTO {

    private Boolean success;
    private LocalDateTime date;
    private BigDecimal result;
    private ConvertQueryDTO query;

}
