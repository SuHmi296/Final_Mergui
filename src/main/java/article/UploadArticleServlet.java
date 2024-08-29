package article;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

        try {
            // Connection parameters
                Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/mergui";
            String dbUser = "root";
            String dbPass = "root";

            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

            String sql = "INSERT INTO articles (title, content, category, image) values (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, content);
            statement.setString(3, category);

            if (inputStream != null) {
                // Fetches input stream of the upload file for the blob column
                statement.setBlob(4, inputStream);
            }

            int row = statement.executeUpdate();
            if (row > 0) {
                message = "Article uploaded successfully!";
            }
            conn.close();
        } catch (Exception e) {
            message = "ERROR: " + e.getMessage();
            e.printStackTrace();
        }

        // Redirects to a success page with a message
        response.sendRedirect("coadmin.jsp?message=" + message);
    }
}
