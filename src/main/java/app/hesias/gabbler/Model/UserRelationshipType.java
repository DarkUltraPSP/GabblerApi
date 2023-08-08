package app.hesias.gabbler.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "user_relationship_type")
@Data
public class UserRelationshipType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRelationshipType", nullable = false)
    private int idRelationshipType;
    private String libelle;
}
