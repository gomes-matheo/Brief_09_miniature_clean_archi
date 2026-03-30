<%@ page contentType="text/html;charset=UTF-8" %>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
        <!DOCTYPE html>
        <html lang="fr">

        <head>
            <meta charset="UTF-8">
            <title>Miniature — Fil d'actualité</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        </head>

        <body>

            <nav class="navbar">
                <span class="logo">Miniature</span>
                <section class="nav-links">
                    <a href="${pageContext.request.contextPath}/feed?mode=reco"
                        class="${mode == 'reco' ? 'active' : ''}">Recommandations</a>
                    <a href="${pageContext.request.contextPath}/feed?mode=abonnements"
                        class="${mode == 'abonnements' ? 'active' : ''}">Abonnements</a>
                </section>
                <section class="nav-user">
                    <span>${loggedUser.username}</span>
                    <a href="${pageContext.request.contextPath}/logout">Déconnexion</a>
                </section>
            </nav>

            <main class="container">

                <%-- Formulaire de création de post --%>
                    <section class="post-form-card">
                        <form method="post" action="${pageContext.request.contextPath}/post">
                            <textarea name="content" placeholder="Quoi de neuf ?" rows="3" required></textarea>

                            <%-- Bonus : pièce jointe --%>
                                <section class="attach-row">
                                    <select name="attachType">
                                        <option value="">Aucune pièce jointe</option>
                                        <option value="link">Lien Web</option>
                                        <option value="image">Image (URL S3)</option>
                                        <option value="video">Vidéo (YouTube URL)</option>
                                        <option value="document">Document PDF</option>
                                    </select>
                                    <input type="text" name="attachValue" placeholder="URL...">
                                </section>

                                <button type="submit" class="btn-primary">Publier</button>
                        </form>
                    </section>

                    <%-- Liste des posts --%>
                        <c:choose>
                            <c:when test="${empty posts}">
                                <p class="empty-feed">Aucun post à afficher.</p>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="post" items="${posts}">
                                    <article class="post-card">
                                        <header class="post-header">
                                            <strong>@${post.owner.username}</strong>
                                            <span class="post-date">${post.createdAt}</span>
                                        </header>
                                        <p class="post-content">${post.content}</p>
                                        <footer class="post-actions">
                                            <form method="post" action="${pageContext.request.contextPath}/like">
                                                <input type="hidden" name="postId" value="${post.id}">
                                                <button type="submit" class="btn-like">❤ ${post.likeCount}</button>
                                            </form>
                                            <a href="${pageContext.request.contextPath}/post-detail?id=${post.id}"
                                                class="btn-comment">💬 Commenter</a>
                                        </footer>
                                    </article>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>

            </main>
        </body>

        </html>