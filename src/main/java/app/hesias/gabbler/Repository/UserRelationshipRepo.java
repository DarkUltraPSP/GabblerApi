package app.hesias.gabbler.Repository;

import app.hesias.gabbler.Model.Entity.UserRelationship;
import app.hesias.gabbler.Model.Entity.UserRelationshipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRelationshipRepo extends JpaRepository<UserRelationship, Integer> {
    @Query("SELECT urt " +
            "FROM UserRelationshipType urt " +
            "INNER JOIN UserRelationship ur ON urt.idRelationshipType = ur.userRelationshipType.idRelationshipType " +
            "WHERE ur.user1.idUser = :user1 AND ur.user2.idUser = :user2")
    UserRelationshipType getRelationshipType(int user1, int user2);

    @Query("SELECT ur " +
            "FROM UserRelationship ur " +
            "WHERE ur.user1.idUser = :user1 AND ur.user2.idUser = :user2")
    UserRelationship getByUser1AndUser2(int user1, int user2);
}
