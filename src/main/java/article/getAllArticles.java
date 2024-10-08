package article;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/getAllArticles")
public class getAllArticles extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public getAllArticles() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<ArticleClass> articles = new ArrayList<>();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/mergui";
            String dbUser = "root";
            String dbPass = "root";

            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
            String sql = "SELECT a.*, i.image_data FROM articles a LEFT JOIN images i ON a.article_id = i.parent_id";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            
            while (rs.next()) {
                ArticleClass article = new ArticleClass();
                article.setId(rs.getInt("article_id"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
                article.setCategory(rs.getString("category"));
                article.setCreatedAt(rs.getTimestamp("created_at"));
                
                // Handling image data as Base64
                byte[] imageBytes = rs.getBytes("image_data");
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
            
            // Convert the articles list to JSON
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonResponse = gson.toJson(articles);
            
            // Send the JSON response
            response.getWriter().write(jsonResponse);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
