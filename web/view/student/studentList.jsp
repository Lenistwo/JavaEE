<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List of Students</title>

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">

</head>
<body>
<table border="1px solid black">
    <tr>
        <th>Imie</th>
        <th>Nazwisko</th>
        <th>Data Urodzenia</th>
        <th>Miasto</th>
        <th>Edycja</th>
        <th>usun</th>
        <th>Informacje o studencie</th>
    </tr>
    <c:forEach var="spec" items="${student}">
    <tr>
        <td>${spec.getName()}</td>
        <td>${spec.getSurname()}</td>
        <td>${spec.getDate()}</td>
        <td>${spec.getCity()}</td>
        <td><a href="updateStudent?id=${spec.getId_s()}">Klik</a></td>
        <td><a href="deleteStudent?id=${spec.getId_s()}">Klik</a></td>
        <td><a href="studentInfo?id=${spec.getId_s()}">Klik</a></td>
    </tr>
    </c:forEach>
</table>
<a href="listOfStudents">Powrot do listy</a>
</body>
</html>
