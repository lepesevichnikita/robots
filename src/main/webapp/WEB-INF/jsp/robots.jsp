<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<t:defaultpage>
    <jsp:attribute name="title">
        Robots
    </jsp:attribute>
    <jsp:body>
        <jsp:include page="new_robot.jsp"/>
        <div class="ui three stackable cards">
            <c:forEach var="robot" items="${robots}">
                <div class="ui flui card">
                    <c:set var="robot" value="${robot}" scope="request"/>
                    <c:import url="robot_card.jsp"/>
                </div>
            </c:forEach>
        </div>
    </jsp:body>
</t:defaultpage>
