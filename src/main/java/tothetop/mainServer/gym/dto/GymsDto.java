package tothetop.mainServer.gym.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tothetop.mainServer.gym.utils.StringToBigDecimalDeserializer;
import tothetop.mainServer.gym.utils.StringToLongDeserialize;

//@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GymsDto {

    private Long id;

    private Long naverId;

    private String name;

    private String thumbUrl;

    private String address;

    private String roadAddress;

    private String metroString;

    private Boolean isPossibleParking;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private String ktCallMd;
}
