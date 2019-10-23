<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="title" fragment="false" required="false" type="java.lang.String" %>

<html>
<title>
    ${title}
</title>
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