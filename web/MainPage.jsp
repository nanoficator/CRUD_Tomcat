<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>DB Info</title>
</head>
<body>

<table border="0">
    <tr>
        <td>
            <table border="1">

               <tr>
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Second Name</th>
                    <th>Username</th>
                    <th>Age</th>
                    <th>Gender</th>
                   <th>Role</th>
                    <th>Options</th>
               </tr>

                <%
                    List<User> allUsers = (List<User>) request.getAttribute("allUsers");
                    Iterator<User> iteratorHQL = allUsers.iterator();
                    while (iteratorHQL.hasNext()) {
                %>

                <tr>
                    <%User user = iteratorHQL.next();%>
                    <td><%=user.getId()%></td>
                    <td><%=user.getFirstName()%></td>
                    <td><%=user.getSecondName()%></td>
                    <td><%=user.getUserName()%></td>
                    <td><%=user.getAge()%></td>
                    <td><%=user.getGender()%></td>
                    <td><%=user.getRole()%></td>
                    <td><a href=/delete/user?id=<%=user.getId()%>>Remove</a> / <a href=/edit/user?id=<%=user.getId()%>>Edit</a></td>
                </tr>
                <%
                    }
                %>
            </table>

        </td>
    </tr>

    <tr>
        <td>
            <table align="center" border="0">
                <th><a href="/delete/all">CLEAR DATA BASE</a></th>
                <th><a href="/add">ADD NEW USER</a></th>
            </table>
        </td>
    </tr>

</table>

</body>
</html>