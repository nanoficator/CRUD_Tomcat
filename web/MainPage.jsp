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

<table width="100%" border="0">

    <tr>
        <th width="48%">HQL</th>
        <th width="4%"></th>
        <th width="48%">SQL</th>
    </tr>

    <tr>
        <td>
            <table width="100%" border="1">

               <tr>
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Second Name</th>
                    <th>Username</th>
                    <th>Age</th>
                    <th>Gender</th>
                    <th>Options</th>
               </tr>

                <%
                    List<User> allUsersHQL = (List<User>) request.getAttribute("allUsersHQL");
                    Iterator<User> iteratorHQL = allUsersHQL.iterator();
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
                    <td><a href=/delete/HQL/user?id=<%=user.getId()%>>Remove</a> / <a href=/edit/HQL/user?id=<%=user.getId()%>>Edit</a></td>
                </tr>
                <%
                    }
                %>
            </table>

        </td>

        <td></td>

        <td>
            <table width="100%" border="1">

                <tr>
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Second Name</th>
                    <th>Username</th>
                    <th>Age</th>
                    <th>Gender</th>
                    <th>Options</th>
                </tr>

                <%
                    List<User> allUsersSQL = (List<User>) request.getAttribute("allUsersSQL");
                    Iterator<User> iteratorSQL = allUsersSQL.iterator();
                    while (iteratorSQL.hasNext()) {
                %>

                <tr>
                    <%User user = iteratorSQL.next();%>
                    <td><%=user.getId()%></td>
                    <td><%=user.getFirstName()%></td>
                    <td><%=user.getSecondName()%></td>
                    <td><%=user.getUserName()%></td>
                    <td><%=user.getAge()%></td>
                    <td><%=user.getGender()%></td>
                    <td><a href=/delete/HQL/user?id=<%=user.getId()%>>Remove</a> / <a href=/edit/HQL/user?id=<%=user.getId()%>>Edit</a></td>
                </tr>
                <%
                    }
                %>
            </table>

        </td>

        </td>
    </tr>

    <tr>
        <td>
            <table align="center" border="0">
                <th><a href="/delete/HQL/all">CLEAR DATA BASE</a></th>
                <th><a href="/add/HQL">ADD NEW USER</a></th>
            </table>
        </td>

        <td></td>

        <td>
            <table align="center" border="0">
                <th><a href="/delete/SQL/all">CLEAR DATA BASE</a></th>
                <th><a href="/add/SQL">ADD NEW USER</a></th>
            </table>
        </td>
    </tr>

</table>

</body>
</html>