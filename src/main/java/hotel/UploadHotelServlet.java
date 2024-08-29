package hotel;
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

@WebServlet("/UploadHotelServlet")
@MultipartConfig(maxFileSize = 16177215) // 16MB
public class UploadHotelServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection settings
    private String dbURL = "jdbc:mysql://localhost:3306/mergui";
    private String dbUser = "root";
    private String dbPass = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get form fields
        String hotelName = request.getParameter("hotelName");
        String hotelLocation = request.getParameter("hotelLocation");
        String[] roomTypes = request.getParameterValues("roomTypes");
        String hotelContact = request.getParameter("hotelContact");
        String hotelPhone = request.getParameter("hotelPhone");

        // Convert roomTypes array to a single string
        String roomTypesString = String.join(",", roomTypes);

        // Get the hotel image
        Part filePart = request.getPart("hotelImage");
        InputStream inputStream = null;
        if (filePart != null) {
            // Prints out some info for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());

            // Obtain the input stream of the upload file
            inputStream = filePart.getInputStream();
        }

        Connection conn = null;
        String message = null;

        try {
            // Connect to the database
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

            // SQL query
            String sql = "INSERT INTO hotels (hotel_name, location, room_types, contact_address, phone, image_data) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, hotelName);
            statement.setString(2, hotelLocation);
            statement.setString(3, roomTypesString);
            statement.setString(4, hotelContact);
            statement.setString(5, hotelPhone);

            if (inputStream != null) {
                // Fetch the image file from the input stream
                statement.setBlob(6, inputStream);
            }

            int row = statement.executeUpdate();
            if (row > 0) {
                message = "Hotel added successfully!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "ERROR: " + e.getMessage();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            // Set the response message
            response.sendRedirect("coadmin.jsp?message=" + message);

        }
    }
}
