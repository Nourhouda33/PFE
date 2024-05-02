package com.Pfe.ProjetCollaboratif.Repository;

import com.Pfe.ProjetCollaboratif.Entity.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacheRepository extends JpaRepository<Tache,Long> {
}
