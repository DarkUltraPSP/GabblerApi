package app.hesias.gabbler.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_relationship")
@Data
public class UserRelationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRelationship", nullable = false)
    private int idRelationship;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "idRelationshipType", nullable = false)
    private UserRelationshipType userRelationshipType;

    @ManyToOne
    @JoinColumn(name = "idUser1", nullable = false)
    private User user1;

    @ManyToOne
    @JoinColumn(name = "idUser2", nullable = false)
    private User user2;

}
