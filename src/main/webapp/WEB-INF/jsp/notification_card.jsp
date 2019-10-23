<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Project: robots
  User: nikital
  Date: 10/23/19
  Time: 12:18 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set value="${requestScope.notification}" var="notification"/>
<div class="content">
    <div class="header">
        <h2 class="header">Notification</h2>
    </div>
    <div class="right meta">
        ${notification.getCreatedAt()}
    </div>
</div>
<div class="content">
    <h4 class="ui sub header">Details</h4>
    <div class="ui small feed">
        <div class="event">
            <div class="content">
                <div class="summary">
                    <c:if test="${notification.hasSubscribable()}">
                        From <a
                            href="/${notification.getSubscribableName().toLowerCase()}/${notification.getSubscribable().getId()}">
                            ${notification.getSubscribableName()} #${notification.getSubscribable().getId()}
                    </a>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="content">
    ${notification.getMessage()}
</div>
