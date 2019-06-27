<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create New Student</title>
</head>
<body>
<form action="createStudent" method="post">
    Imie: <input type="text" name="name" required minlength="1" maxlength="20">
    Nazwisko: <input type="text" name="surname" required minlength="1" maxlength="35">
    Data urodzenia: <input type="date" name="date" required>
    Miasto: <input type="text" name="city" required minlength="1" maxlength="30">
    <input type="submit" value="sing-up" required>
</form>
<a href="listOfStudents">Powrot do listy</a>
</body>
</html>
