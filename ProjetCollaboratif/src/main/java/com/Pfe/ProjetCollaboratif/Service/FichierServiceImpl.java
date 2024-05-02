package com.Pfe.ProjetCollaboratif.Service;

import com.Pfe.ProjetCollaboratif.Entity.Fichier;
import com.Pfe.ProjetCollaboratif.Repository.FichierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class FichierServiceImpl implements FichierService{
    @Autowired
    FichierRepository fichierRepository;
    @Override
    public Fichier ajouterFichier(Fichier fichier) {

        return fichierRepository.save(fichier);
    }

    @Override
    public Fichier modifierFichier(Fichier fichier) {

        return fichierRepository.save(fichier);
    }

    @Override
    public List<Fichier> getFichier() {

        return fichierRepository.findAll();
    }

    @Override
    public void supprimerFichier(Long id) {
        fichierRepository.deleteById(id);

    }

    @Override
    public Optional<Fichier> getFichier(Long id) {
        return fichierRepository.findById(id);
    }
}
