package app.hesias.gabbler.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "UserRelationship")
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
}
