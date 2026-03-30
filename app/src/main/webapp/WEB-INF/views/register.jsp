<%@ page contentType="text/html;charset=UTF-8" %>
    <!DOCTYPE html>
    <html lang="fr">

    <head>
        <meta charset="UTF-8">
        <title>Miniature — Inscription</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    </head>

    <body class="auth-page">

        <main class="auth-card">
            <h1 class="logo">Miniature</h1>
            <h2>Créer un compte</h2>

            <% if ("missing".equals(request.getParameter("error"))) { %>
                <p class="error">Tous les champs sont obligatoires.</p>
                <% } else if ("exists".equals(request.getParameter("error"))) { %>
                    <p class="error">Ce nom d'utilisateur ou cet email est déjà utilisé.</p>
                    <% } %>

                        <form method="post" action="${pageContext.request.contextPath}/register">
                            <input type="text" name="username" placeholder="Nom d'utilisateur" required>
                            <input type="email" name="email" placeholder="Email" required>
                            <input type="password" name="password" placeholder="Mot de passe" required>
                            <button type="submit">S'inscrire</button>
                        </form>

                        <p>Déjà un compte ? <a href="${pageContext.request.contextPath}/login">Se connecter</a></p>
        </main>

    </body>

    </html>