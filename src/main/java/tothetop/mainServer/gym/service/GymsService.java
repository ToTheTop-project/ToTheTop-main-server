package tothetop.mainServer.gym.service;

import java.util.List;
import org.springframework.stereotype.Service;
import tothetop.mainServer.gym.dto.GymsDto;

public interface GymsService {
    List<GymsDto> findAllGyms();
}
