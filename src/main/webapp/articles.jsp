<%@page import="article.ArticleClass"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="article.ArticleClass" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Articles</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">

<div class="container mx-auto p-6">
    <div class="flex flex-wrap -m-4">
    <div>Articles</div>
        <%
            List<ArticleClass> articles = (List<ArticleClass>) request.getAttribute("articles");
            if (articles != null) {
                for (ArticleClass article : articles) {
        %>
            <div>a</div>
        
        <a href="#" class="w-full sm:w-1/2 lg:w-1/3 p-4 group">
            <div class="bg-transparent rounded-lg overflow-hidden">
                <div class="w-[360px] h-52 flex justify-center items-center rounded-xl">
                    <div class="w-full h-full rounded-lg transition-all overflow-hidden duration-300 group-hover:scale-95">
                        <img class="w-full h-full rounded-lg object-cover transition-all duration-300 group-hover:scale-125"
                             src="<%= article.getImage() %>" alt="<%= article.getTitle() %>" />
                    </div>
                </div>
                <div class="p-4">
                    <h2 class="text-xl font-bold mb-2"><%= article.getTitle() %></h2>
                    <p class="text-gray-600 mb-4"><%= article.getContent() %></p>
                    <a href="#" class="text-red-600 font-bold hover:underline">Read more &rarr;</a>
                </div>
            </div>
        </a>
        <%
                }
            }
        %>
    </div>
</div>

</body>
</html>
