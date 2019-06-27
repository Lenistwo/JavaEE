<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Specialization</title>
</head>
<body>
<form action="updateSpecialization?id=${spec.getId_spec()}" method="post">
    Nazwa: <input type="text" name="name" value="${spec.getName()}" required minlength="1" maxlength="25">
    <input type="submit" value="sing-up">
</form>
<a href="listOfSpecializations">Powrot do listy</a>
</body>
</html>
