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
        <div class="ui justified container">
            <jsp:include page="menu.jsp"/>
            <div class="ui three stackable cards">
                <c:forEach var="notification" items="${notifications}">
                    <div class="card">
                        <div class="content">
                            <div class="header">
                                Notification
                                <div class="meta">
                                        ${notification.getCreatedAt().toString()}
                                </div>
                            </div>
                        </div>
                        <div class="content">
                            <h4 class="ui sub header">Details</h4>
                            <div class="ui small feed">
                                <div class="event">
                                    <div class="content">
                                        <div class="summary">
                                            <c:if test="${notification.hasSubscribable()}">
                                                From <a>${notification.getSubscribableName()}
                                                #${notification.getSubscribable().getId()}</a>
                                            </c:if>
                                            <c:if test="${notification.hasNotifiable()}">
<%--                                                To <a>${notification.getNotifiableName()}--%>
<%--                                                #${notification.getNotifiable().getId()} </a>--%>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="content">
                                ${notification.getMessage()}
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </jsp:body>
</t:defaultpage>
