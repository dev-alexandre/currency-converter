package challenger.com.br.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("operation")
public class Operation {

    @Id
    @JsonProperty("TransactionID")
    private Integer id;

    @NotNull
    private Integer userId;

    @NotNull
    private LocalDate operationDate;

    @NotNull
    private BigDecimal result;

    @NotNull
    private String currencyFrom;

    @NotNull
    private String currencyTo;

    @NotNull
    private BigDecimal amount;

}
