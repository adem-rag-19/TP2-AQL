package org.example.game;

import org.example.game.exception.JeuFermeException;
import org.example.game.exception.DebitImpossibleException;

public class Jeu {
    private final Banque banque;
    private boolean ouvert;

    public Jeu(Banque banque) {
        this.banque = banque;
        this.ouvert = true;
    }

    public void jouer(Joueur joueur, De de1, De de2) throws JeuFermeException {
        if (!estOuvert()) {
            throw new JeuFermeException("Le jeu est fermé");
        }

        try {
            int mise = joueur.mise();
            joueur.debiter(mise);
            banque.crediter(mise);

            int resultat1 = de1.lancer();
            int resultat2 = de2.lancer();
            int somme = resultat1 + resultat2;

            if (somme == 7) {
                int gain = mise * 2;
                joueur.crediter(gain);
                banque.debiter(gain);
                
                if (!banque.est_solvable()) {
                    fermer();
                }
            }
        } catch (DebitImpossibleException e) {
            // Le joueur est insolvable, le jeu continue
        }
    }

    public void fermer() {
        this.ouvert = false;
    }

    public boolean estOuvert() {
        return ouvert;
    }
} 