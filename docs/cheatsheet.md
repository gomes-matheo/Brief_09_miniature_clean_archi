# Brief6 Cheatsheet (Miniature)

## 1) Demarrage rapide

### Prerequis
- Java 25 (toolchain configuree dans Gradle)
- Gradle Wrapper du projet (`gradlew.bat`)

### Lancer l'app (Windows)
```bash
.\gradlew.bat app:run
```

### URL utiles
- Login: http://localhost:8080/login
- Register: http://localhost:8080/register
- Feed: http://localhost:8080/feed

### Commandes utiles (Windows)
```bash
.\gradlew.bat app:classes
.\gradlew.bat app:test
.\gradlew.bat app:build
```

## 2) Architecture express
- Stack: Java Servlet + JSP + Tomcat embarque
- Point d'entree: `org.example.App`
- Controllers: `org.example.controller.*`
- Vues JSP: `app/src/main/webapp/WEB-INF/views`
- Styles: `app/src/main/webapp/css/style.css`
- Stockage: `org.example.util.DataStore` (in-memory)

## 3) Flux utilisateur
1. Inscription via `/register`
2. Connexion via `/login`
3. Session HTTP avec attribut `loggedUser`
4. Publication via `/post`
5. Interaction: likes `/like`, details/commentaires `/post-detail`
6. Deconnexion via `/logout`

## 4) Endpoints HTTP

### `GET /login`
- Affiche la page de connexion.

### `POST /login`
- Params: `username`, `password`
- Erreurs:
  - `?error=missing` si champs manquants
  - `?error=invalid` si identifiants faux
- Succes: session `loggedUser`, redirect `/feed`

### `GET /register`
- Affiche la page d'inscription.

### `POST /register`
- Params: `username`, `email`, `password`
- Erreurs:
  - `?error=missing`
  - `?error=exists` (username ou email deja pris)
- Succes: redirect `/login?success=registered`

### `GET /feed`
- Auth requise (sinon redirect `/login`)
- Query param optionnel: `mode`
  - `reco` (defaut): tous les posts non brouillon
  - `abonnements`: posts des comptes suivis
- Forward vers `feed.jsp` avec `posts`, `mode`, `loggedUser`

### `POST /post`
- Auth requise
- Param: `content`
- Si vide: redirect `/feed?error=empty`
- Sinon: creation du post puis redirect `/feed`

### `GET /post-detail?id={postId}`
- Auth requise
- Charge un post + ses commentaires
- Forward vers `post_detail.jsp`

### `POST /post-detail`
- Auth requise
- Params: `postId`, `content` (fallback: `comment`)
- Cree un commentaire, puis redirect `/feed`

### `POST /like`
- Auth requise
- Param: `postId`
- Toggle like user/post
- Redirect vers `Referer` (sinon `/feed`)

### `GET /logout`
- Invalide la session
- Redirect `/login`

## 5) Session et auth
- Cle de session: `loggedUser`
- Type stocke: `org.example.model.User`
- Verification auth repetee dans les servlets protegees (`feed`, `post`, `post-detail`, `like`)

## 6) DataStore en 30 secondes
- Singleton: `DataStore.getInstance()`
- Collections thread-safe:
  - `CopyOnWriteArrayList` pour users/posts/comments
  - `Collections.synchronizedSet` pour likes/follows
- Sequence IDs: `AtomicLong` (`userIdSeq`, `postIdSeq`, `commentIdSeq`)
- Like stocke comme cle string: `userId:postId`
- Follow stocke comme cle string: `followerId:followingId`

## 7) Modeles
- `User`: `id`, `username`, `email`, `password`, `createdAt`
- `Post`: `id`, `owner`, `content`, `createdAt`, `isDraft`, `likeCount`, `parent`
- `Comment`: `id`, `postId`, `owner`, `content`, `createdAt`
- `Attachment`: modele present, mais non branche au flux principal de creation de post

## 8) Points d'attention (important)
- Donnees en memoire uniquement: perte totale au restart.
- Mots de passe en clair (pas de hash).
- Pas de vraie persistence DB.
- Certaines conversions numeriques (`Long.parseLong`) ne sont pas toujours protegees.
- Le formulaire feed expose `attachType`/`attachValue`, mais `PostServlet` ne les traite pas.
- Le mode `abonnements` depend de `follows`, mais aucun endpoint de follow n'est expose ici.

## 9) Debug rapide
1. Verifier que `app:run` tourne et ecoute sur `:8080`.
2. Si redirect en boucle vers login: verifier presence de `loggedUser` en session.
3. Si liste vide sur feed: verifier que des posts existent et que `isDraft=false`.
4. Si commentaires introuvables: verifier `postId` envoye par le formulaire.
5. Si likes incoherents: verifier la cle `userId:postId` dans le set `likes`.

## 10) Mini check-list dev
- [ ] Endpoint protege? verifier session avant logique metier
- [ ] Validation des params request
- [ ] Redirect explicite sur erreurs
- [ ] Mise a jour de la JSP correspondante
- [ ] Test manuel login -> post -> like -> comment -> logout
