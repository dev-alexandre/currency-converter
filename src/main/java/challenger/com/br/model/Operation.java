package challenger.com.br.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@With
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

    @Transient
    private BigDecimal amountTo;
}
