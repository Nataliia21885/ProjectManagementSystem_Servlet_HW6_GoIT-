<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
  <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  </head>
    <body>
      <c:import url="/WEB-INF/jsp/navigation.jsp"/>
      <h3 style="color:blue;">Create new project:</h3>
        <form action="/projects" method="post">
            <label for="name"> Project name: </label><br>
            <input type="text" id="project_name" name="project_name"><br>
            <label for="name"> Country: </label><br>
            <input type="text" id="country" name="country"><br>
            <label for="name"> Company id(use only digits): </label><br>
            <input type="text" id="company_id" name="company_id"><br>
            <label for="name"> Customer id(use only digits): </label><br>
            <input type="text" id="customer_id" name="customer_id"><br>
            <label for="name"> Cost(use only digits): </label><br>
            <input type="text" id="cost" name="cost"><br>
            <label for="name"> Date of creation(in format: YY-MM-DD): </label><br>
            <input type="text" id="date_of_creation" name="date_of_creation"><br>
            <button type="submit">Save</button>
        </form>
        </br>
    </body>
 </html>