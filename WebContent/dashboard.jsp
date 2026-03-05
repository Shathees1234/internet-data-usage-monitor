<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>

<%
int userId = (Integer) session.getAttribute("userId");
double limit = (Double) session.getAttribute("limit");

Connection con = DBConnection.getConnection();

PreparedStatement ps = con.prepareStatement(
"SELECT SUM(data_used) FROM usage WHERE user_id=?");

ps.setInt(1,userId);

ResultSet rs = ps.executeQuery();

double used = 0;

if(rs.next()){
    used = rs.getDouble(1);
}

double remaining = limit - used;
%>

<h2>Internet Data Dashboard</h2>

Total Data Plan: <%=limit%> MB <br>
Used Data: <%=used%> MB <br>
Remaining Data: <%=remaining%> MB <br><br>

<h3>Add Daily Usage</h3>

<form action="AddUsageServlet" method="post">
Data Used (MB): <input type="text" name="dataUsed">
<input type="submit" value="Add">
</form>
