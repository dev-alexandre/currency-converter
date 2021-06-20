package challenger.com.br.adpter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdpter implements JsonDeserializer<LocalDate> {

    Logger logger = LoggerFactory.getLogger(LocalDateAdpter.class);

    @Override
    public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        logger.debug("deserialize string {} to pattern yyyy-MM-dd", jsonElement.getAsString());

        String rawString = jsonElement.getAsString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime = LocalDate.parse(rawString, formatter);

        return dateTime;

    }
}
