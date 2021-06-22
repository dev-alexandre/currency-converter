package challenger.com.br.controller;

import challenger.com.br.config.AppConfig;
import challenger.com.br.exception.BadParameterException;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AppConfig appConfig;

    private Map<String, Object> getBody(Exception exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern(appConfig.getApiFormatDateTime())));
        body.put("message", exception.getMessage());
        return body;
    }

    @ExceptionHandler(value = BadParameterException.class)
    public ResponseEntity<?> BadParameterException(final BadParameterException exception) {
        return new ResponseEntity<>(getBody(exception), HttpStatus.BAD_REQUEST);
    }

}
