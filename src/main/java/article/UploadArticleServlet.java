package article;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/UploadArticleServlet")
@MultipartConfig(maxFileSize = 16177215) // 16MB
public class UploadArticleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("articleTitle");
        String content = request.getParameter("articleContent");
        String category = request.getParameter("articleCategory");

        InputStream inputStream = null; // Input stream for the image file

        // Obtains the upload file part in this multipart request
        Part filePart = request.getPart("image");
        if (filePart != null) {
            // Prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());

            // Obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }

        String message = null; // Message will be sent back to client

        Connection conn = null;
        try {
            // Connection parameters
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/mergui";
            String dbUser = "root";
            String dbPass = "root";

            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
            conn.setAutoCommit(false); // Enable transaction

            // Insert the article into the articles table
            String sqlArticle = "INSERT INTO articles (title, content, category) values (?, ?, ?)";
            PreparedStatement articleStatement = conn.prepareStatement(sqlArticle, Statement.RETURN_GENERATED_KEYS);
            articleStatement.setString(1, title);
            articleStatement.setString(2, content);
            articleStatement.setString(3, category);

            int articleRow = articleStatement.executeUpdate();

            if (articleRow > 0) {
                // Get the generated article ID (parent_id)
                try (ResultSet generatedKeys = articleStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int parentId = generatedKeys.getInt(1);

                        // Insert the image into the images table with the parent_id
                        if (inputStream != null) {
                            String sqlImage = "INSERT INTO images (parent_id, image_data) values (?, ?)";
                            PreparedStatement imageStatement = conn.prepareStatement(sqlImage);
                            imageStatement.setInt(1, parentId);
                            imageStatement.setBlob(2, inputStream);

                            int imageRow = imageStatement.executeUpdate();
                            if (imageRow > 0) {
                                message = "Article and image uploaded successfully!";
                            } else {
                                message = "Article uploaded, but image upload failed!";
                            }
                        } else {
                            message = "Article uploaded successfully!";
                        }
                    }
                }
            }

            conn.commit(); // Commit transaction
        } catch (Exception e) {
            message = "ERROR: " + e.getMessage();
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback transaction if there is an error
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // Redirects to a success page with a message
        response.sendRedirect("coadmin.jsp?message=" + message);
    }
}
