package challenger.com.br.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.junit.Assert.assertThrows;


@SpringBootTest
@ContextConfiguration
class LocalDateAdapterTest {

    private final String path = "src/test/java/resources/localDateAdapter/";
    private final String exampleData = path + "local-date-functional.json";

    private Gson deserialize;

    @PostConstruct
    public void setup() {
        deserialize = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
    }

    @Test
    void deserializeMinimalData() throws IOException {
        var data = Files.readString(Paths.get(exampleData));

        JsonObject json = deserialize.fromJson(data, new TypeToken<JsonObject>(){}.getType());
        LocalDate localDate =  deserialize.fromJson(json.get("date"), new TypeToken<LocalDate>(){}.getType());

        Assertions.assertNotNull(localDate);
    }

    @Test
    void deserializeDateTestingValues() throws IOException {

        var data = Files.readString(Paths.get(exampleData));

        LocalDate desiredDate = LocalDate.of(2021, 6, 24);
        JsonObject json = deserialize.fromJson(data, new TypeToken<JsonObject>(){}.getType());
        LocalDate localDate =  deserialize.fromJson(json.get("date"), new TypeToken<LocalDate>(){}.getType());

        Assertions.assertEquals(localDate, desiredDate);
    }

    @Test
    void deserializeMinAcceptableDate() throws IOException {

        String exampleDataMin = path + "local-date-min-acceptable.json";
        var data = Files.readString(Paths.get(exampleDataMin));

        LocalDate desiredDate = LocalDate.of(1, 1, 1);
        JsonObject json = deserialize.fromJson(data, new TypeToken<JsonObject>(){}.getType());
        LocalDate localDate =  deserialize.fromJson(json.get("date"), new TypeToken<LocalDate>(){}.getType());

        Assertions.assertEquals(localDate, desiredDate);
    }

    @Test
    void deserializeMaxAcceptableDate() throws IOException {

        String exampleDataMax = path + "local-date-max-acceptable.json";
        var data = Files.readString(Paths.get(exampleDataMax));

        LocalDate desiredDate = LocalDate.of(2150, 12, 12);
        JsonObject json = deserialize.fromJson(data, new TypeToken<JsonObject>(){}.getType());
        LocalDate localDate =  deserialize.fromJson(json.get("date"), new TypeToken<LocalDate>(){}.getType());

        Assertions.assertEquals(localDate, desiredDate);
    }

    @Test
    void deserializeDateWrongMonth() throws IOException{
        String exampleDataWrongMonth = path + "local-date-wrong-month.json";
        var data = Files.readString(Paths.get(exampleDataWrongMonth));
        JsonObject json = deserialize.fromJson(data, new TypeToken<JsonObject>(){}.getType());

        Exception exception = assertThrows(Exception.class, () ->
            deserialize.fromJson(json.get("date"), new TypeToken<LocalDate>(){}.getType())
        );

        Assertions.assertNotNull(exception);
    }

    @Test
    void deserializeDateWrongDay() throws IOException{
        String exampleDataWrongDay = path + "local-date-wrong-day.json";
        var data = Files.readString(Paths.get(exampleDataWrongDay));

        JsonObject json = deserialize.fromJson(data, new TypeToken<JsonObject>(){}.getType());

        Exception exception = assertThrows(Exception.class, () ->
            deserialize.fromJson(json.get("date"), new TypeToken<LocalDate>(){}.getType())
        );

        Assertions.assertNotNull(exception);
    }

}