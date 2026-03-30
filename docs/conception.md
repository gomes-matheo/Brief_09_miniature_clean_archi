# Documentation de conception

## 1. Objectif

Miniature est une application web Java de type reseau social simplifie. Elle permet:

- inscription et connexion
- publication de posts
- consultation d'un fil d'actualite
- likes
- commentaires

## 2. Choix d'architecture

L'application suit une architecture MVC legere basee sur les servlets:

- Modele: classes metier (`User`, `Post`, `Comment`, `Attachment`)
- Controleur: servlets du package `controller`
- Vue: pages JSP sous `WEB-INF/views`

Le serveur web est Tomcat embarque, demarre par la classe `App`.

## 3. Composants

### 3.1 App (bootstrap)

Responsabilites:

- creation et configuration de Tomcat embarque
- declaration des mappings des servlets
- exposition de l'application sur le port 8080

### 3.2 Controleurs (servlets)

- `LoginServlet`: affichage formulaire + authentification
- `RegisterServlet`: creation de compte
- `LogoutServlet`: invalidation de session
- `FeedServlet`: affichage du fil (mode recommandations/abonnements)
- `PostServlet`: creation de post
- `PostDetailServlet`: affichage d'un post et de ses commentaires
- `LikeServlet`: ajout/suppression d'un like

### 3.3 Modele metier

- `User`: identite et credentials
- `Post`: contenu publie, proprietaire, date, compteur de likes
- `Comment`: commentaire associe a un post
- `Attachment`: structure prevue pour pieces jointes

### 3.4 Persistance

La persistance est geree par `DataStore`:

- singleton en memoire
- collections thread-safe (`CopyOnWriteArrayList`, `Set` synchronise)
- generateurs d'identifiants via `AtomicLong`

Consequence:

- pas de base de donnees
- pas de SQL
- donnees ephemeres (reset au redemarrage)

## 4. Flux fonctionnels

### 4.1 Authentification

1. L'utilisateur ouvre `/login`.
2. Le controleur verifie username/password dans `DataStore`.
3. En succes, `loggedUser` est stocke en session.
4. Redirection vers `/feed`.

### 4.2 Publication

1. Utilisateur connecte soumet le formulaire `/post`.
2. Le contenu est valide (non vide).
3. `DataStore.createPost(...)` cree le post.
4. Redirection vers `/feed`.

### 4.3 Likes

1. Soumission POST sur `/like` avec `postId`.
2. `toggleLike(userId, postId)` ajoute ou retire le like.
3. Redirection vers la page precedente.

### 4.4 Commentaires

1. Consultation via `/post-detail?id=...`.
2. Le controleur charge post + liste des commentaires.
3. Le formulaire de commentaire poste ensuite vers `/comment` (a aligner avec un servlet dedie si besoin).

## 5. Session et securite

- acces au fil et actions protegees par verification `loggedUser`
- redirection vers `/login` si session absente
- deconnexion par invalidation de session

Limites actuelles:

- mots de passe en clair (pas de hash)
- validation serveur basique
- pas de protection CSRF

## 6. Qualite et evolutions recommandees

- ajouter hash de mot de passe (BCrypt)
- remplacer `DataStore` par une vraie couche repository
- ajouter tests des servlets et tests d'integration
- harmoniser le flux de commentaire (`/comment`)
- ajouter gestion des erreurs utilisateur plus detaillee
