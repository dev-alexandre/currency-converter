package challenger.com.br.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter implements JsonDeserializer<LocalDate> {
    final Logger logger = LoggerFactory.getLogger(LocalDateAdapter.class);

    @Override
    public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        logger.debug("deserialize string {} to pattern yyyy-MM-dd", jsonElement.getAsString());

        var rawString = jsonElement.getAsString();
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return LocalDate.parse(rawString, formatter);

    }
}
