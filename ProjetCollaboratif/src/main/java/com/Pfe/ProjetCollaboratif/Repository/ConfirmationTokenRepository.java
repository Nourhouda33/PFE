package com.Pfe.ProjetCollaboratif.Repository;

import com.Pfe.ProjetCollaboratif.Entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Long> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
