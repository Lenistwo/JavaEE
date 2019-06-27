<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Student</title>
</head>
<body>
<form action="updateStudent?id=${student.getId_s()}" method="post">
    Imie: <input type="text" name="name" required minlength="1" maxlength="20" value="${student.getName()}">
    Nazwisko: <input type="text" name="surname" required minlength="1" maxlength="35" value="${student.getSurname()}">
    Data urodzenia: <input type="date" name="date" required value="${student.getDate()}">
    Miasto: <input type="text" name="city" required minlength="1" maxlength="30" value="${student.getCity()}">
    <input type="submit" value="sing-up" required>
</form>
<a href="listOfStudents">Powrot do listy</a>
</body>
</html>
