package tothetop.mainServer.gym;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tothetop.mainServer.gym.dto.GymsDto;
import tothetop.mainServer.gym.service.GymsDataAccessService;
import tothetop.mainServer.gym.service.GymsService;
import tothetop.mainServer.utils.ApiUtils;
import tothetop.mainServer.utils.ApiUtils.ApiResult;

@AllArgsConstructor
@RestController
@RequestMapping("/api/gyms")
public class GymRestController {
    private final GymsDataAccessService gymsDataAccessService;
    private final GymsService gymsService;

    @GetMapping("/saveDataAccess")
    public void saveDataAccess() {
        gymsDataAccessService.saveGyms();
    }

    @GetMapping("/saveDetailsAccess")
    public void saveDetailsAccess() {
        gymsDataAccessService.saveGymDetails();
    }

    @GetMapping("/all")
    public ApiResult<List<GymsDto>> getAllGyms() {
        try{
            List<GymsDto> resultList = gymsService.findAllGyms();
            return ApiUtils.success(resultList);
        }catch(NullPointerException e){
            return ApiUtils.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
