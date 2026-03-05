import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class AddUsageServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        double dataUsed = Double.parseDouble(request.getParameter("dataUsed"));

        HttpSession session = request.getSession();
        int userId = (Integer) session.getAttribute("userId");

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO usage(user_id,usage_date,data_used) VALUES(?,NOW(),?)");

            ps.setInt(1, userId);
            ps.setDouble(2, dataUsed);

            ps.executeUpdate();

            response.sendRedirect("dashboard.jsp");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
