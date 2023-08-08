package app.hesias.gabbler.Repository;

import app.hesias.gabbler.Model.User;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepo extends JpaRepository<User, Integer> {
}
