package app.hesias.gabbler.Model.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "utilisateur")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser", nullable = false)
    private int idUser;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private LocalDateTime birthdate;
    private int phone;
    private String password;
    private String biography;
    private LocalDateTime createdAt;
    private String profilePictureUrl;
    private String bannerPictureUrl;
    private boolean isActivated;
    private boolean isBanned;
    private boolean isPrivate;
}
