package app.hesias.gabbler.Repository;

import app.hesias.gabbler.Model.Entity.Gab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GabRepo extends JpaRepository<Gab, Integer> {
}
