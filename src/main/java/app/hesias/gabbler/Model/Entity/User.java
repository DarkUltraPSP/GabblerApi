package app.hesias.gabbler.Model.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "utilisateur")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser", nullable = false)
    private int idUser;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private Date birthdate;
    private int phone;
    private String password;
    private String biography;
    private Date createdAt = new Date();
    private String profilePictureUrl;
    private String bannerPictureUrl;
    private boolean isActivated;
    private boolean isBanned;
    private boolean isPrivate;
}
