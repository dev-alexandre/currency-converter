package challenger.com.br.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * This class make simple mock time
 **/

@Service
public class DataUtil {

    public LocalDate getLocalDate(){
        return LocalDate.now();
    }
}
