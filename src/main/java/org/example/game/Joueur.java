package org.example.game;

import org.example.game.exception.DebitImpossibleException;

public interface Joueur {
    int mise(); // on suppose que mise positive
    void debiter(int somme) throws DebitImpossibleException;
    void crediter(int somme);
} 