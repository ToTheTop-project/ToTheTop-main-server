package tothetop.mainServer.gym.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public class StringToLongDeserialize extends JsonDeserializer<Long> {

    @Override
    public Long deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
        throws IOException, JsonProcessingException {
        String value = jsonParser.getText();
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return 0L; // 기본값 설정 (예: 0.0)
        }
    }
}
