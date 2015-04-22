<%@ page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="<c:url value="/j_spring_security_check" />" method="POST">
	<label for="username">User Name:</label> <input id="username"
		name="j_username" type="text" /> <label for="password">Password:</label>
	<input id="password" name="j_password" type="password" />
	<input type="hidden" name="redirectURL" value="http://www.baidu.com">
	<label for="_spring_security_remember_me">Password:</label>
	<input id="_spring_security_remember_me" name="_spring_security_remember_me" type="checkbox" value="true"/>
	 <input
		type="submit" value="Log In" />
</form>
