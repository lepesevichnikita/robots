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
            <jsp:include page="new_task.jsp"/>
            <div class="ui three stackable cards">
                <c:forEach var="task" items="${tasks}">
                    <div class="card">
                        <div class="content">
                            <div class="header">
                                Task #${task.getId()}
                                <div class="meta">
                                        ${task.getStatus().name()}
                                </div>
                            </div>
                        </div>
                        <c:if test="${task.hasTitle()}">
                            <div class="content">
                                ${task.getTitle()}
                            </div>
                        </c:if>
                        <c:if test="${task.hasDescription()}">
                            <div class="content">
                                    ${task.getDescription()}
                            </div>
                        </c:if>
                    </div>
                </c:forEach>
            </div>
        </div>
    </jsp:body>
</t:genericpage>
