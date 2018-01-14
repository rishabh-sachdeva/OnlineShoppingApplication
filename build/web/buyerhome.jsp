<%
    long v1=session.getMaxInactiveInterval();
    long v=session.getCreationTime();
    java.util.Date dt=new java.util.Date(v);        
%>
<html>
    <body>
        <h1>BUYER-HOME</h1>
        <h4>If You Are Inactive For <%=v1%> Seconds Your Session Will Be Destroyed</h4>
        <h3>You Are With Us From : <%=dt%></h3>
        <hr>
        <a href="ShowCat">Explore-Store</a><br>
        <a href="ShowShoppingCart">View-Cart</a><br>
        <a href="EndSession">Logout</a><br>
        <hr>
    </body>
</html>
