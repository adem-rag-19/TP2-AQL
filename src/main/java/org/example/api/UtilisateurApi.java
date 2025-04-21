package org.example.api;

import org.example.model.Utilisateur;
import org.example.exception.ServiceException;

public interface UtilisateurApi {
    void creerUtilisateur(Utilisateur utilisateur) throws ServiceException;
} 