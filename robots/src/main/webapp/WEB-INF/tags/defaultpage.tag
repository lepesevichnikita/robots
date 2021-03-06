<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@tag description="Default Page template" pageEncoding="UTF-8" %>
<%@attribute name="title" required="false" type="java.lang.String" %>

<t:genericpage>
    <jsp:attribute name="title">
        ${title}
    </jsp:attribute>
    <jsp:attribute name="header">
        <jsp:include page="../jsp/head.jsp"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <jsp:include page="../jsp/footer.jsp"/>
    </jsp:attribute>
    <jsp:body>
        <jsp:include page="../jsp/menu.jsp"/>
        <div class="ui container"/>
        <div class="ui justified container">
            <jsp:doBody/>
        </div>
    </jsp:body>
</t:genericpage>
