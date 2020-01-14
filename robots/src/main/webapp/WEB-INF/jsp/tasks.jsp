<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<t:defaultpage>
    <jsp:attribute name="title">
        Tasks
    </jsp:attribute>
    <jsp:body>
        <jsp:include page="new_task.jsp"/>
        <div class="ui three stackable cards">
            <c:forEach var="task" items="${tasks}">
                <div class="ui fluid card">
                    <c:set value="${task}" var="task" scope="request"/>
                    <c:import url="task_card.jsp"/>
                </div>
            </c:forEach>
        </div>
    </jsp:body>
</t:defaultpage>
