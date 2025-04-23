package org.example.game.impl;

import org.example.game.Jeu;
import org.example.game.Joueur;
import org.example.game.De;
import org.example.game.exception.JeuFermeException;
import org.example.game.exception.DebitImpossibleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JeuTestAvecBanqueReelle {

    @Mock
    private Joueur joueur;

    @Mock
    private De de1;

    @Mock
    private De de2;

    private Jeu jeu;
    private BanqueImpl banque;

    @BeforeEach
    void setUp() {
        // Création d'une banque avec un fond initial de 1000 et un minimum de 500
        banque = new BanqueImpl(1000, 500);
        jeu = new Jeu(banque);
    }

    @Test
    void testPariGagnantAvecBanqueReelle() throws DebitImpossibleException, JeuFermeException {
        // Arrange
        when(joueur.mise()).thenReturn(100);
        when(de1.lancer()).thenReturn(3);
        when(de2.lancer()).thenReturn(4);

        // Act
        jeu.jouer(joueur, de1, de2);

        // Assert
        verify(joueur).mise();
        verify(joueur).debiter(100);
        verify(de1).lancer();
        verify(de2).lancer();
        verify(joueur).crediter(200);
        
        // Vérification de l'état de la banque
        assertEquals(900, banque.getFond()); // 1000 - 100 + 100 - 200
        assertTrue(jeu.estOuvert()); // La banque est encore solvable
    }

    @Test
    void testBanqueInsolvableApresGainAvecBanqueReelle() throws DebitImpossibleException, JeuFermeException {
        // Arrange
        when(joueur.mise()).thenReturn(300);
        when(de1.lancer()).thenReturn(3);
        when(de2.lancer()).thenReturn(4);

        // Act
        jeu.jouer(joueur, de1, de2);

        // Assert
        verify(joueur).mise();
        verify(joueur).debiter(300);
        verify(de1).lancer();
        verify(de2).lancer();
        verify(joueur).crediter(600);
        
        // Vérification de l'état de la banque
        assertEquals(400, banque.getFond()); // 1000 - 300 + 300 - 600
        assertFalse(jeu.estOuvert()); // La banque n'est plus solvable
    }
} 