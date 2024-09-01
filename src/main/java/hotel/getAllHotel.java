package hotel;

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

@WebServlet("/getAllHotel")
public class getAllHotel extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public getAllHotel() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<HotelClass> hotels = new ArrayList<>();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/mergui";
            String dbUser = "root";
            String dbPass = "root";

            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
            String sql = "SELECT a.*, i.image_data FROM hotels a LEFT JOIN images i ON a.hotel_id = i.parent_id";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            
            while (rs.next()) {
                HotelClass hotel = new HotelClass();
                hotel.setHotelId(rs.getInt("hotel_id"));
                hotel.setHotelName(rs.getString("hotel_name")); // Removed trailing space
                hotel.setLocation(rs.getString("location"));
                hotel.setRoomTypes(rs.getString("room_types"));
                hotel.setContactAddress(rs.getString("contact_address"));
                hotel.setPhone(rs.getString("phone"));
                hotel.setCreatedAt(rs.getTimestamp("created_at"));
                
                // Handling image data as Base64
                byte[] imageBytes = rs.getBytes("image_data"); // Ensure image_data exists in ResultSet
                String image;
                if (imageBytes != null && imageBytes.length > 0) {
                    image = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytes);
                } else {
                    image = "./Images/backgroundimg.jpg"; // Default image URL
                }
                System.out.println("Image =>" + image);
                
                hotel.setImage(image);
                hotels.add(hotel); // Assuming hotels is a List<HotelClass>
            }

            
            // Convert the articles list to JSON
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonResponse = gson.toJson(hotels);
            
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
