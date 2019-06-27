<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create New Group</title>
</head>
<body>

<form action="createGroup" method="post">
    Nazwa: <input type="text" name="name" required>
    Specjalizacja: <select name="specialization" required>
        <c:forEach var="spec" items="${spec}">
            <option value="${spec.getId_spec()}">${spec.getName()}</option>
        </c:forEach>
    </select>
    Rok akademicki: <input type="number" name="academical_year" required min="1" max="127">
    <input type="submit" value="sing-up">
</form>
<a href="listOfGroups">Powrot do listy</a>
</body>
</html>
