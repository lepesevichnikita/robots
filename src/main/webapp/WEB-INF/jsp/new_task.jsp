<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Project: robots
  User: nikital
  Date: 10/17/19
  Time: 8:18 PM
--%>

<div class="ui segment">
    <div class="ui form">
        <form:form action="/task" method="POST" modelAttribute="newTask">
            <div class="field">
                <form:label path="title">Title</form:label>
                <form:input name="title" placeholder="Title" path="title"/>
            </div>
            <div class="field">
                <form:label path="description">Description</form:label>
                <form:textarea name="description" placeholder="Description" rows="2" path="description"/>
            </div>
            <div class="field">
                <form:label path="robot">Robot</form:label>
                <form:select path="robot" cssClass="ui search dropdown">
                    <form:option value="" label="Select robot"/>
                    <form:options items="${allRobots}" itemLabel="id" itemValue="id"/>
                </form:select>
            </div>
            <input class="ui fluid positive button" type="submit" value="Save"/>
        </form:form>
    </div>
</div>
