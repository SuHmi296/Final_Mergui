package auth;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/signup")
public class signup extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles the HTTP POST method for user signup.
     *
     * @param request  The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html;charset=UTF-8");

        // Retrieve parameters from the request
        String username = request.getParameter("usernameOrBusinessName");
        String password = request.getParameter("password"); // In real applications, hash this before storing
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String userType = request.getParameter("role");
        String activationCode = request.getParameter("activationCode"); // New parameter for activation code

        // Validate required parameters
        if (username == null || password == null || email == null || phone == null || userType == null ||
            username.isEmpty() || password.isEmpty() || phone.isEmpty() || email.isEmpty() || userType.isEmpty()) {
            response.sendRedirect("/Mergui_Project/signup.html?err=missing_parameters");
            return;
        }

        // Database connection setup
        String jdbcURL = "jdbc:mysql://localhost:3306/mergui";
        String dbUser = "root";
        String dbPassword = "root";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            if (userType.equalsIgnoreCase("coadmin")) {
            	System.out.println("//Coadmin");
                // Check if the activation code is valid and unused
                String checkCodeSql = "SELECT CodeID FROM activationcode WHERE Code = ? AND IsUsed = 0";
                statement = connection.prepareStatement(checkCodeSql);
                statement.setString(1, activationCode);
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    // Activation code is valid
                	System.out.println("// Activation code is valid\r\n");
                    int codeId = resultSet.getInt("CodeID");

                    // Register the coadmin
                    String insertSql = "INSERT INTO coadmin (username, PasswordHash, email, phone) VALUES (?, ?, ?, ?)";
                    statement = connection.prepareStatement(insertSql);
                    statement.setString(1, username);
                    statement.setString(2, password); // TODO: Hash the password before storing
                    statement.setString(3, email);
                    statement.setString(4, phone);

                    int rowsInserted = statement.executeUpdate();

                    if (rowsInserted > 0) {
                        // Update activation code to mark it as used
                        String updateCodeSql = "UPDATE activationcode SET CoadminID = ?, IsUsed = 1 WHERE CodeID = ?";
                        statement = connection.prepareStatement(updateCodeSql);
                        statement.setInt(1, getUserId(username, connection)); // Retrieve the new coadmin ID
                        statement.setInt(2, codeId);
                        statement.executeUpdate();

                        // Get the current session or create one if it doesn't exist
                        HttpSession session = request.getSession();
                        session.setAttribute("name", username);
                        session.setAttribute("email", email);
                        session.setAttribute("userType", userType);
                        session.setAttribute("phone", phone);
                        response.sendRedirect("/Mergui_Project/user.jsp");
                    } else {
                        // Insert failed
                        response.sendRedirect("/Mergui_Project/signup.html?err=signup_failed");
                    }
                } else {
                    // Activation code is invalid or already used
                    response.sendRedirect("/Mergui_Project/signup.html?err=invalid_activation_code");
                }
            } else {
            	System.out.println("//Customer");

                // Handle other Customer sign in
                String sql = "INSERT INTO customer (username, PasswordHash, email, phone) VALUES (?, ?, ?, ?)";
                statement = connection.prepareStatement(sql);
                statement.setString(1, username);
                statement.setString(2, password); // TODO: Hash the password before storing
                statement.setString(3, email);
                statement.setString(4, phone);

                int rowsInserted = statement.executeUpdate();

                HttpSession session = request.getSession();
                if (rowsInserted > 0) {
                    // Insert was successful
                    session.setAttribute("name", username);
                    session.setAttribute("email", email);
                    session.setAttribute("userType", userType);
                    session.setAttribute("phone", phone);
                    response.sendRedirect("/Mergui_Project/user.jsp");
                } else {
                    // Insert failed
                    response.sendRedirect("/Mergui_Project/signup.html?err=signup_failed");
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            response.sendRedirect("/Mergui_Project/signup.html?err=signup_error");
        } finally {
            // Close resources in reverse order of their opening
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Retrieve the ID of a user based on username. This method assumes that usernames are unique.
     * @param username The username of the user.
     * @param connection The database connection.
     * @return The user ID.
     * @throws SQLException If a database access error occurs.
     */
    private int getUserId(String username, Connection connection) throws SQLException {
        String sql = "SELECT CoadminID FROM coadmin WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("CoadminID");
                } else {
                    throw new SQLException("User not found: " + username);
                }
            }
        }
    }

    /**
     * Handles the HTTP GET method.
     * Redirects GET requests to a signup page or returns an error.
     *
     * @param request  The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Typically, GET requests for signup would display the signup form.
        // If this servlet only handles form submissions, you might redirect to the signup form.
        response.sendRedirect("/Mergui_Project/signup.html"); // Adjust the path as needed
    }
}
