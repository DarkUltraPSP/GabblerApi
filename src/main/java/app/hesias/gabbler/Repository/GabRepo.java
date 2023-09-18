package app.hesias.gabbler.Repository;

import app.hesias.gabbler.Model.Entity.Gab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GabRepo extends JpaRepository<Gab, Integer> {
    @Query(value = "SELECT * FROM gab ORDER BY created_at DESC", nativeQuery = true)
    List<Gab> findAll();

    @Query("SELECT g FROM Gab g WHERE g.user.idUser = :id")
    Optional<List<Gab>> findGabsByUserId(int id);

    @Query("SELECT g FROM Gab g WHERE g.content LIKE %:content%")
    Optional<List<Gab>> searchByContent(String content);
}
