import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM users WHERE username=? AND password=?");

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                HttpSession session = request.getSession();
                session.setAttribute("userId", rs.getInt("id"));
                session.setAttribute("limit", rs.getDouble("data_limit"));

                response.sendRedirect("dashboard.jsp");

            } else {
                response.getWriter().println("Invalid Login");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
