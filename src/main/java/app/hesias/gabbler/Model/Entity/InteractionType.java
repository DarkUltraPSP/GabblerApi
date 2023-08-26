package app.hesias.gabbler.Model.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "InteractionType")
@Getter
@Setter
public class InteractionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idInteractionType", nullable = false)
    private int idInteractionType;
    private String libelle;
}
