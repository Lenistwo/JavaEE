<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Group</title>
</head>
<body>
<form action="updateGroup?id=${group.getId_g()}" method="post">
    Nazwa: <input type="text" name="name" value="${group.getName()}" required minlength="1" maxlength="10">
    Specjalizacja: <select name="specialization" required>
    <option selected>${current.getName()}</option>
    <c:forEach var="spec" items="${spec}">
        <option value="${spec.getId_spec()}">${spec.getName()}</option>
    </c:forEach>
</select>
    Rok akademicki: <input type="number" name="academical_year" value="${group.getAcademical_year()}" required min="1" max="127">
    <input type="submit" value="sing-up">
</form>
<a href="listOfGroups">Powrot do listy</a>
</body>
</html>
