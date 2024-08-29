package article;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/article")
public class article extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String message = "";

        switch (action) {
            case "create":
                message = createArticle(request);
                break;
            case "update":
                message = updateArticle(request);
                break;
            case "delete":
                message = deleteArticle(request);
                break;
            case "viewAll":
                request.setAttribute("articles", viewAllArticles());
                request.getRequestDispatcher("viewAllArticles.jsp").forward(request, response);
                return;
            case "view":
                request.setAttribute("article", viewArticle(request));
                request.getRequestDispatcher("viewArticle.jsp").forward(request, response);
                return;
            default:
                message = "Invalid action!";
                break;
        }

        response.sendRedirect("coadmin.jsp?message=" + message);
    }

    private String createArticle(HttpServletRequest request) {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String category = request.getParameter("category");
        String message;

        try {
            // Connection parameters
            Class.forName("com.mysql.cj.jdbc.Driver");
        String dbURL = "jdbc:mysql://localhost:3306/mergui";
        String dbUser = "root";
        String dbPass = "root";

        Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
            String sql = "INSERT INTO articles (title, content, category, image) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, content);
            statement.setString(3, category);
            statement.setBlob(4, request.getInputStream());

            int row = statement.executeUpdate();
            if (row > 0) {
                message = "Article uploaded successfully!";
            } else {
                message = "Failed to upload article.";
            }
        } catch (Exception e) {
            message = "ERROR: " + e.getMessage();
            e.printStackTrace();
        }
        return message;
    }

    private String updateArticle(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String category = request.getParameter("category");
        String message;

        try {
            // Connection parameters
            Class.forName("com.mysql.cj.jdbc.Driver");
        String dbURL = "jdbc:mysql://localhost:3306/mergui";
        String dbUser = "root";
        String dbPass = "root";

        Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
            String sql = "UPDATE articles SET title=?, content=?, category=? WHERE id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, content);
            statement.setString(3, category);
            statement.setInt(4, id);

            int row = statement.executeUpdate();
            if (row > 0) {
                message = "Article updated successfully!";
            } else {
                message = "Failed to update article.";
            }
        } catch (Exception e) {
            message = "ERROR: " + e.getMessage();
            e.printStackTrace();
        }
        return message;
    }

    private String deleteArticle(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String message;

        try {
            // Connection parameters
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        String dbURL = "jdbc:mysql://localhost:3306/mergui";
	        String dbUser = "root";
	        String dbPass = "root";
	
	        Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
	            String sql = "DELETE FROM articles WHERE id=?";
	            PreparedStatement statement = conn.prepareStatement(sql);
	            statement.setInt(1, id);

            int row = statement.executeUpdate();
            if (row > 0) {
                message = "Article deleted successfully!";
            } else {
                message = "Failed to delete article.";
            }
        } catch (Exception e) {
            message = "ERROR: " + e.getMessage();
            e.printStackTrace();
        }
        return message;
    }
    
    private ArrayList<ArticleClass> viewAllArticles() {
        ArrayList<ArticleClass> articles = new ArrayList<>();
        try {
            // Connection parameters
            Class.forName("com.mysql.cj.jdbc.Driver");
	        String dbURL = "jdbc:mysql://localhost:3306/mergui";
	        String dbUser = "root";
	        String dbPass = "root";
	
	        Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
            String sql = "SELECT * FROM articles";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                ArticleClass article = new ArticleClass();
                article.setId(resultSet.getInt("id"));
                article.setTitle(resultSet.getString("title"));
                article.setContent(resultSet.getString("content"));
                article.setCategory(resultSet.getString("category"));
                article.setCreatedAt(resultSet.getTimestamp("created_at"));
                articles.add(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }
        return articles;
    }



    private ArticleClass viewArticle(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        ArticleClass article = new ArticleClass();

        try {
            // Connection parameters
            Class.forName("com.mysql.cj.jdbc.Driver");
	        String dbURL = "jdbc:mysql://localhost:3306/mergui";
	        String dbUser = "root";
	        String dbPass = "root";
	
	        Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
            String sql = "SELECT * FROM articles WHERE id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                article.setId(resultSet.getInt("id"));
                article.setTitle(resultSet.getString("title"));
                article.setContent(resultSet.getString("content"));
                article.setCategory(resultSet.getString("category"));
                article.setImage(resultSet.getBlob("image"));
                article.setCreatedAt(resultSet.getTimestamp("created_at"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return article;
    }
}
