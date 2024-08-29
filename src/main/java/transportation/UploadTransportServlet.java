package transportation;

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

@WebServlet("/UploadTransportServlet")
@MultipartConfig(maxFileSize = 16177215) // upload file's size up to 16MB
public class UploadTransportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get form fields
        String transportType = request.getParameter("transportType");
        String transportLocation = request.getParameter("transportLocation");
        String[] transportServices = request.getParameterValues("transportService");
        String contactAddress = request.getParameter("transportContact");
        String phone = request.getParameter("transportPhone");
        
        // Combine services into a single string
        String services = String.join(",", transportServices);

        // Get the uploaded file part
        Part filePart = request.getPart("image");

        InputStream inputStream = null;

        if (filePart != null) {
            // Get the input stream of the uploaded file
            inputStream = filePart.getInputStream();
        }

        // Database connection
        String jdbcURL = "jdbc:mysql://localhost:3306/mergui";
        String dbUser = "root";
        String dbPassword = "root";
        String message = "";
        String sql = "INSERT INTO transportations (transport_type, location, services, contact_address, phone, image_data) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try  {
                Class.forName("com.mysql.cj.jdbc.Driver");
        		Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, transportType);
            statement.setString(2, transportLocation);
            statement.setString(3, services);
            statement.setString(4, contactAddress);
            statement.setString(5, phone);

            if (inputStream != null) {
                // Set the binary stream for the image
                statement.setBlob(6, inputStream);
            } else {
                statement.setNull(6, java.sql.Types.BLOB);
            }

            int row = statement.executeUpdate();

            if (row > 0) {
            	message = "Success";
            }else {
                response.getWriter().print("The Error.");

            }
            
            response.sendRedirect("coadmin.jsp?message=" + message);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
