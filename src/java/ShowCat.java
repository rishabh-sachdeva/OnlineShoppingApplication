import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
public class ShowCat extends HttpServlet {

    Connection con;
    PreparedStatement ps;

    @Override
    public void init(){
        try
        {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con=DriverManager.getConnection("jdbc:odbc:EshopDsn");
            ps=con.prepareStatement("select distinct pcat from products");
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
        int pno=0;
        HttpSession session=request.getSession();
        ArrayList pList=(ArrayList)session.getAttribute("cart");
        if(pList!=null)
        {
            pno=pList.size();
        }

        //String uname=(String)session.getAttribute("user");
        //if(uname==null)
        //{
        //    response.sendRedirect("index.jsp");
        //}

        String prod="all";
        Cookie ck[]=request.getCookies();
        if(ck!=null)
        {
            for(Cookie c:ck)
            {
                String name=c.getName();
                if(name.equals("choice"))
                {
                    prod=c.getValue();
                    break;
                }
            }
        }


        try
        {
            ResultSet rs=ps.executeQuery();
            out.println("<html>");
            out.println("<body>");
            out.println("<h3><marquee>Attractive Offers On "+prod+" Products</marquee></h3>");
            out.println("<h4>Total Products In Cart "+pno+"</h4>");
            out.println("<h2>Welcome "+uname+"</h2>");
            out.println("<h2>Select Desired Category</h2>");
            while(rs.next())
            {
                String s=rs.getString(1);
                out.println("<a href=ShowProductList?pcat="+s+">");
                out.println(s);
                out.println("</a>");
                out.println("<br>");
            }
            out.println("<hr>");
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
