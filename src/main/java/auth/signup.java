package auth;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/signup")
public class signup extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public signup() {
        super();
    }

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
        String username = request.getParameter("username");
        String password = request.getParameter("password"); // In real applications, hash this before storing
        String email = request.getParameter("email");
        String userType = request.getParameter("role");

        // Validate required parameters
        if (username == null || password == null || email == null || userType == null ||
            username.isEmpty() || password.isEmpty() || email.isEmpty() || userType.isEmpty()) {
            response.sendRedirect("/Mergui_Project/user.jsp?err=missing_parameters");
            return;
        }

        // Database connection setup
        String jdbcURL = "jdbc:mysql://localhost:3306/mergui";
        String dbUser = "root";
        String dbPassword = "root";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // SQL INSERT statement
            String sql = "INSERT INTO users (username, password, email, user_type) VALUES (?, ?, ?, ?)";

            // Create a PreparedStatement
            statement = connection.prepareStatement(sql);

            // Set parameters for the PreparedStatement
            statement.setString(1, username);
            statement.setString(2, password); // TODO: Hash the password before storing
            statement.setString(3, email);
            statement.setString(4, userType);

            // Execute the INSERT statement
            int rowsInserted = statement.executeUpdate();

            // Get the current session or create one if it doesn't exist
            HttpSession session = request.getSession();

            if (rowsInserted > 0) {
                // Insert was successful
                session.setAttribute("name", username);
                session.setAttribute("email", email);
                session.setAttribute("userType", userType);
                response.sendRedirect("/Mergui_Project/user.jsp");
            } else {
                // Insert failed
                response.sendRedirect("/Mergui_Project/user.jsp?err=signup_failed");
            }

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            // Optionally, log the exception and redirect to an error page
            response.sendRedirect("/Mergui_Project/user.jsp?err=signup_error");
        } finally {
            // Close resources in reverse order of their opening
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
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
