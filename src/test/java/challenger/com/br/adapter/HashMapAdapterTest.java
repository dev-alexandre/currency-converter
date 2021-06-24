package challenger.com.br.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.Assert.*;

@SpringBootTest
@ContextConfiguration
class HashMapAdapterTest {

    private final String path = "src/test/java/resources/hashMapAdapter/";
    private final String ratesMiniumExampleFunctional = path + "rates-minimum-example-functional.json";
    private final String ratesExtensiveExampleFunctional = path + "rates-extensive-example-functional.json";
    private final String ratesExampleWithEmptyKey = path + "rates-example-with-problem-empty-key.json";
    private final String ratesExampleWithEmptyValue = path + "rates-example-with-problem-empty-value.json";

    private Gson deserialize;

    @PostConstruct
    public void setup(){
        deserialize = new GsonBuilder()
            .registerTypeAdapter(Map.class, new HashMapAdapter()).create();
    }

    @Test
    void deserializeMinimalData() throws IOException {
        var data = Files.readString(Paths.get(ratesMiniumExampleFunctional));

        final String currencyTest = "AED";
        Map<String, Double> result =  deserialize.fromJson(data, new TypeToken<Map<String, Double>>(){}.getType());
        assertNotNull(result);
        assertEquals(result.size(), 1);
        assertTrue(result.containsKey(currencyTest));
        Double aed = java.util.Optional.ofNullable(result.get(currencyTest)).orElse(-1D);
        Assertions.assertEquals(aed, 4.386531D);
    }


    @Test
    void deserializeExtensiveData() throws IOException {
        var data = Files.readString(Paths.get(ratesExtensiveExampleFunctional));
        Map<String, Double> result =  deserialize.fromJson(data, new TypeToken<Map<String, Double>>(){}.getType());
        assertNotNull(result);
        assertEquals(result.size(), 168);
    }

    @Test
    void deserializeCathyErrorOnInvalidDataKey() throws IOException {
        var data = Files.readString(Paths.get(ratesExampleWithEmptyKey));

        JsonSyntaxException exception = assertThrows(JsonSyntaxException.class, () -> {
            deserialize.fromJson(data, new TypeToken<Map<String, Double>>(){}.getType());
        });

        assertNotNull(exception);
    }

    @Test
    void deserializeCathyErrorOnInvalidDataValue() throws IOException {
        var data = Files.readString(Paths.get(ratesExampleWithEmptyValue));

        JsonSyntaxException exception = assertThrows(JsonSyntaxException.class, () -> {
            deserialize.fromJson(data, new TypeToken<Map<String, Double>>(){}.getType());
        });

        assertNotNull(exception);
    }

}