<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page pageEncoding="UTF-8"%>
<div role="main">

  <spring:hasBindErrors name="User">
 <p>   <b><spring:message code="page.label.fixerror" text="Please fix all errors!" /></b></p>
  </spring:hasBindErrors>
<div data-dojo-type="dijit/form/Form" id="myForm" data-dojo-id="myForm"
 action="<c:url value='/j_spring_security_check'/>"
	method="POST">
    <script type="dojo/on" data-dojo-event="reset">
        return confirm('Press OK to reset widget values');
    </script>

    <script type="dojo/on" data-dojo-event="submit">
        if(this.validate()){
	   		 require(["dojo/request/xhr"], function(xhr){
        xhr("<c:url value='/spring/ajaxSignup'/>",{
            handleAs:"text",
			method:"POST",
            query: {   username: dojo.byId("signupUsername").value,
					   nickname:dojo.byId("signupNick").value,
                       password: dojo.byId("signupPwd").value, 
					   passwordAgain:dojo.byId("signupPwdAgain").value
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
	<label for="signupUsername"><spring:message code="page.label.login.username" text="Username" /></label> 
	</td>
	<td>
						 <spring:bind path="signupForm.username">
				          <input id="signupUsername" type="text" name="username"   placeholder="<spring:message code="page.label.login.username" text="Username" />" required value="<c:out value="${status.value}" />">
						<a href="#" class="icon ticker"></a>       
						<font color="red"><c:out value="${status.errorMessage}"/></font>
						</spring:bind>
						
	</td>
	</tr>
	<tr>
	<td>					
	<label for="signupNick"> <spring:message
			code="page.label.login.nickname" text="Nick Name" />
	</label> 
	</td>
	<td>
						 <spring:bind path="signupForm.nickname">
				          <input id="signupNick" type="text" name="nickname"   placeholder="<spring:message code="page.label.login.nickname" text="Nick Name" />" required value="<c:out value="${status.value}" />">
						<a href="#" class="icon ticker"></a>       
						<font color="red"><c:out value="${status.errorMessage}"/></font>
						</spring:bind>
	</td>
	</tr>
	<tr>
	<td>					
							<label for="signupPwd"> <spring:message
			code="page.label.login.password" text="Password" />
	</label>
	</td>
	<td> 
					 <spring:bind path="signupForm.password">
				          <input type="password" name="password" id="signupPwd"  placeholder="Password" required >
						<a href="#" class="icon ticker"></a>       
						<font color="red"><c:out value="${status.errorMessage}"/></font>
						</spring:bind>
	</td>
	</tr>
	<tr>
	<td>
							<label for="signupPwdAgain"> <spring:message code="page.label.login.passwordAgain" text="Password Again" />
	</label> 
	</td>
	<td>			
						<spring:bind path="signupForm.passwordAgain">
				          <input type="password" name="passwordAgain" id="signupPwdAgain"   placeholder="<spring:message code="page.label.login.passwordAgain" text="Password Again" />" required >
						<a href="#" class="icon ticker"></a>       
						<font color="red"><c:out value="${status.errorMessage}"/></font>
						</spring:bind>
	</td>
	</tr>
	</table>
    <button data-dojo-type="dijit/form/Button" type="submit" name="submitButton" value="Submit"><spring:message
			code="page.label.signup.submit" /></button>
			    <button data-dojo-type="dijit/form/Button" type="reset" name="submitButton" value="Submit"><spring:message
			code="page.label.signup.reset" /></button>

</div>
</div>