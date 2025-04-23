package org.example.game;

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
public class JeuTest {

    @Mock
    private Banque banque;

    @Mock
    private Joueur joueur;

    @Mock
    private De de1;

    @Mock
    private De de2;

    private Jeu jeu;

    @BeforeEach
    void setUp() {
        jeu = new Jeu(banque);
    }

    @Test
    void testJeuFerme() {
        // Arrange
        jeu.fermer();

        // Act & Assert
        assertThrows(JeuFermeException.class, () -> {
            jeu.jouer(joueur, de1, de2);
        });

        // Vérification qu'aucune interaction n'a eu lieu
        verifyNoInteractions(joueur, de1, de2, banque);
    }

    @Test
    void testJoueurInsolvable() throws DebitImpossibleException {
        // Arrange
        when(joueur.mise()).thenReturn(100);
        doThrow(new DebitImpossibleException("Solde insuffisant")).when(joueur).debiter(100);

        // Act
        assertDoesNotThrow(() -> jeu.jouer(joueur, de1, de2));

        // Assert
        verify(joueur).mise();
        verify(joueur).debiter(100);
        verifyNoMoreInteractions(joueur);
        verifyNoInteractions(de1, de2, banque);
    }

    @Test
    void testPariPerdant() throws DebitImpossibleException, JeuFermeException {
        // Arrange
        when(joueur.mise()).thenReturn(100);
        when(de1.lancer()).thenReturn(2);
        when(de2.lancer()).thenReturn(3);

        // Act
        jeu.jouer(joueur, de1, de2);

        // Assert
        verify(joueur).mise();
        verify(joueur).debiter(100);
        verify(banque).crediter(100);
        verify(de1).lancer();
        verify(de2).lancer();
        verifyNoMoreInteractions(joueur, banque);
    }

    @Test
    void testPariGagnant() throws DebitImpossibleException, JeuFermeException {
        // Arrange
        when(joueur.mise()).thenReturn(100);
        when(de1.lancer()).thenReturn(3);
        when(de2.lancer()).thenReturn(4);
        when(banque.est_solvable()).thenReturn(true);

        // Act
        jeu.jouer(joueur, de1, de2);

        // Assert
        verify(joueur).mise();
        verify(joueur).debiter(100);
        verify(banque).crediter(100);
        verify(de1).lancer();
        verify(de2).lancer();
        verify(joueur).crediter(200);
        verify(banque).debiter(200);
        verify(banque).est_solvable();
    }

    @Test
    void testBanqueInsolvableApresGain() throws DebitImpossibleException, JeuFermeException {
        // Arrange
        when(joueur.mise()).thenReturn(100);
        when(de1.lancer()).thenReturn(3);
        when(de2.lancer()).thenReturn(4);
        when(banque.est_solvable()).thenReturn(false);

        // Act
        jeu.jouer(joueur, de1, de2);

        // Assert
        verify(joueur).mise();
        verify(joueur).debiter(100);
        verify(banque).crediter(100);
        verify(de1).lancer();
        verify(de2).lancer();
        verify(joueur).crediter(200);
        verify(banque).debiter(200);
        verify(banque).est_solvable();
        assertFalse(jeu.estOuvert());
    }
} 