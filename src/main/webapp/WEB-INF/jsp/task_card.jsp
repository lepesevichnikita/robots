<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Project: robots
  User: nikital
  Date: 10/23/19
  Time: 12:42 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set value="${requestScope.task}" var="task"/>
<div class="card">
    <div class="content">
        <div class="header">
            Task #${task.getId()}
            <div class="meta">
                ${task.getCurrentState().getName()}
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
