import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
public class ShowDetails extends HttpServlet {

    Connection con;
    PreparedStatement ps;

    @Override
    public void init(){
        try
        {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con=DriverManager.getConnection("jdbc:odbc:EshopDsn");
            ps=con.prepareStatement("select * from products where pid=?");
        }
        catch(Exception ex){}
    }
    @Override
    public void destroy(){
        try
        {
               con.close();
        }
        catch(Exception e){}
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        int n=Integer.parseInt(request.getParameter("id"));
        try
        {
            ps.setInt(1, n);
            ResultSet rs=ps.executeQuery();
            rs.next();
            String s1=rs.getString(1);//pid
            String s2=rs.getString(2);//pname
            String s3=rs.getString(3);//pdesc
            String s4=rs.getString(4);//pcat
            String s5=rs.getString(5);//price
            out.println("<html>");
            out.println("<body bgcolor=#3B170B>");
            out.println("<hr>");
            out.println("<h2>Product Details</h2>");
            out.println("<hr>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<td>Product-Id</td>");
            out.println("<td>"+s1+"</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>PName</td>");
            out.println("<td>"+s2+"</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>PDesc</td>");
            out.println("<td>"+s3+"</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>PCat</td>");
            out.println("<td>"+s4+"</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Price</td>");
            out.println("<td>"+s5+"</td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("<hr>");
            out.println("<a href=AddInCart?id="+s1+">Add-To-Cart</a><br>");
            out.println("<a href=ShowCat>Cat-Page</a><br>");
            out.println("<a href=buyerhome.jsp>home</a><br>");
            out.println("</body>");
            out.println("</html>");




        }
        catch(Exception ex){}

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
