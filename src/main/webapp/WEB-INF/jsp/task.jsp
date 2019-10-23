<<<<<<< HEAD
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%--
  Project: tasks
=======
<%--
  Project: robots
>>>>>>> test
  User: nikital
  Date: 10/23/19
  Time: 5:32 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<<<<<<< HEAD

<t:defaultpage>
    <jsp:attribute name="title">
        Task #${task.getId()}
    </jsp:attribute>
    <jsp:body>
        <div class="ui fluid raised card">
            <c:set value="${task}" var="task" scope="request"/>
            <c:import url="task_card.jsp"/>
        </div>
        <div class="ui horizontal divider">Robot</div>
        <div class="ui fluid card">
            <c:set value="${task.getRobot()}" var="robot" scope="request"/>
            <c:import url="robot_card.jsp"/>
        </div>
    </jsp:body>
</t:defaultpage>
=======
<html>
<head>
    <title>$Title$</title>
</head>
<body>
$END$
</body>
</html>
>>>>>>> test
