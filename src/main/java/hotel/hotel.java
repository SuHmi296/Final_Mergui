package hotel;

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

@WebServlet("/hotel")
public class hotel extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String message = "";

        switch (action) {
            case "create":
                message = createHotel(request);
                break;
            case "update":
                message = updateHotel(request);
                break;
            case "delete":
                message = deleteHotel(request);
                break;
            case "viewAll":
                request.setAttribute("hotels", viewAllHotels());
                request.getRequestDispatcher("hotels.jsp").forward(request, response);
                return;
            case "view":
                request.setAttribute("hotel", viewHotel(request));
                request.getRequestDispatcher("viewHotel.jsp").forward(request, response);
                return;
            default:
                message = "Invalid action!";
                break;
        }

        response.sendRedirect("coadmin.jsp?message=" + message);
    }

    private String createHotel(HttpServletRequest request) {
        String hotelName = request.getParameter("hotel_name");
        String location = request.getParameter("location");
        String roomTypes = request.getParameter("room_types");
        String contactAddress = request.getParameter("contact_address");
        String phone = request.getParameter("phone");
        String message;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/mergui";
            String dbUser = "root";
            String dbPass = "root";

            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
            conn.setAutoCommit(false); // Start transaction

            // Insert hotel
            String sqlHotel = "INSERT INTO hotels (hotel_name, location, room_types, contact_address, phone) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement hotelStatement = conn.prepareStatement(sqlHotel, Statement.RETURN_GENERATED_KEYS);
            hotelStatement.setString(1, hotelName);
            hotelStatement.setString(2, location);
            hotelStatement.setString(3, roomTypes);
            hotelStatement.setString(4, contactAddress);
            hotelStatement.setString(5, phone);

            int hotelRow = hotelStatement.executeUpdate();
            int hotelId = 0;
            if (hotelRow > 0) {
                ResultSet generatedKeys = hotelStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    hotelId = generatedKeys.getInt(1);
                }
            }

            // Insert image
            Part filePart = request.getPart("image");
            if (filePart != null && hotelId > 0) {
                String sqlImage = "INSERT INTO images (parent_id, image_data) VALUES (?, ?)";
                PreparedStatement imageStatement = conn.prepareStatement(sqlImage);
                imageStatement.setInt(1, hotelId);
                imageStatement.setBlob(2, filePart.getInputStream());
                imageStatement.executeUpdate();
            }

            conn.commit();
            message = "Hotel and image uploaded successfully!";
        } catch (Exception e) {
            message = "ERROR: " + e.getMessage();
            e.printStackTrace();
        }
        return message;
    }

    private String updateHotel(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("hotel_id"));
        String hotelName = request.getParameter("hotel_name");
        String location = request.getParameter("location");
        String roomTypes = request.getParameter("room_types");
        String contactAddress = request.getParameter("contact_address");
        String phone = request.getParameter("phone");
        String message;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/mergui";
            String dbUser = "root";
            String dbPass = "root";

            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
            String sql = "UPDATE hotels SET hotel_name=?, location=?, room_types=?, contact_address=?, phone=? WHERE hotel_id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, hotelName);
            statement.setString(2, location);
            statement.setString(3, roomTypes);
            statement.setString(4, contactAddress);
            statement.setString(5, phone);
            statement.setInt(6, id);

            int row = statement.executeUpdate();
            if (row > 0) {
                message = "Hotel updated successfully!";
            } else {
                message = "Failed to update hotel.";
            }
        } catch (Exception e) {
            message = "ERROR: " + e.getMessage();
            e.printStackTrace();
        }
        return message;
    }

    private String deleteHotel(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("hotel_id"));
        String message;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/mergui";
            String dbUser = "root";
            String dbPass = "root";

            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
            String sql = "DELETE FROM hotels WHERE hotel_id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            int row = statement.executeUpdate();
            if (row > 0) {
                message = "Hotel deleted successfully!";
            } else {
                message = "Failed to delete hotel.";
            }
        } catch (Exception e) {
            message = "ERROR: " + e.getMessage();
            e.printStackTrace();
        }
        return message;
    }

    private ArrayList<HotelClass> viewAllHotels() {
        ArrayList<HotelClass> hotels = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/mergui";
            String dbUser = "root";
            String dbPass = "root";

            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
            String sql = "SELECT h.*, i.image_data FROM hotels h LEFT JOIN images i ON h.hotel_id = i.parent_id";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                HotelClass hotel = new HotelClass();
                hotel.setHotelId(resultSet.getInt("hotel_id"));
                hotel.setHotelName(resultSet.getString("hotel_name"));
                hotel.setLocation(resultSet.getString("location"));
                hotel.setRoomTypes(resultSet.getString("room_types"));
                hotel.setContactAddress(resultSet.getString("contact_address"));
                hotel.setPhone(resultSet.getString("phone"));
                hotel.setCreatedAt(resultSet.getTimestamp("created_at"));
                hotel.setPostedAt(resultSet.getTimestamp("posted_at"));

                byte[] imageBytes = resultSet.getBytes("image_data");
                String image;
                if (imageBytes != null && imageBytes.length > 0) {
                    image = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytes);
                } else {
                    image = "./Images/backgroundimg.jpg"; // Default image URL
                }
                System.out.println("Image =>"+image);
                hotel.setImage(image);
                hotels.add(hotel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotels;
    }


    private HotelClass viewHotel(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("hotel_id"));
        HotelClass hotel = new HotelClass();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/mergui";
            String dbUser = "root";
            String dbPass = "root";

            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
            String sql = "SELECT h.*, i.image_data FROM hotels h LEFT JOIN images i ON h.hotel_id = i.parent_id WHERE h.hotel_id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                hotel.setHotelId(resultSet.getInt("hotel_id"));
                hotel.setHotelName(resultSet.getString("hotel_name"));
                hotel.setLocation(resultSet.getString("location"));
                hotel.setRoomTypes(resultSet.getString("room_types"));
                hotel.setContactAddress(resultSet.getString("contact_address"));
                hotel.setPhone(resultSet.getString("phone"));
                hotel.setCreatedAt(resultSet.getTimestamp("created_at"));
                hotel.setPostedAt(resultSet.getTimestamp("posted_at"));

                byte[] imageBytes = resultSet.getBytes("image_data");
                String image;
                if (imageBytes != null && imageBytes.length > 0) {
                    image = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytes);
                } else {
                    image = "./Images/backgroundimg.jpg"; // Default image URL
                }
                System.out.println("Image =>"+image);
                
                hotel.setImage(image);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotel;
    }
}

