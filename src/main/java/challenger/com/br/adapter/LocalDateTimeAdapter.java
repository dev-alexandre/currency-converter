package challenger.com.br.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocalDateTimeAdapter implements JsonDeserializer<LocalDateTime> {

    Logger logger = LoggerFactory.getLogger(LocalDateTimeAdapter.class);

    @Override
    public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        logger.debug("deserialize string {} to pattern", jsonElement.getAsString());

        var rawString = jsonElement.getAsString();

        try {
            var jsonValueAsLong = Long.parseLong(rawString);
            return Instant.ofEpochMilli(jsonValueAsLong).atZone(ZoneId.systemDefault()).toLocalDateTime();

        } catch (Exception e){
            logger.debug("Value {} can't be converted to LocalDateTime", rawString);
            return null;
        }

    }
}