package challenger.com.br.adapter;

import com.google.gson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.Map;

public class HashMapAdapter implements JsonDeserializer<Map<String, Double>> {
    final Logger logger = LoggerFactory.getLogger(HashMapAdapter.class);

    @Override
    public Map<String, Double> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        logger.debug("deserialize string {} to pattern", jsonElement.getAsJsonObject());
        return new Gson().fromJson(jsonElement.getAsJsonObject(), Map.class);
    }
}
