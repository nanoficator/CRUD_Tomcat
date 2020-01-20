<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delete User</title>
</head>
<body>

<p>Are you sure to delete ${message}?</p>
<form action="/admin/delete/${id}" method="post">
    <button>YES</button>
</form>

<form action="/admin/main" method="get">
    <button>NO</button>
</form>

</body>
</html>