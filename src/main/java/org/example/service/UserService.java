package org.example.service;

import org.example.api.UtilisateurApi;
import org.example.model.Utilisateur;
import org.example.exception.ServiceException;

public class UserService {
    private final UtilisateurApi utilisateurApi;

    public UserService(UtilisateurApi utilisateurApi) {
        this.utilisateurApi = utilisateurApi;
    }

    public void creerUtilisateur(Utilisateur utilisateur) throws ServiceException {
        utilisateurApi.creerUtilisateur(utilisateur);
    }
} 