package mg.univUf.mami.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mg.univUf.mami.entity.Utilisateur;

public interface UtilisateurRepo extends JpaRepository<Utilisateur, Long>{

	Optional<Utilisateur> findByPseudo(String pseudo);

}
