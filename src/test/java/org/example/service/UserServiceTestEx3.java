package org.example.service;

import org.example.api.UtilisateurApi;
import org.example.model.Utilisateur;
import org.example.exception.ServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTestEx3 {

    @Mock
    private UtilisateurApi utilisateurApiMock;

    @Test
    public void testCreerUtilisateurAvecException() throws ServiceException {
        // Création d'un nouvel utilisateur
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "jeandupont@email.com");

        // Configuration du mock pour lever une exception
        doThrow(new ServiceException("Echec de la création de l'utilisateur"))
            .when(utilisateurApiMock)
            .creerUtilisateur(utilisateur);

        // Création du service avec le mock
        UserService userService = new UserService(utilisateurApiMock);

        // Test de l'exception
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            userService.creerUtilisateur(utilisateur);
        });

        assertEquals("Echec de la création de l'utilisateur", exception.getMessage());
    }

    @Test
    public void testCreerUtilisateurAvecErreurValidation() throws ServiceException {
        // Création d'un nouvel utilisateur invalide
        Utilisateur utilisateur = new Utilisateur("", "", "");

        // Création du service avec le mock
        UserService userService = new UserService(utilisateurApiMock);

        // Vérification que la méthode n'est jamais appelée
        verify(utilisateurApiMock, never()).creerUtilisateur(any(Utilisateur.class));
    }

    @Test
    public void testCreerUtilisateurAvecId() throws ServiceException {
        // Création d'un nouvel utilisateur
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "jeandupont@email.com");

        // Configuration du mock pour ne rien faire (comportement par défaut pour void)
        doNothing().when(utilisateurApiMock).creerUtilisateur(utilisateur);

        // Création du service avec le mock
        UserService userService = new UserService(utilisateurApiMock);

        // Appel de la méthode à tester
        userService.creerUtilisateur(utilisateur);

        // Vérification que la méthode a été appelée
        verify(utilisateurApiMock).creerUtilisateur(utilisateur);
    }

    @Test
    public void testCreerUtilisateurAvecArgumentCaptor() throws ServiceException {
        // Création d'un nouvel utilisateur
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "jeandupont@email.com");

        // Création du capteur d'arguments
        ArgumentCaptor<Utilisateur> argumentCaptor = ArgumentCaptor.forClass(Utilisateur.class);

        // Configuration du mock pour ne rien faire
        doNothing().when(utilisateurApiMock).creerUtilisateur(any(Utilisateur.class));

        // Création du service avec le mock
        UserService userService = new UserService(utilisateurApiMock);

        // Appel de la méthode à tester
        userService.creerUtilisateur(utilisateur);

        // Capture des arguments
        verify(utilisateurApiMock).creerUtilisateur(argumentCaptor.capture());
        Utilisateur utilisateurCapture = argumentCaptor.getValue();

        // Vérification des arguments capturés
        assertEquals("Jean", utilisateurCapture.getNom());
        assertEquals("Dupont", utilisateurCapture.getPrenom());
        assertEquals("jeandupont@email.com", utilisateurCapture.getEmail());
    }
} 