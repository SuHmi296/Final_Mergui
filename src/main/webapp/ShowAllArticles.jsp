<%@ page import="java.util.ArrayList" %>
<%@ page import="article.ArticleClass" %>

<html>
<head>
    <title>All Articles</title>
</head>
<body>
    <h1>All Articles</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Content</th>
            <th>Category</th>
            <th>Created At</th>
        </tr>
        <%
            ArrayList<ArticleClass> articles = (ArrayList<ArticleClass>) request.getAttribute("articles");
            if (articles != null && !articles.isEmpty()) {
                for (ArticleClass article : articles) {
        %>
        <tr>
            <td><%= article.getId() %></td>
            <td><%= article.getTitle() %></td>
            <td><%= article.getContent() %></td>
            <td><%= article.getCategory() %></td>
            <td><%= article.getCreatedAt() %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5">No articles found.</td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
