package app.hesias.gabbler.Model.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "gab")
@Data
public class Gab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idGab", nullable = false)
    private int idGab;
    private String content;
    private LocalDateTime createdAt;
    private String mediaUrl;
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;
}
