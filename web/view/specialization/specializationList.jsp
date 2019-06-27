<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List of Specializations</title>
</head>
<body>
    <h1><a href="createSpecialization">Add new Group </a></h1>
    <table border="1px solid black">
        <tr>
            <th>Nazwa</th>
            <th>Edycja</th>
            <th>usun</th>
        </tr>
        <c:forEach var="spec" items="${spec}">
            <tr>
                <td>${spec.getName()}</td>
                <td><a href="updateSpecialization?id=${spec.getId_spec()}">Klik</a></td>
                <td><a href="deleteSpecialization?id=${spec.getId_spec()}">Klik</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
