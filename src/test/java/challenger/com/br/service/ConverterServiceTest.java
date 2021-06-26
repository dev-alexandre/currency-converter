package challenger.com.br.service;

import challenger.com.br.config.AppEnvironment;
import challenger.com.br.dto.ExchangeRatesResponseDTO;
import challenger.com.br.model.Operation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@WebFluxTest(ConverterService.class)
public class ConverterServiceTest {

    @MockBean
    private ExchangeRatesService exchangeRatesService;

    @MockBean
    private OperationService operationService;

    @MockBean
    private CalculationEngine calculationEngine;

    @Autowired
    private ConverterService converterService;

    @MockBean
    private AppEnvironment appEnvironment;

    public ExchangeRatesResponseDTO exampleData;

    public Operation operation;

    @PostConstruct
    public void setup(){
        final String usdValue = "USD";
        final String brlValue = "BRL";

        Map<String, Double> ratesMap = new HashMap<>();
        ratesMap.put(usdValue,1D);
        ratesMap.put(brlValue,1D);

        exampleData = ExchangeRatesResponseDTO
                .builder()
                .rates(ratesMap)
                .build();

        operation = Operation.builder().id(1).build();
    }

    @Test
    public void mustNotThrowWhenFindCurrency(){
        try{
            converterService.validCurrency("BRL",exampleData);
        } catch (Exception ex){
            Assertions.fail(ex.getMessage());
        }
    }

    @Test
    public void mustThrowWhenFindCurrency(){

        Exception exception = assertThrows(Exception.class, () ->
            converterService.validCurrency("NOT_BRL",exampleData)
        );

        Assertions.assertNotNull(exception);
    }

    @Test
    public void mustThrowExceptionWhenReceiverNegativeValue(){
        Exception exception = assertThrows(Exception.class, () ->
            converterService.validAmount(new BigDecimal(-1))
        );

        Assertions.assertNotNull(exception);
    }

    @Test
    public void mustNotThrowExceptionWhenReceiverNegativeValue(){
        try{
            converterService.validAmount(BigDecimal.TEN);
        } catch (Exception ex){
            Assertions.fail(ex.getMessage());
        }

    }

    @Test
    public void validParameters(){
        try{
            converterService.validParameters("BRL", "USD", BigDecimal.TEN, exampleData);
        } catch (Exception ex){
            Assertions.fail(ex.getMessage());
        }
    }

    @Test
    public void validParametersThrowWhenCurrencyFromNotFind(){
        Exception exception = assertThrows(Exception.class, () ->
            converterService.validParameters("NOT_BRL", "USD", BigDecimal.TEN, exampleData)
        );
        Assertions.assertNotNull(exception);
    }

    @Test
    public void validParametersThrowWhenCurrencyToNotFind(){
        Exception exception = assertThrows(Exception.class, () ->
            converterService.validParameters("BRL", "TO_USD", BigDecimal.TEN, exampleData)
        );
        Assertions.assertNotNull(exception);
    }

    @Test
    public void converterAmount(){

        when(exchangeRatesService.getExchangeRates()).thenReturn(exampleData);
        when(appEnvironment.getApiFormatDateTime()).thenReturn("yyyy-mm-dd hh:mm:ss");
        Operation build = Operation.builder().build();

        Mono<Operation> operationMono = converterService.converterAmount(10, "BRL", "USD", BigDecimal.ONE);
        Assertions.assertNotNull(operationMono);
        Assertions.assertNotNull(operationMono.block());
    }

}