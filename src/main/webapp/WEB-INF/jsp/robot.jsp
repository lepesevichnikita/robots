<<<<<<< HEAD
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
=======
>>>>>>> test
<%--
  Project: robots
  User: nikital
  Date: 10/23/19
  Time: 1:50 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<<<<<<< HEAD

<t:defaultpage>
    <jsp:attribute name="title">
        Robot #${robot.getId()}
    </jsp:attribute>
    <jsp:body>
        <div class="ui raised fluid card">
            <c:set value="${robot}" var="robot" scope="request"/>
            <c:import url="robot_card.jsp"/>
        </div>
        <div class="ui horizontal divider">
            Tasks
        </div>
        <div class="ui three cards stackable">
            <c:forEach items="${tasks}" var="task">
                <div class="ui fluid card">
                    <c:set var="task" value="${task}" scope="request"/>
                    <c:import url="task_card.jsp"/>
                </div>
            </c:forEach>
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
