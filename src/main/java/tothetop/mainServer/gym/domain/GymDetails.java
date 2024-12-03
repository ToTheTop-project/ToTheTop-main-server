package tothetop.mainServer.gym.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tothetop.mainServer.gym.utils.StringToLongDeserialize;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GymDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gym_id", nullable = false)
    @JsonDeserialize(using = StringToLongDeserialize.class)
    private Long gymId;

    @Column(length = 20)
    private String tel;

    @Column(length = 2000)
    private String price;

    @Column(name = "naver_booking_url", length = 1000)
    private String naverBookingUrl;

    @Column(name = "homepage_url", length = 1000)
    private String homepageUrl;

    @Column(name = "gym_info", length = 5000)
    private String gymInfo;

    @Column(name = "metro_distance", length = 300)
    private String metroDistance;

    @Column(name = "parking_type", length = 20)
    private String parkingType;

    @Column(name = "parking_description", length = 1000)
    private String parkingDescription;

    @Column(name = "is_possible_wifi", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean isPossibleWifi = false;

    @Column(name = "is_possible_MW_toilet", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean isPossibleMWToilet = false;

    @Column(name = "is_possible_booking", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean isPossibleBooking = false;

    @Column(name = "is_possible_group", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean isPossibleGroup = false;

    @Column(name = "is_possible_animal", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean isPossibleAnimal = false;

    @Column(name = "is_possible_kids_zone", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean isPossibleKidsZone = false;

    @Column(name = "is_possible_disabled_parking", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean isPossibleDisabledParking = false;

    @Column(name = "is_possible_waiting_zone", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean isPossibleWaitingZone = false;

    @Column(length = 500)
    private String blog;

    @Column(length = 500)
    private String homepage;

    @Column(length = 500)
    private String instagram;

    @Column(length = 500)
    private String facebook;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_id", insertable = false, updatable = false)
    private Gyms gyms;
}

