package app.hesias.gabbler.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "InteractionType")
@Data
public class InteractionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idInteractionType", nullable = false)
    private int idInteractionType;
    private String libelle;
}
