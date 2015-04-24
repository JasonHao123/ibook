<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<c:set var="titleKey">
	<tiles:insertAttribute name="title" ignore="true" />
</c:set>
<c:set var="title" scope="request">
<spring:message code="${titleKey}" text="${titleKey}" />&nbsp;-&nbsp;<spring:message code="site.name" text="Smart Knowledgebase" />
</c:set>
<title>${title}</title>
		<link rel="stylesheet" href="<c:url value="/resources/css/themes/default/jquery.mobile-1.4.5.min.css" />">

		<link rel="shortcut icon" href="<c:url value="/resources/favicon.ico" />">
		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
		<script src="<c:url value="/resources/js/jquery.js" />"></script>

		<script src="<c:url value="/resources/js/jquery.mobile-1.4.5.min.js" />"></script>
	</head>
	<body>
	<div data-role="page" class="jqm-demos" data-quicklinks="true">
<tiles:insertAttribute name="header" />

<tiles:insertAttribute name="content" />	


<tiles:insertAttribute name="footer" />	


</div><!-- /page -->

</body>
</html>
