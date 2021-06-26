package challenger.com.br.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@WebFluxTest(DataUtil.class)
class DataUtilTest {

    @Autowired
    private DataUtil dataUtil;

    @Test
    public void testReturnTime(){
        Assertions.assertNotNull(dataUtil.getLocalDate());
    }
}