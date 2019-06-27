<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Assign Student To Group</title>
</head>
<body>
<form action="studentInfo?id=${student.getId_s()}" method="post">
    Data: <input type="date" name="date" required>
    Grupa: <select name="group" required>
    <c:forEach var="group" items="${group}">
        <option value="${group.getId_g()}">${group.getName()}</option>
    </c:forEach>
</select>
    <input type="submit" value="sing-up">
</form>
<a href="listOfStudents">Powrot do listy</a>
</body>
</html>
