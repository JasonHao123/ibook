<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page pageEncoding="UTF-8"%>

<div role="main">
<p>
	<c:if test="${not empty param.login_error}">
		<font color="red"> Your login attempt was not successful, try
			again.<br /> <br /> Reason: <c:out
				value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />.
		</font>
	</c:if>
</p>

<div data-dojo-type="dijit/form/Form" id="myForm" data-dojo-id="myForm"
 action="<c:url value='/j_spring_security_check'/>"
	method="POST">
    <script type="dojo/on" data-dojo-event="reset">
        return confirm('Press OK to reset widget values');
    </script>

    <script type="dojo/on" data-dojo-event="submit">
        if(this.validate()){
	   		 require(["dojo/request/xhr"], function(xhr){
        xhr("<c:url value='/spring/ajaxLogin'/>",{
            handleAs:"text",
			method:"POST",
            query: {   username: dojo.byId("name").value,
                       password: dojo.byId("pwd").value, 
                       rememberMe: dojo.byId("rememberMe").value
                   }
               }).then(function(result){
				if("OK"==result) {
               		 window.location.reload();
				}else {
					alert(result);
				}
            });
   		 });
            return false;
        }else{
            alert('Form contains invalid data.  Please correct first');
            return false;
        }
        return true;
    </script>
	<input type="hidden" name="<c:out value="${_csrf.parameterName}"/>"
		value="<c:out value="${_csrf.token}"/>" /> 
    <table style="border: 1px solid #9f9f9f;" cellspacing="10">
        <tr>
            <td>
                <label for="name"><spring:message
			code="page.label.login.username" text="Username" /></label>
            </td>
            <td>
                <input type="text" id="name" name="j_username" required="true" data-dojo-type="dijit/form/ValidationTextBox"/>
            </td>
        </tr>
        <tr>
            <td>
                <label for="dob"><spring:message
			code="page.label.login.password" text="Password" /></label>
            </td>
            <td>
                <input type="password" id="pwd" name="j_password" data-dojo-type="dijit/form/ValidationTextBox"/>
            </td>
        </tr>
        <tr>
            <td>
                <label for="rem"><spring:message
			code="page.label.login.remember" text="Keep me logged in" /></label>
            </td>
            <td>
                <input type="checkbox" id="rememberMe" name="j_spring_security_remember_me" data-dojo-type="dijit/form/CheckBox"/>
            </td>
        </tr>
    </table>

    <button data-dojo-type="dijit/form/Button" type="submit" name="submitButton" value="Submit"><spring:message
			code="page.label.login.login" /></button>

    <button data-dojo-type="dijit/form/Button" ><spring:message
			code="page.label.login.signup" text="Signup" />
							 <script type="dojo/on" data-dojo-event="click" data-dojo-args="e">
					showInDialog('Signup','<c:url value="/spring/signup" />')
				</script>
			</button>
</div>
</div>