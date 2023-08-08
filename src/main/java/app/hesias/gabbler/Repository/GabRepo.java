package app.hesias.gabbler.Repository;

import app.hesias.gabbler.Model.Gab;
import app.hesias.gabbler.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GabRepo extends JpaRepository<Gab, Integer> {
}
