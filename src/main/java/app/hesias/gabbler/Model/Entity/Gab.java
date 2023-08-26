package app.hesias.gabbler.Model.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "gab")
@Getter
@Setter
public class Gab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idGab", nullable = false)
    private int idGab;
    @Column(name = "content", nullable = false, length = 255)
    private String content;
    private Date createdAt = new Date();
    private String mediaUrl;
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;
}
