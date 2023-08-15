package app.hesias.gabbler.Repository;

import app.hesias.gabbler.Model.Entity.InteractionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InteractionTypeRepo extends JpaRepository<InteractionType, Integer> {
    @Query("SELECT i FROM InteractionType i WHERE i.libelle = :libelle")
    Optional <InteractionType> findByLibelle(@Param("libelle") String libelle);
}
