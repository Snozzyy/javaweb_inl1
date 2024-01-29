package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(urlPatterns = "/students")
public class StudentsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fName;
        String lName;
        String city;
        String interests;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:13306/gritacademy",
                    "user", "");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students;");

            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Students</title>");

            out.println("<style>");
            out.println("table, th, td {");
            out.println("border:1px solid black;}");
            out.println("</style>");

            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"header\">");
            out.println("<a href=\"/\">Home</a>");
            out.println("<a href=\"/students\">Students</a>");
            out.println("<a href=\"/courses\">Courses</a>");
            out.println("<a href=\"/attendance\">Attendance</a>");
            out.println("</div>");
            out.println("<table style = \"border:px solid black\">");
            out.println("<tr>");
            out.println("<th>Name</th>");
            out.println("<th>City</th>");
            out.println("<th>Interests</th>");
            out.println("</tr>");

            while (rs.next()) {
                fName = rs.getString("fname");
                lName = rs.getString("lname");
                city = rs.getString("city");
                interests = rs.getString("interests");

                out.println("<tr>");
                out.printf("<td>%s %s</td>\n", fName, lName);
                out.printf("<td>%s</td>", city);
                out.printf("<td>%s</td>", interests);
                out.println("</tr>");
            }
            con.close();

            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
