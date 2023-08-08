package app.hesias.gabbler.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "interaction")
@Data
public class Interaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idInteraction", nullable = false)
    private int idInteraction;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idGab")
    private Gab gab;

    @ManyToOne
    @JoinColumn(name = "idInteractionType")
    private InteractionType interactionType;
}
