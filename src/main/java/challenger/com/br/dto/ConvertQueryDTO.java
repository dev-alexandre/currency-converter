package challenger.com.br.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ConvertQueryDTO {

    private String from;
    private String to;
    private BigDecimal amount;

}
