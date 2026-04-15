package mg.univUf.mami.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Utilisateur {
    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String pseudo;

    private String motDePasse;

    private String mail;
    
    private boolean enabled;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        ROLE_ADMIN,
        ROLE_USER
    }
}
