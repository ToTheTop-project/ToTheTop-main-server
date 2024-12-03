package tothetop.mainServer.gym.service;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tothetop.mainServer.gym.domain.Gyms;
import tothetop.mainServer.gym.domain.GymsJpaRepository;
import tothetop.mainServer.gym.dto.GymsDto;
import tothetop.mainServer.gym.utils.GymsMapper;

@Slf4j
@Service
@AllArgsConstructor
public class GymServiceImpl implements GymsService{
    private final GymsJpaRepository gymJpaRepository;
    private final GymsMapper gymsMapper = GymsMapper.INSTANCE;

    public List<GymsDto> findAllGyms() {
        List<Gyms> gymList = gymJpaRepository.findAll();
        if(gymList.isEmpty()) {
            throw new NullPointerException("Gyms list is empty");
        }

        List<GymsDto> gymsDtoList = new ArrayList<>();
        gymList.forEach(gym -> {
            GymsDto dto = gymsMapper.toDto(gym);
            gymsDtoList.add(dto);
        });


        return gymsDtoList;
    }
}
