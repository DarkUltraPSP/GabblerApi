package app.hesias.gabbler.Model.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "user_relationship")
@Getter
@Setter
public class UserRelationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRelationship", nullable = false)
    private int idRelationship;
    private Date createdAt = new Date();

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
