package mg.univUf.mami.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mg.univUf.mami.entity.Parent;

public interface ParentRepo extends JpaRepository<Parent , Long> {

}
