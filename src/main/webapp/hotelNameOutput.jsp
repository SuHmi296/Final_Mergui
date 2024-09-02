<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Restaurant and Hotel Names</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    
    <script type="text/javascript" src="${pageContext.request.contextPath }/innerHtmlInserter.js"></script>
    <style>
        .container {
            display: flex;
            justify-content: space-evenly;
        }
    </style>
</head>
<body class="bg-gray-100 ">
<!-- navbar -->
	<nav
      id="navContainer"
      class="z-50 flex justify-between items-center p-1 px-20 sticky top-0 bg-white backdrop-blur-md"
    ></nav>
    
    <div class="container mx-auto p-6">
<div class="w-full md:w-1/2 bg-white shadow-lg rounded-lg p-4">
            <h1 class="text-3xl font-bold mb-4 text-red-700 ">Accommodation Name<c/h1>
            <ul class="list-disc pl-5 space-y-4">
                <%
                    String hotelDBURL = "jdbc:mysql://localhost:3306/mergui";
                    String hotelUser = "root";
                    String hotelPass = "root";
                    
                    Connection con = null;
                    PreparedStatement pstm = null;
                    ResultSet r = null;
                    String sq = "SELECT h_id, h_name FROM hotel";
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        con = DriverManager.getConnection(hotelDBURL, hotelUser, hotelPass);
                        pstm = con.prepareStatement(sq);
                        r = pstm.executeQuery();
                        while (r.next()) {
                            int id = r.getInt("h_id");
                            String name = r.getString("h_name");
                            %>
                           <li class="flex items-center">
                            <i class="fas fa-building mr-2 text-red-500"></i>
                                <a href="accomodation.html?h_id=<%= id %>" class="text-xl text-red-700 hover:underline hover:text-red-600"><%= name %></a>
                            </li>
                            <%
                        }
                    } catch (Exception e) {
                        e.printStackTrace(); 
                        response.getWriter().print("<p>Error: " + e.getMessage() + "</p>");
                    } finally {
                        if (r != null) {
                            try {
                                r.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        if (pstm != null) {
                            try {
                                pstm.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        if (con != null) {
                            try {
                                con.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                %>
            </ul></div>
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d62335.45897381604!2d98.62132849999999!3d12.451972849999999!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x30fbb04dd43ed2db%3A0x4d1bccdd01a793d3!2z4YCZ4YC84YCt4YCQ4YC6!5e0!3m2!1smy!2smm!4v1724772233365!5m2!1smy!2smm" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
        
    </div>
    <!--footer-->
    <footer
      id="footerContainer"
      class="flex flex-col items-center justify-center space-y-10 w-s4/5 max-w-7xl hover:bg-[#fafafa] hover:text-red-700 mx-auto"
    ></footer>
</body>
</html>