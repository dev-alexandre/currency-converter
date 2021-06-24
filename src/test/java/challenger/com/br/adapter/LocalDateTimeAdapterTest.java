package challenger.com.br.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

@SpringBootTest
@ContextConfiguration
class LocalDateTimeAdapterTest {

    private final String path = "src/test/java/resources/localDateTimeAdapter/";
    private final String exampleData = path + "local-date-time-functional.json";
    private final String exampleDataWrong = path + "local-date-time-wrong.json";

    private Gson deserialize;

    @Autowired
    private LocalDateTimeAdapter localDateTimeAdapter;

    @PostConstruct
    public void setup() {
        deserialize = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, localDateTimeAdapter).create();
    }

    @Test
    void deserializeMinimalData() throws IOException {
        var data = Files.readString(Paths.get(exampleData));

        JsonObject json = deserialize.fromJson(data, new TypeToken<JsonObject>(){}.getType());
        JsonElement testableElement = json.get("date");
        LocalDateTime localDate =  deserialize.fromJson(testableElement, new TypeToken<LocalDateTime>(){}.getType());

        assertNotNull(localDate);
    }

    @Test
    void deserializeLocalDateTimeAdapterTestValues() throws IOException {
        var data = Files.readString(Paths.get(exampleData));
        LocalDateTime desiredDate = LocalDateTime.of(2021,06,25,1,33,37);

        JsonObject json = deserialize.fromJson(data, new TypeToken<JsonObject>(){}.getType());
        JsonElement testableElement = json.get("date");
        LocalDateTime localDate =  deserialize.fromJson(testableElement, new TypeToken<LocalDateTime>(){}.getType());

        assertEquals(localDate, desiredDate);
    }

    @Test
    void deserializeDateWrongFormatter() throws IOException{
        var data = Files.readString(Paths.get(exampleDataWrong));
        JsonObject json = deserialize.fromJson(data, new TypeToken<JsonObject>(){}.getType());

        Exception exception = assertThrows(Exception.class, () -> {
            deserialize.fromJson(json.get("date"), new TypeToken<LocalDate>(){}.getType());
        });

        Assertions.assertNotNull(exception);
    }



}