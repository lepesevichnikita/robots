<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Project: robots
  User: nikital
  Date: 10/23/19
  Time: 12:44 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set value="${requestScope.robot}" var="robot"/>
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
