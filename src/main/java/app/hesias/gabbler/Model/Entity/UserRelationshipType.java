package app.hesias.gabbler.Model.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_relationship_type")
@Getter
@Setter
public class UserRelationshipType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRelationshipType", nullable = false)
    private int idRelationshipType;
    private String libelle;
}
