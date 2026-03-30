<%@ page contentType="text/html;charset=UTF-8" %>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
        <!DOCTYPE html>
        <html lang="fr">

        <head>
            <meta charset="UTF-8">
            <title>Miniature — Post de @${post.owner.username}</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        </head>

        <body>

            <nav class="navbar">
                <span class="logo">Miniature</span>
                <section class="nav-links">
                    <a href="${pageContext.request.contextPath}/feed">← Retour au fil</a>
                </section>
                <section class="nav-user">
                    <span>${loggedUser.username}</span>
                    <a href="${pageContext.request.contextPath}/logout">Déconnexion</a>
                </section>
            </nav>

            <main class="container">

                <%-- Post principal --%>
                    <article class="post-card post-detail-card">
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
                        </footer>
                    </article>

                    <%-- Formulaire de commentaire --%>
                        <section class="comment-form-card">
                            <h3>Ajouter un commentaire</h3>
                            <form method="post" action="${pageContext.request.contextPath}/post-detail">
                                <input type="hidden" name="postId" value="${post.id}">
                                <textarea name="content" placeholder="Votre commentaire..." rows="2"
                                    required></textarea>
                                <button type="submit" class="btn-primary">Commenter</button>
                            </form>
                        </section>

                        <%-- Liste des commentaires --%>
                            <section class="comments-section">
                                <h3>${comments.size()} commentaire(s)</h3>
                                <c:choose>
                                    <c:when test="${empty comments}">
                                        <p class="empty-feed">Soyez le premier à commenter !</p>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach var="comment" items="${comments}">
                                            <article class="comment-card">
                                                <header class="post-header">
                                                    <strong>@${comment.owner.username}</strong>
                                                    <span class="post-date">${comment.createdAt}</span>
                                                </header>
                                                <p>${comment.content}</p>
                                            </article>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                            </section>

            </main>
        </body>

        </html>