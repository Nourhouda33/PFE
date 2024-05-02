package com.Pfe.ProjetCollaboratif.Repository;

import com.Pfe.ProjetCollaboratif.Entity.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe , Long> {
}
