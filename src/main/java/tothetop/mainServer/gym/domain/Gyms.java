package tothetop.mainServer.gym.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
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
import tothetop.mainServer.gym.utils.StringToBigDecimalDeserializer;
import tothetop.mainServer.gym.utils.StringToLongDeserialize;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Gyms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nullable
    @Column(name = "naver_id", unique = true)
    @JsonDeserialize(using = StringToLongDeserialize.class)
    private Long naverId;

    @NotNull
    private String name;

    @Nullable
    @Column(name = "thumb_url")
    private String thumbUrl;

    @NotNull
    private String address;

    @Nullable
    @Column(name = "road_address")
    private String roadAddress;

    @Nullable
    @Column(name = "metro_string")
    private String metroString;

    @Nullable
    @Column(name = "is_possible_parking", columnDefinition = "TINYINT(1)")
    private Boolean isPossibleParking;

    @JsonDeserialize(using = StringToBigDecimalDeserializer.class)
    private BigDecimal latitude;

    @JsonDeserialize(using = StringToBigDecimalDeserializer.class)
    private BigDecimal longitude;

    @Nullable
    @Column(name = "kt_call_md")
    private String ktCallMd;
}
