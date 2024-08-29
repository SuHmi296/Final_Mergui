package auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/signin")
public class signin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public signin() {
        super();
    }

    // Method to hash passwords using SHA-256
//    private String hashPassword(String password) {
//        try {
//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            byte[] hashBytes = digest.digest(password.getBytes());
//            StringBuilder sb = new StringBuilder();
//            for (byte b : hashBytes) {
//                sb.append(String.format("%02x", b));
//            }
//            return sb.toString();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String activationCode = request.getParameter("activationCode");
        String message="";
        // Database connection setup
        String jdbcURL = "jdbc:mysql://localhost:3306/mergui";
        String dbUser = "root";
        String dbPassword = "root";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // SQL to get the user data
            String sql = "SELECT * FROM users WHERE email = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);

            // Execute query
            resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                String storedPasswordHash = resultSet.getString("password");
                String storedRole = resultSet.getString("user_type");

                // Hash the provided password and compare with stored hash
                //String hashedPassword = hashPassword(password);
                String hashedPassword = password;

                if (storedPasswordHash.equals(hashedPassword)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("name", resultSet.getString("username"));
                    session.setAttribute("email", resultSet.getString("email"));
                    session.setAttribute("user_type", resultSet.getString("user_type"));

                    // Redirect to a page where the client-side script will store data in localStorage
                    String role = resultSet.getString("user_type");
                    response.sendRedirect("user.jsp?name=" + resultSet.getString("username") 
                                           + "&email=" + resultSet.getString("email") 
                                           + "&user_type=" + role);
                } else {
                    message = "Password Incorrect";
                    response.sendRedirect("signin.html?message=" + message);
                }

            } else {
                response.getWriter().println("No user found with the provided email.");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            out.println(e.getMessage());
            response.getWriter().println("Error in login.");
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                out.println(ex.getMessage());
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
