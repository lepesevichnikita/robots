<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Project: robots
  User: nikital
  Date: 10/23/19
  Time: 12:44 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set value="${requestScope.robot}" var="robot"/>
<div class="content">
    <div class="header">
        <a href="/robot/${robot.getId()}">
            <h2 class="header">
                Robot #${robot.getId()}
            </h2>
        </a>
        <div class="meta">
            ${robot.getCurrentState().getName()}
        </div>
    </div>
    <div class="right meta">
        ${robot.getCreatedAt()}
    </div>
</div>
<c:if test="${robot.hasCurrentTask()}">
    <c:if test="${robot.getCurrentTask().hasDescription()}">
        <div class="content">
                ${robot.getCurrentTask().getDescription()}
        </div>
    </c:if>
</c:if>
