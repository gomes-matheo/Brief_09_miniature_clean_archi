<%@ page contentType="text/html;charset=UTF-8" %>
    <!DOCTYPE html>
    <html lang="fr">

    <head>
        <meta charset="UTF-8">
        <title>Miniature — Connexion</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    </head>

    <body class="auth-page">

        <main class="auth-card">
            <h1 class="logo">Miniature</h1>
            <h2>Connexion</h2>

            <% if ("invalid".equals(request.getParameter("error"))) { %>
                <p class="error">Identifiants incorrects.</p>
                <% } else if ("missing".equals(request.getParameter("error"))) { %>
                    <p class="error">Tous les champs sont obligatoires.</p>
                    <% } %>
                        <% if ("registered".equals(request.getParameter("success"))) { %>
                            <p class="success">Compte créé avec succès ! Connectez-vous.</p>
                            <% } %>

                                <form method="post" action="${pageContext.request.contextPath}/login">
                                    <input type="text" name="username" placeholder="Nom d'utilisateur" required>
                                    <input type="password" name="password" placeholder="Mot de passe" required>
                                    <button type="submit">Se connecter</button>
                                </form>

                                <p>Pas encore de compte ? <a
                                        href="${pageContext.request.contextPath}/register">S'inscrire</a></p>
        </main>

    </body>

    </html>