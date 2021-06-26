package challenger.com.br.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

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
    @Column(value = "user_id")
    private Integer userId;

    @NotNull
    @Column(value = "operation_date")
    private String operationDate;

    @NotNull
    @Column(value = "currency_from")
    private String currencyFrom;

    @NotNull
    @Column(value = "currency_to")
    private String currencyTo;

    @NotNull
    @Column(value = "amount_from")
    private BigDecimal amountFrom;

    @NotNull
    @Column(value = "rate")
    private BigDecimal rate;

    @NotNull
    @Column(value = "amount_to")
    private BigDecimal amountTo;

}
