# TP2-AQL - Tests Unitaires avec JUnit et Mockito

Ce projet contient quatre exercices de tests unitaires utilisant JUnit 5 et Mockito.

## Exercice 1 : Tests de la Calculatrice

L'exercice 1 se concentre sur les tests unitaires de base avec JUnit et Mockito. Il implémente une classe `Calculatrice` simple avec une méthode d'addition et son test correspondant.

### Structure
- `Calculatrice.java` : Classe principale avec la méthode `additionner`
- `CalculatriceTest.java` : Tests unitaires utilisant Mockito

### Objectifs
- Apprendre à créer des tests unitaires de base
- Utiliser les assertions JUnit
- Comprendre le fonctionnement des mocks avec Mockito

## Exercice 2 : Tests du Service Utilisateur

L'exercice 2 introduit les tests de services avec des dépendances externes. Il implémente un service de gestion d'utilisateurs qui interagit avec une API externe.

### Structure
- `UserService.java` : Service de gestion des utilisateurs
- `Utilisateur.java` : Modèle de données utilisateur
- `UtilisateurApi.java` : Interface pour l'API externe
- `UserServiceTest.java` : Tests unitaires du service

### Objectifs
- Tester un service avec des dépendances externes
- Utiliser les mocks pour simuler les dépendances
- Vérifier les interactions avec les dépendances

## Exercice 3 : Tests Avancés du Service Utilisateur

L'exercice 3 approfondit les tests du service utilisateur avec des cas plus complexes.

### Structure
- `UserServiceTestEx3.java` : Tests avancés incluant :
  - Tests d'exceptions
  - Tests de validation
  - Utilisation d'ArgumentCaptor
  - Vérification des appels de méthodes

### Objectifs
- Tester les cas d'erreur et les exceptions
- Utiliser ArgumentCaptor pour vérifier les arguments passés
- Tester la validation des données
- Vérifier les interactions complexes avec les mocks

## Exercice 4 : Jeu de Dés

L'exercice 4 implémente un jeu de casino en ligne appelé "jeu du 7" qui utilise deux dés et une banque pour gérer les paris et les gains.

### Structure
- `Jeu.java` : Classe principale du jeu
- `De.java` : Interface pour les dés
- `Joueur.java` : Interface pour le joueur
- `Banque.java` : Interface pour la banque
- `JeuFermeException.java` : Exception pour les cas où le jeu est fermé
- `DebitImpossibleException.java` : Exception pour les cas où le joueur est insolvable

### Règles du Jeu
- Le jeu se joue avec 2 dés
- Le joueur propose une mise
- Si la somme des dés vaut 7, le joueur gagne 2 fois sa mise
- Si la somme n'est pas 7, le joueur perd sa mise
- Le jeu peut fermer si la banque n'est plus solvable

### Scénarios de Test
1. **Jeu fermé**
   - Test d'état : vérifie que le jeu lance une exception quand il est fermé
   - Vérifie qu'aucune interaction n'a lieu avec les dés ou la banque

2. **Joueur insolvable**
   - Test d'interaction : vérifie que le jeu ne touche pas aux dés
   - Vérifie que le joueur n'est pas débité
   - Vérifie que la banque n'est pas impliquée

3. **Pari perdant**
   - Test d'interaction : vérifie que le joueur est débité
   - Vérifie que la banque encaisse la mise
   - Vérifie que les dés sont lancés

4. **Pari gagnant**
   - Test d'interaction : vérifie que le joueur est crédité du double de sa mise
   - Vérifie que la banque est débitée
   - Vérifie que les dés sont lancés

5. **Banque insolvable après gain**
   - Test d'état et d'interaction : vérifie que le joueur est crédité
   - Vérifie que le jeu se ferme
   - Vérifie que la banque est débitée

### Objets à Mocker
Les objets suivants doivent être mockés pour tester la méthode `jouer` :
1. `Banque` : Pour simuler les opérations de crédit/débit et vérifier la solvabilité
2. `Joueur` : Pour simuler les mises et les opérations de crédit/débit
3. `De` (x2) : Pour simuler les lancers de dés de manière déterministe

Ces objets sont mockés car :
- Ils représentent des dépendances externes
- Leur comportement doit être contrôlé pour les tests
- Ils peuvent avoir des effets de bord non désirés
- Ils permettent de tester le jeu de manière isolée

### Différence entre Tests avec Mock et Tests avec Implémentation
Les tests avec mock permettent de :
- Tester le jeu de manière isolée
- Vérifier les interactions exactes
- Simuler des cas d'erreur facilement
- Avoir des tests plus rapides et plus stables

Les tests avec l'implémentation réelle permettent de :
- Tester l'intégration réelle des composants
- Détecter des problèmes d'implémentation
- Vérifier le comportement global du système
- Tester des cas plus réalistes

## Configuration du Projet

Le projet utilise :
- Java 8
- Maven
- JUnit 5
- Mockito 4.2.0

Pour exécuter les tests :
```bash
mvn test
```

## Structure des Packages

```
src/
├── main/
│   └── java/
│       └── org/
│           └── example/
│               ├── Calculatrice.java
│               ├── Main.java
│               ├── api/
│               │   └── UtilisateurApi.java
│               ├── exception/
│               │   └── ServiceException.java
│               ├── model/
│               │   └── Utilisateur.java
│               └── service/
│                   └── UserService.java
└── test/
    └── java/
        └── org/
            └── example/
                ├── CalculatriceTest.java
                └── service/
                    ├── UserServiceTest.java
                    └── UserServiceTestEx3.java
``` 