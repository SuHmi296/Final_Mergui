package article;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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
                request.getRequestDispatcher("articles.jsp").forward(request, response);
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
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/mergui";
            String dbUser = "root";
            String dbPass = "root";

            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
            conn.setAutoCommit(false); // Start transaction

            // Insert article
            String sqlArticle = "INSERT INTO articles (title, content, category) VALUES (?, ?, ?)";
            PreparedStatement articleStatement = conn.prepareStatement(sqlArticle, Statement.RETURN_GENERATED_KEYS);
            articleStatement.setString(1, title);
            articleStatement.setString(2, content);
            articleStatement.setString(3, category);

            int articleRow = articleStatement.executeUpdate();
            int articleId = 0;
            if (articleRow > 0) {
                ResultSet generatedKeys = articleStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    articleId = generatedKeys.getInt(1);
                }
            }

            // Insert image
            Part filePart = request.getPart("image");
            if (filePart != null && articleId > 0) {
                String sqlImage = "INSERT INTO images (parent_id, image_data) VALUES (?, ?)";
                PreparedStatement imageStatement = conn.prepareStatement(sqlImage);
                imageStatement.setInt(1, articleId);
                imageStatement.setBlob(2, filePart.getInputStream());
                imageStatement.executeUpdate();
            }

            conn.commit();
            message = "Article and image uploaded successfully!";
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
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/mergui";
            String dbUser = "root";
            String dbPass = "root";

            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
            String sql = "SELECT a.*, i.image_data FROM articles a LEFT JOIN images i ON a.id = i.parent_id";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                ArticleClass article = new ArticleClass();
                article.setId(resultSet.getInt("id"));
                article.setTitle(resultSet.getString("title"));
                article.setContent(resultSet.getString("content"));
                article.setCategory(resultSet.getString("category"));
                article.setCreatedAt(resultSet.getTimestamp("created_at"));
//                article.setImage(resultSet.getBlob("image_data"));
                byte[] imageBytes = resultSet.getBytes("image_data");
                String image;
                if (imageBytes != null && imageBytes.length > 0) {
                    image = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytes);
                } else {
                    image = "./Images/backgroundimg.jpg"; // Default image URL
                }
                System.out.println("Image =>"+image);
                article.setImage(image);
                articles.add(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articles;
    }

    private ArticleClass viewArticle(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        ArticleClass article = new ArticleClass();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/mergui";
            String dbUser = "root";
            String dbPass = "root";

            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
            String sql = "SELECT a.*, i.image_data FROM articles a LEFT JOIN images i ON a.id = i.parent_id WHERE a.id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                article.setId(resultSet.getInt("id"));
                article.setTitle(resultSet.getString("title"));
                article.setContent(resultSet.getString("content"));
                article.setCategory(resultSet.getString("category"));
                article.setCreatedAt(resultSet.getTimestamp("created_at"));
//                article.setImage(resultSet.getBlob("image_data"));
                byte[] imageBytes = resultSet.getBytes("image_data");
                String image;
                if (imageBytes != null && imageBytes.length > 0) {
                    image = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytes);
                } else {
                    image = "./Images/backgroundimg.jpg"; // Default image URL
                }
                System.out.println("Image =>"+image);
                
                article.setImage(image);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return article;
    }
}
