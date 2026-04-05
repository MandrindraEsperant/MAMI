package mg.univUf.mami.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mg.univUf.mami.entity.Parent;
import mg.univUf.mami.repository.ParentRepo;

@Service
public class ParentService {

    @Autowired
    private ParentRepo repo;

    // 🔹 AJOUT
    public Parent save(Parent parent) {
        return repo.save(parent);
    }

    // 🔹 SUPPRESSION
    public void delete(Long id) {

        Parent parent = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Parent introuvable"));

        repo.delete(parent);
    }

    // 🔹 LIRE PAR ID
    public Parent getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Parent introuvable"));
    }

    // 🔹 LIRE TOUS
    public List<Parent> getAll() {
        return repo.findAll();
    }
}