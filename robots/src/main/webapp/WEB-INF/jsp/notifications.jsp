<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<t:defaultpage>
    <jsp:attribute name="title">
        Notifications
    </jsp:attribute>
    <jsp:body>
        <div class="ui three stackable cards">
            <c:forEach var="notification" items="${notifications}">
                <div class="ui fluid card">
                    <c:set var="notification" value="${notification}" scope="request"/>
                    <c:import url="notification_card.jsp"/>
                </div>
            </c:forEach>
        </div>
    </jsp:body>
</t:defaultpage>
