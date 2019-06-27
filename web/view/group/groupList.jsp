<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List of Groups</title>
</head>
<body>
<h1><a href="createGroup">Add new Group </a></h1>
<table border="1px solid black">
    <tr>
        <th>Nazwa</th>
        <th>Nazwa Specjalizacji</th>
        <th>Rok Akademicki</th>
        <th>Edycja</th>
        <th>Usun</th>
    </tr>
    <c:forEach var="spec" items="${group}">
        <tr>
            <td>${spec.getName()}</td>
            <td>${spec.getId_spec().getName()}</td>
            <td>${spec.getAcademical_year()}</td>
            <td><a href="updateGroup?id=${spec.getId_g()}">Klik</a></td>
            <td><a href="deleteGroup?id=${spec.getId_g()}">Klik</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
