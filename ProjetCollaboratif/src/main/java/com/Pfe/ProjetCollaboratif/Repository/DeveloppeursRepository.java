package com.Pfe.ProjetCollaboratif.Repository;

import com.Pfe.ProjetCollaboratif.Entity.Developpeurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloppeursRepository extends JpaRepository<Developpeurs, Long > {
    boolean existsByEmail(String email);

    Developpeurs findByEmail(String email);
}
