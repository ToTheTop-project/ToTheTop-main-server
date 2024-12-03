package tothetop.mainServer.gym.utils;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tothetop.mainServer.gym.domain.Gyms;
import tothetop.mainServer.gym.dto.GymsDto;
import tothetop.mainServer.utils.EntityMapper;

@Mapper
public interface GymsMapper extends EntityMapper<GymsDto, Gyms> {
    GymsMapper INSTANCE = Mappers.getMapper(GymsMapper.class);

    GymsDto toDto(Gyms gyms);
    Gyms toEntity(GymsDto dto);
}
