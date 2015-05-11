<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" %>
<link rel="stylesheet" href="<c:url value="/resources/css/listview-grid.css" />">
	<div role="main" class="ui-content jqm-content">
        <ul data-role="listview" data-inset="true">
        	<li><a href="#">
            	<img src="<c:url value="/resources/img/apple.png" />" class="ui-li-thumb">
            	<h2>iOS 6.1</h2>
                <p>Apple released iOS 6.1</p>
                <p class="ui-li-aside">iOS</p>
            </a></li>
        	<li><a href="#">
            	<img src="<c:url value="/resources/img/blackberry_10.png" />" class="ui-li-thumb">
            	<h2>BlackBerry 10</h2>
                <p>BlackBerry launched the Z10 and Q10 with the new BB10 OS</p>
                <p class="ui-li-aside">BlackBerry</p>
            </a></li>
 
        	<li><a href="<c:url value="/user/company/add.do" />" data-ajax="false">
            	<h2>Add</h2>
                
            </a></li>
        </ul>
        </div>