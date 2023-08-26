package app.hesias.gabbler.Model.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "interaction")
@Getter
@Setter
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
