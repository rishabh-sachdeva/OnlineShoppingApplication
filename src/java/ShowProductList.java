import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
public class ShowProductList extends HttpServlet {
    
    Connection con;
    PreparedStatement ps;
    
    @Override
    public void init(){
        try
        {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con=DriverManager.getConnection("jdbc:odbc:EshopDsn");
            ps=con.prepareStatement("select pid,pname from products where pcat=?");
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
        HttpSession session=request.getSession();
        String uname=(String) session.getAttribute("user");
        if(uname==null)
        {
            response.sendRedirect("index.jsp");
        }
        
        
        
        
        
        
        String s=request.getParameter("pcat");
        
        Cookie c=new Cookie("choice",s);
        c.setMaxAge(60*60*24*7);
        response.addCookie(c);
        
        try
        {
            ps.setString(1, s);
            ResultSet rs=ps.executeQuery();
            out.println("<html>");
            out.println("<body>");
            out.println("<h2>Welcome "+uname+"</h2>");
            out.println("<h2>Product List</h2>");
            out.println("<hr>");
            while(rs.next())
            {        
                String s1=rs.getString(1);//pid
                String s2=rs.getString(2);//pname    
                out.println("<a href=ShowDetails?id="+s1+">");
                out.println(s2);
                out.println("</a>");
                out.println("<br>");
            }       
            out.println("<hr>");
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
