package org.example.game;

public interface Banque {
    void crediter(int somme);
    boolean est_solvable();
    void debiter(int somme);
} 