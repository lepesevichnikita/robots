<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<t:genericpage>
    <jsp:attribute name="header">
        <jsp:include page="head.jsp"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <jsp:include page="footer.jsp"/>
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
                                        ${robot.getStatus().name()}
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
</t:genericpage>
