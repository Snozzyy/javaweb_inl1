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

@WebServlet(urlPatterns = "/courses")
public class CoursesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name;
        int yhp;
        String description;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:13306/gritacademy",
                    "user", "");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM courses;");

            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();

            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" href=\"css/style.css\">");
            out.println("<title>Courses</title>");

            out.println("</head>");
            out.println("<body>");
            out.println("<nav class=\"navbar\">");
            out.println("<a href=\"/\">Home</a>");
            out.println("<a href=\"/students\">Students</a>");
            out.println("<a href=\"/courses\">Courses</a>");
            out.println("<a href=\"/attendance\">Attendance</a>");
            out.println("</nav>");
            out.println("<div class=\"tables\">");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Name</th>");
            out.println("<th>Yhp</th>");
            out.println("<th>Description</th>");
            out.println("</tr>");

            while (rs.next()) {
                name = rs.getString("name");
                yhp = rs.getInt("yhp");
                description = rs.getString("description");

                out.println("<tr>");
                out.printf("<td>%s</td>\n", name);
                out.printf("<td>%d</td>", yhp);
                out.printf("<td>%s</td>", description);
                out.println("</tr>");
            }
            con.close();

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
