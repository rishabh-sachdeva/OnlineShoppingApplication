<%
    Cookie c[]=request.getCookies();
    String v1="", v2="";
    if(c!=null)
    {
    for(int i=0; i<c.length;i++)
    {
        String name=c[i].getName();
        if(name.equals("uid"))
        {
            v1=c[i].getValue();
        }
        
        if(name.equals("pwd"))
        {
            v2=c[i].getValue();
        }
    }
    }
   
    
%>
<html>
    <body>
        <h2>E-Shop</h2>
        <form action="VerifyUser">
            <table border="2">
                <tr>
                    <td>User-Id</td>
                    <td><input type="text" name="userid" value="<%=v1%>" /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password" value="<%=v2%>" /></td>
                </tr>
                <tr>
                    <td>User-Type</td>
                    <td><select name="usertype">
                            <option>buyer</option>
                            <option>admin</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Save-Password</td>
                    <td><input type="checkbox" name="save" value="yes" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Submit" /></td>
                </tr>
            </table>
        </form>
        <a href="registration.jsp">new-user-registration</a>
    </body>
</html>
