import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class signup extends HttpServlet
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
				 String  firstname= request.getParameter("firstname");
				 String  lastname= request.getParameter("lastname");
				 String  email= request.getParameter("email");
				 String  password=request.getParameter("password");
				 String sql="INSERT INTO asp(username,firstname,lastname,email,password)VALUES('"+username+"','"+firstname+"','"+lastname+"','"+email+"','"+password+"')";
				 int  rs = stmt.executeUpdate(sql);
				 if(rs>0)
				 {
						String site = new String("HTML/project.html");
						response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location", site);
				 }
				 else
				 {
					 out.println("Insertion was  not succesfull");
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