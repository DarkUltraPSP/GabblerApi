package app.hesias.gabbler.Repository;

import app.hesias.gabbler.Model.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteractionRepo extends JpaRepository<Interaction, Integer> {
}
