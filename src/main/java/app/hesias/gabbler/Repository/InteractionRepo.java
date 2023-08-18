package app.hesias.gabbler.Repository;

import app.hesias.gabbler.Model.Entity.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InteractionRepo extends JpaRepository<Interaction, Integer> {
    //Find all interactions by gab id
    @Query("SELECT i FROM Interaction i " +
            "WHERE i.gab.idGab = :idGab")
    Optional <List<Interaction>> findAllByGabId(@Param("idGab") int id);

    @Query("SELECT i FROM Interaction i " +
            "WHERE i.user.idUser = :idUser")
    Optional <List<Interaction>> findAllByUserId(@Param("idUser") int id);

    @Query("SELECT i FROM Interaction i " +
            "WHERE i.interactionType.libelle = :type AND i.gab.idGab = :idGab")
    Optional <List<Interaction>> findByTypeByGabs(@Param("type") String type,@Param("idGab") int idGab);

    @Query("SELECT i FROM Interaction i " +
            "WHERE i.gab = :idGab AND i.user.idUser = :idUser")
    Optional <Interaction> findByGabByUser(@Param("idGab") int idGab,@Param("idUser") int idUser);
}
