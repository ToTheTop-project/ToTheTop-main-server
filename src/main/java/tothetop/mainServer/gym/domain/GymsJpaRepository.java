package tothetop.mainServer.gym.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymsJpaRepository extends JpaRepository<Gyms, Long> {

    Gyms findByNaverId(Long naverId);
}
