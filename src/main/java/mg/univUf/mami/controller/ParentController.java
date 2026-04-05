package mg.univUf.mami.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mg.univUf.mami.entity.Parent;
import mg.univUf.mami.service.ParentService;

@RestController
@RequestMapping("/api/parents")
public class ParentController {

    @Autowired
    
    private ParentService service;

    // 🔹 AJOUT
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Parent parent) {
        service.save(parent);
        return ResponseEntity.ok("Parent ajouté");
    }

    // 🔹 SUPPRESSION
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Parent supprimé");
    }

    // 🔹 LIRE PAR ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // 🔹 LIRE TOUS
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}