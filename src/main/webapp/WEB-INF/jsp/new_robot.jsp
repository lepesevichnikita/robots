<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Project: robots
  User: nikital
  Date: 10/18/19
  Time: 12:30 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="ui segment">
    <div class="ui form">
        <form:form action="/robot" method="POST">
            <input class="ui fluid positive button" type="submit" value="Create new idle robot"/>
        </form:form>
    </div>
</div>
