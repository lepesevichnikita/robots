<%--
  Project: robots
  User: nikital
  Date: 10/17/19
  Time: 4:38 PM
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<footer>
    <script type="text/javascript" src="/webjars/Semantic-UI/2.4.1/semantic.min.js"></script>
    <script type="text/javascript" src="/webjars/jquery/3.4.1/jquery.min.js"></script>
    <spring:url value="/js/main.js" var="mainJs"/>
    <script type="text/javascript" src="${mainJs}"></script>
</footer>
