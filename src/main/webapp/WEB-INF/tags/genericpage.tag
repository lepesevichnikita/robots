<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<html>
<header>
    <jsp:invoke fragment="header"/>
</header>
<body>
    <jsp:doBody/>
</body>
<footer>
    <jsp:invoke fragment="footer"/>
</footer>
</html>