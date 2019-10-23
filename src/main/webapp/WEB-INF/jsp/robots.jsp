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
        <div class="ui justified container">
            <jsp:include page="menu.jsp"/>
            <jsp:include page="new_robot.jsp"/>
            <div class="ui three stackable cards">
                <c:forEach var="robot" items="${robots}">
                    <div class="card">
                        <div class="content">
                            <div class="header">
                                Robot #${robot.getId()}
                                <div class="meta">
                                        ${robot.getCurrentState().getName()}
                                </div>
                            </div>
                        </div>
                        <div class="content">
                            <c:if test="${robot.hasCurrentTask()}">
                                <div class="content">
                                    <c:if test="${robot.hasCurrentTask() && robot.getCurrentTask().hasDescription()}">
                                        ${robot.getCurrentTask().getDescription()}
                                    </c:if>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </jsp:body>
</t:defaultpage>
