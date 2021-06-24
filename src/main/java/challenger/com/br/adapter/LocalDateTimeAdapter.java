package challenger.com.br.adapter;

import challenger.com.br.config.AppEnvironment;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Service
public class LocalDateTimeAdapter implements JsonDeserializer<LocalDateTime> {

    @Autowired
    private AppEnvironment appEnvironment;

    Logger logger = LoggerFactory.getLogger(LocalDateTimeAdapter.class);

    @Override
    public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        logger.debug("deserialize string {} to pattern", jsonElement.getAsString());

        var rawString = jsonElement.getAsString();
        var jsonValueAsLong = Long.parseLong(rawString);

        ZoneId zone = ZoneId.of(appEnvironment.getTimeZone());
        ZoneOffset zoneOffSet = zone.getRules().getOffset(LocalDateTime.now());

        return LocalDateTime.ofEpochSecond(jsonValueAsLong, 0, zoneOffSet);
    }
}
