package tothetop.mainServer.gym.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tothetop.mainServer.utils.StringToBigDecimalDeserializer;
import tothetop.mainServer.utils.StringToLongDeserialize;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Gyms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @JsonDeserialize(using = StringToLongDeserialize.class)
    private Long naverId;

    @NotNull
    private String name;

    @Nullable
    private String thumbUrl;

    @NotNull
    private String address;

    @NotNull
    private String roadAddress;

    @Nullable
    private String metroString;

    @NotNull
    private boolean isPossibleParking;

    @JsonDeserialize(using = StringToBigDecimalDeserializer.class)
    private BigDecimal latitude;

    @JsonDeserialize(using = StringToBigDecimalDeserializer.class)
    private BigDecimal longitude;

    @Nullable
    private String ktCallMd;
}
