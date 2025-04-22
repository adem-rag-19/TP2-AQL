# TP2-AQL - Tests Unitaires avec JUnit et Mockito

Ce projet contient trois exercices de tests unitaires utilisant JUnit 5 et Mockito.

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