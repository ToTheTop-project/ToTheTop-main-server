package tothetop.mainServer.gym.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.math.BigDecimal;

public class StringToBigDecimalDeserializer extends JsonDeserializer<BigDecimal> {
    @Override
    public BigDecimal deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
        throws IOException, JsonProcessingException {
        String value = jsonParser.getText();
        try {
            return new BigDecimal(value);
        } catch (NumberFormatException | NullPointerException e) {
            return BigDecimal.ZERO; // 기본값 설정 (예: 0.0)
        }
    }
}
