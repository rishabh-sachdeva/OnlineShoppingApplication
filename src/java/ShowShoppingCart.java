import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowShoppingCart extends HttpServlet {
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        HttpSession session=request.getSession();
        ArrayList pList=(ArrayList)session.getAttribute("cart");
        if(pList==null)
        {
            out.println("<h2>Your Cart Is Empty</h2>");
            out.println("<h4><a href=ShowCat>Start Buying Now</a></h4>");
        }
        else
        {
            out.println("<h2>YOUR CART : </h2>");
            out.println("<hr>");
            out.println("<form action=RemoveFromCart>");
            out.println("<table border=2>");
            out.println("<tr>");
            out.println("<td>ProdId</td>");
            out.println("<td>Name</td>");
            out.println("<td>Desc</td>");
            out.println("<td>Cat</td>");
            out.println("<td>Price</td>");
            out.println("<td>Remove</td>");
            out.println("</tr>");
            int sum=0;
            for(int i=0; i<pList.size();i++)
            {
                String s=(String)pList.get(i);
                int id=Integer.parseInt(s);
                try
                {
                ps.setInt(1, id);
                ResultSet rs=ps.executeQuery();
                rs.next();
                String s1=rs.getString("pid");
                String s2=rs.getString("pname");
                String s3=rs.getString("pdesc");
                String s4=rs.getString("pcat");
                String s5=rs.getString("price");
                sum=sum+Integer.parseInt(s5);
                out.println("<tr>");
                out.println("<td>"+s1+"</td>");
                out.println("<td>"+s2+"</td>");
                out.println("<td>"+s3+"</td>");
                out.println("<td>"+s4+"</td>");
                out.println("<td align=right>"+s5+"</td>");
                out.println("<td align=center><input type=checkbox name=pid value="+s1+"></td>");
                out.println("</tr>");
                }
                catch(Exception ex){}
            }
            out.println("<tr>");
            out.println("<td></td>");
            out.println("<td></td>");
            out.println("<td>Total</td>");
            out.println("<td>Rs.</td>");
            out.println("<td>"+sum+"</td>");
            out.println("<td><input type=submit value=Remove></td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("</form>");
        }
        out.println("<a href=>Confirm-Order</a><br>");
        out.println("<a href=buyerhome.jsp>Home</a><br>");
        out.println("<a href=ShowCat>Buy More</a><br>");
        out.println("</body></html>");
    }
    public void destroy(){
        try
        {
            con.close();
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
