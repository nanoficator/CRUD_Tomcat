<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Add User</title>
</head>
<body>

<a href=/main>Back to main page</a>

<form action="/add/" method="post">
    <p>First Name: <input type="text" name="firstName"></p>
    <p>Second Name: <input type="text" name="secondName"></p>
    <p>Username: <input type="text" name="userName"></p>
    <p>Password: <input type="password" name="password"></p>
    <p>Confirm password: <input type="password" name="confirmPassword"></p>
    <p>Age: <input type="number" name="age"></p>
    <p>Gender:
        <input type="radio" value="male" name="gender">Male
        <input type="radio" value="female" name="gender">Female
    </p>
    <p>Role:
        <input type="radio" value="user" name="role">user
        <input type="radio" value="admin" name="role">admin
    </p>
    <p>Create new user: <input type="submit" name="SUBMIT"></p>
</form>

</body>
</html>