package challenger.com.br.adpter;

import com.google.gson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.Map;

public class MapAdpter implements JsonDeserializer<Map> {

    Logger logger = LoggerFactory.getLogger(MapAdpter.class);

    @Override
    public Map deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        logger.debug("deserialize string {} to pattern", jsonElement.getAsJsonObject());
        return new Gson().fromJson(jsonElement.getAsJsonObject(), Map.class);
    }
}
