package restaurant;

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

@WebServlet("/UploadServlet")
@MultipartConfig(maxFileSize = 16177215) // 16MB
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String dbURL = "jdbc:mysql://localhost:3306/mergui";
    private String dbUser = "root";
    private String dbPass = "root";
    String message;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html;charset=UTF-8");
        
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String location = request.getParameter("location");
        String[] selectedServices = request.getParameterValues("service");
        String services = (selectedServices != null) ? String.join(", ", selectedServices) : "";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish database connection
            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

            String sql = "INSERT INTO restaurantTB (name, image, address, phone, location, service) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);

            // Set parameters for the prepared statement
            pstmt.setString(1, name);

            Part filePart = request.getPart("image"); // Image part
            if (filePart != null && filePart.getSize() > 0) {
                InputStream inputStream = filePart.getInputStream();
                pstmt.setBlob(2, inputStream);
            } else {
                pstmt.setNull(2, java.sql.Types.BLOB); // Handle case where no image is uploaded
            }

            pstmt.setString(3, address);
            pstmt.setString(4, phone);
            pstmt.setString(5, location);
            pstmt.setString(6, services);

            // Execute the insert statement
            int row = pstmt.executeUpdate();
            if (row > 0) {
            	message = "Success";
            } else {
            	message = "Failed";
            }
            
            response.sendRedirect("coadmin.jsp?message=" + message);


        } catch (Exception ex) {
            ex.printStackTrace();
            response.getWriter().print("Error: " + ex.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
