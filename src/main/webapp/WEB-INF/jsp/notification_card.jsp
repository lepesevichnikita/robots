<%--
  Project: robots
  User: nikital
  Date: 10/23/19
  Time: 12:18 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
