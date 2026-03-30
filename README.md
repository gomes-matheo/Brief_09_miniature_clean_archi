# Brief6 - Miniature

Application web Java (Servlet/JSP) de micro-fil d'actualite, executee sur Tomcat embarque.

## Prerequis

- Java 25 (le projet utilise un toolchain Java 25 dans Gradle)
- Windows, macOS ou Linux

## Installation

1. Cloner le projet.
2. Ouvrir un terminal a la racine du repository.

## Lancer l'application

### Windows

```bash
.\gradlew.bat app:run
```
Une fois lancee, l'application est disponible sur:

- http://localhost:8080/login

## Utilisation rapide

1. Ouvrir http://localhost:8080/register
2. Creer un compte
3. Se connecter via http://localhost:8080/login
4. Publier un post sur le fil
5. Ouvrir un post pour voir/commenter
6. Liker un post

## Arreter l'application

Dans le terminal d'execution: `Ctrl + C`

### Compiler le projet

```bash
.\gradlew.bat app:classes
```

### Executer les tests

```bash
.\gradlew.bat app:test
```

### Build complet

```bash
.\gradlew.bat app:build
```

## Remarques importantes

- Le stockage des donnees est en memoire (`DataStore`).
- Les donnees sont perdues a chaque redemarrage de l'application.

## Structure principale

- `app/src/main/java/org/example/App.java` : point d'entree, boot Tomcat embarque
- `app/src/main/java/org/example/controller/` : servlets HTTP
- `app/src/main/java/org/example/model/` : modeles metier
- `app/src/main/java/org/example/util/DataStore.java` : stockage en memoire
- `app/src/main/webapp/WEB-INF/views/` : pages JSP
- `app/src/main/webapp/css/style.css` : styles
