package app.hesias.gabbler.Repository;

import app.hesias.gabbler.Model.UserRelationshipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRelationshipTypeRepo extends JpaRepository<UserRelationshipType, Integer> {
    @Query("SELECT u FROM UserRelationshipType u WHERE u.libelle = :libelle")
    UserRelationshipType findByLibelle(@Param("libelle") String libelle);
}
