import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class login extends HttpServlet
{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException	
	{ 
		 response.setContentType("text/html");
		 PrintWriter out =response.getWriter();
		 try{
        	    
        	     Class.forName("com.mysql.jdbc.Driver");
				 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/WebProj", "root", "kanu03");
				 Statement stmt = conn.createStatement();
         		 String  username= request.getParameter("username");
				 String  password=request.getParameter("password");
				 String sql="SELECT password FROM asp where username='"+username+"'";
				 ResultSet rs = stmt.executeQuery(sql);
				 out.println("username:"+username);
				 out.println("password:"+password);
				 if(rs.next())
				 {
					 String fpassword= rs.getString("password");
					 if(password.equals(fpassword))
					 {
						out.println("Entered password was correct");
						String site = new String("HTML/project.html");
						response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location", site); 
						response.sendRedirect("HTML/project.html");
					 }
					 else
					 {
						 out.println("Invalid Password");
					 }
				 }
				 else
				 {
					 out.println("Invalid Username");
				 }
		 }
		 catch(SQLException se)
      	{
         se.printStackTrace();
     	}
      	catch(Exception e)
      	{
         e.printStackTrace();
      	}
	}
}