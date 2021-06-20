package challenger.com.br.adpter;

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

public class LocalDateTimeAdpter implements JsonDeserializer<LocalDateTime> {

    Logger logger = LoggerFactory.getLogger(LocalDateTimeAdpter.class);

    @Override
    public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        logger.debug("deserialize string {} to pattern", jsonElement.getAsString());

        String rawString = jsonElement.getAsString();

        try {
            long jsonValueAsLong = Long.valueOf(rawString);

            LocalDateTime localDateTime = Instant.ofEpochMilli(jsonValueAsLong).atZone(ZoneId.systemDefault()).toLocalDateTime();
            return localDateTime;

        } catch (Exception e){
            logger.debug("Value {} can't be converted to LocalDateTime", rawString);
            return null;
        }

    }
}
