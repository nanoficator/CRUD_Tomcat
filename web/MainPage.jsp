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
    <td>
        <script>

            var table = JSON.parse('${tableHQL}');
            var rows = parseInt(${rowsHQL});

            document.write ('<table width="100%" border="1">');
            document.write("<th>ID</th>");
            document.write("<th>First Name</th>");
            document.write("<th>Second Name</th>");
            document.write("<th>Username</th>");
            document.write("<th>Age</th>");
            document.write("<th>Gender</th>");
            document.write("<th>Options</th>");
            for (i = 0; i < rows; i++) {
                document.writeln("<tr>");
                document.write("<td>" + table[i].id + "</td>");
                document.write("<td>" + table[i].firstName + "</td>");
                document.write("<td>" + table[i].secondName + "</td>");
                document.write("<td>" + table[i].userName + "</td>");
                document.write("<td>" + table[i].age + "</td>");
                document.write("<td>" + table[i].gender + "</td>");
                document.write("<td>" + "<a href=/delete/HQL/user?id=" + table[i].id + ">Remove</a>" + " / " + "<a href=/edit/HQL/user?id=" + table[i].id + ">Edit</a>" + "</td>");
                document.writeln("</tr>");
            }
            document.write ("</table> ");

        </script>

        <br>

        <table align="center" border="0">
            <th><a href="/delete/HQL/all">CLEAR DATA BASE</a></th>
            <th><a href="/add/HQL">ADD NEW USER</a></th>
        </table>
    </td>
    <td></td>
    <td>
        <script>

            var table = JSON.parse('${tableSQL}');
            var rows = parseInt(${rowsSQL});

            document.write ('<table width="100%" border="1">');
            document.write("<th>ID</th>");
            document.write("<th>First Name</th>");
            document.write("<th>Second Name</th>");
            document.write("<th>Username</th>");
            document.write("<th>Age</th>");
            document.write("<th>Gender</th>");
            document.write("<th>Options</th>");
            for (i = 0; i < rows; i++) {
                document.writeln("<tr>");
                document.write("<td>" + table[i].id + "</td>");
                document.write("<td>" + table[i].firstName + "</td>");
                document.write("<td>" + table[i].secondName + "</td>");
                document.write("<td>" + table[i].userName + "</td>");
                document.write("<td>" + table[i].age + "</td>");
                document.write("<td>" + table[i].gender + "</td>");
                document.write("<td>" + "<a href=/delete/SQL/user?id=" + table[i].id + ">Remove</a>" + " / " + "<a href=/edit/SQL/user?id=" + table[i].id + ">Edit</a>" + "</td>");
                document.writeln("</tr>");
            }
            document.write ("</table> ");

        </script>

        <br>

        <table align="center" border="0">
            <th><a href="/delete/SQL/all">CLEAR DATA BASE</a></th>
            <th><a href="/add/SQL">ADD NEW USER</a></th>
        </table>
    </td>

</table>

</body>
</html>