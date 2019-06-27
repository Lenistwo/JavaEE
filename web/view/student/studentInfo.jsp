<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Student Information</title>
</head>
<body>
<table border="1px solid black">
    <tr>
        <th>Nazwa Grupy</th>
        <th>Nazwa Specializacji</th>
    </tr>
        <tr>
            <td>${info.getId_g().getName()}</td>
            <td>${info.getId_g().getId_spec().getName()}</td>
        </tr>
</table>
<a href="listOfStudents">Powrot do listy</a>
</body>
</html>
