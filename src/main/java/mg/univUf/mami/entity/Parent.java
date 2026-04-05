package mg.univUf.mami.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Parent {

    @Id @GeneratedValue
    private Long id;

    private String nom;

    @Column(unique = true)
    private String numero;

    private String adresse;
}