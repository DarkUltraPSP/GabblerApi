package app.hesias.gabbler.Repository;

import app.hesias.gabbler.Model.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InteractionRepo extends JpaRepository<Interaction, Integer> {
}
