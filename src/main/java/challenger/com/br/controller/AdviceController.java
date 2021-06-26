package challenger.com.br.controller;

import challenger.com.br.exception.BadParameterException;
import challenger.com.br.exception.ThirdPartyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class AdviceController {
    final Logger logger = LoggerFactory.getLogger(AdviceController.class);

    private Map<String, Object> getBody(Exception exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss")));
        body.put("message", exception.getMessage());
        return body;
    }

    @ExceptionHandler(value = BadParameterException.class)
    public ResponseEntity<Map<String, Object>> badParameterException(final BadParameterException exception) {
        logger.info("Response: bad parameters");
        return new ResponseEntity<>(getBody(exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ThirdPartyException.class)
    public ResponseEntity<Map<String, Object>> thirdPartyException(final ThirdPartyException exception) {
        logger.info("Response: third party failure");
        return new ResponseEntity<>(getBody(exception), HttpStatus.SERVICE_UNAVAILABLE);
    }
}
