package mg.univUf.mami.entity;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String pseudo;
    private String ancienMotDePasse;
    private String nouveauMotDePasse;

    // getters setters
}