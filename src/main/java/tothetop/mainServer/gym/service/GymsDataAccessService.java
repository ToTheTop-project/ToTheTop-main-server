package tothetop.mainServer.gym.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tothetop.mainServer.gym.domain.GymDetails;
import tothetop.mainServer.gym.domain.GymDetailsJpaRepository;
import tothetop.mainServer.gym.domain.Gyms;
import tothetop.mainServer.gym.domain.GymsJpaRepository;

@Service
@AllArgsConstructor
@Transactional
public class GymsDataAccessService {
    private static final Logger log = Logger.getLogger(GymsDataAccessService.class.getName());
    private final GymsJpaRepository gymsJpaRepository;
    private final GymDetailsJpaRepository gymDetailsJpaRepository;

    public String saveGyms() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            List<Gyms> gyms = mapper.readValue(
                new File("/Users/shin-uijin/ToTheTop/data/data-server/express/Gym6.json"),
                mapper.getTypeFactory().constructCollectionType(List.class, Gyms.class)
            );

            // 중복 필터링 - Set을 사용하여 naverId 기준으로 중복 제거
            List<Gyms> filteredGyms = gyms.stream()
                .collect(Collectors.toMap(Gyms::getNaverId, gym -> gym, (existing, replacement) -> existing))
                .values()
                .stream()
                .toList();

            // 중복이 제거된 데이터 저장
            gymsJpaRepository.saveAll(filteredGyms);

        } catch (IOException e) {
            e.printStackTrace();
            return "failed";
        }

        return "success";
    }

    public String saveGymDetails() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            // JSON 파일을 JsonNode로 읽기
            JsonNode rootNode = mapper.readTree(new File("/Users/shin-uijin/ToTheTop/data/data-server/express/Gym6.json"));
            List<GymDetails> gymDetailsList = new ArrayList<>();

            for (JsonNode gymNode : rootNode) {
                Gyms gym = gymsJpaRepository.findByNaverId(Long.parseLong(gymNode.get("naverId").asText()));
                if (gym == null) {
                    continue;
                }

                JsonNode amenity = gymNode.get("amenity");

                Boolean isPossibleWifi = false;
                Boolean isPossibleMWToilet = false;
                Boolean isPossibleBooking = false;
                Boolean isPossibleGroup = false;
                Boolean isPossibleAnimal = false;
                Boolean isPossibleKidsZone = false;
                Boolean isPossibleDisabledParking = false;
                Boolean isPossibleWaitingZone = false;

                if (amenity != null && amenity.isArray()) {
                    // 'amenity' 필드 내에 특정 값이 포함되어 있는지 확인
                    for (JsonNode amenityElement : amenity) {
                        switch (amenityElement.asText()) {
                            case "무선 인터넷" -> isPossibleWifi = true;
                            case "남/녀 화장실 구분" -> isPossibleMWToilet = true;
                            case "예약" -> isPossibleBooking = true;
                            case "단체 이용 가능" -> isPossibleGroup = true;
                            case "반려동물 동반" -> isPossibleAnimal = true;
                            case "유아시설 (놀이방)" -> isPossibleKidsZone = true;
                            case "장애인 주차구역" -> isPossibleDisabledParking = true;
                            case "대기공간" -> isPossibleWaitingZone = true;
                        }
                    }
                }

                String blog = "";
                String homepage = "";
                String instagram = "";
                String facebook = "";
                if (gymNode.has("snsInfo")) {
                    JsonNode snsInfoNode = gymNode.get("snsInfo");
                    blog = snsInfoNode.has("blog") ? snsInfoNode.get("blog").asText() : "";
                    homepage = snsInfoNode.has("homepage") ? snsInfoNode.get("homepage").asText() : "";
                    instagram = snsInfoNode.has("instagram") ? snsInfoNode.get("instagram").asText() : "";
                    facebook = snsInfoNode.has("facebook") ? snsInfoNode.get("facebook").asText() : "";
                }

                boolean isPossibleParking = false;
                String parkingType = "";
                String parkingDescription = "";
                if (gymNode.has("parkingInfo")) {
                    JsonNode parkingInfoNode = gymNode.get("parkingInfo");
                    isPossibleParking = parkingInfoNode.has("isPossibleParking") && parkingInfoNode.get("isPossibleParking").asBoolean();
                    parkingType = parkingInfoNode.has("parkingType") ? parkingInfoNode.get("parkingType").asText() : "";
                    parkingDescription = parkingInfoNode.has("parkingDescription") ? parkingInfoNode.get("parkingDescription").asText() : "";
                }
                String gymInfo = "";
                if (gymNode.has("gymInfo") && gymNode.get("gymInfo").isArray()) {
                    StringBuilder gymInfoBuilder = new StringBuilder();
                    for (JsonNode info : gymNode.get("gymInfo")) {
                        if (gymInfoBuilder.length() > 0) {
                            gymInfoBuilder.append("\n"); // 항목 간의 구분을 위해 줄바꿈 추가
                        }
                        gymInfoBuilder.append(info.asText());
                    }
                    gymInfo = gymInfoBuilder.toString();
                }

                String tel = gymNode.has("tel") ? gymNode.get("tel").asText() : "";
                String price = gymNode.has("price") ? gymNode.get("price").asText() : "";
                String naverBookingUrl = gymNode.has("naverBookingUrl") ? gymNode.get("naverBookingUrl").asText() : "";
                String homepageUrl = gymNode.has("homepageUrl") ? gymNode.get("homepageUrl").asText() : "";
                String metroDistance = gymNode.has("metroDistance") ? gymNode.get("metroDistance").asText() : "";

                GymDetails gymDetails = new GymDetails(
                    null,
                    gym.getId(),
                    tel,
                    price,
                    naverBookingUrl,
                    homepageUrl,
                    gymInfo,
                    metroDistance,
                    parkingType,
                    parkingDescription,
                    isPossibleWifi,
                    isPossibleMWToilet,
                    isPossibleBooking,
                    isPossibleGroup,
                    isPossibleAnimal,
                    isPossibleKidsZone,
                    isPossibleDisabledParking,
                    isPossibleWaitingZone,
                    blog,
                    homepage,
                    instagram,
                    facebook,
                    gym
                );

                // 중복 제거 - 같은 gymId가 존재하면 추가하지 않음
                if (gymDetailsList.stream().noneMatch(details -> details.getGymId().equals(gymDetails.getGymId()))) {
                    gymDetailsList.add(gymDetails);
                }
            }

            // gymDetailsList를 데이터베이스에 저장
            gymDetailsJpaRepository.saveAll(gymDetailsList);

            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }
}
