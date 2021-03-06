<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page pageEncoding="UTF-8" %>
<div data-role="panel" class="jqm-navmenu-panel" data-position="left" data-display="overlay" data-theme="a">
<ul class="jqm-list ui-alt-icon ui-nodisc-icon">
<li data-filtertext="demos homepage" data-icon="home"><a href="<c:url value="/job/index.do" />">Home</a></li>
<c:forEach items="${categories}" var="category">
<c:choose>
	<c:when test="${category.leaf}">
		<li data-filtertext="demos homepage" data-icon="home"><a href="<c:url value="/job/search.do" ><c:param name="category" value="${category.name}" /></c:url>"> ${category.name }</a></li>	
	</c:when>
	<c:otherwise>
	<li data-role="collapsible" data-enhanced="true" data-collapsed-icon="carat-d" data-expanded-icon="carat-u" data-iconpos="right" data-inset="false" class="ui-collapsible ui-collapsible-themed-content ui-collapsible-collapsed">
	<h3 class="ui-collapsible-heading ui-collapsible-heading-collapsed">
		<a href="<c:url value="/job/search.do" ><c:param name="category" value="${category.id}" /></c:url>" class="ui-collapsible-heading-toggle ui-btn ui-btn-icon-right ui-btn-inherit ui-icon-carat-d">
		    ${category.name }<span class="ui-collapsible-heading-status"> click to expand contents</span>
		</a>
	</h3>
	<div class="ui-collapsible-content ui-body-inherit ui-collapsible-content-collapsed" aria-hidden="true">
		<ul>
		<c:forEach items="${category.children}" var="subCategory">
			<li><a href="<c:url value="/job/search.do" ><c:param name="subCategory" value="${subCategory.id}" /></c:url>" data-ajax="false"> ${subCategory.name }</a></li>	
	</c:forEach>
	</ul>
	</div>
</li>

	</c:otherwise>
</c:choose>
</c:forEach>

		     </ul>
		</div><!-- /panel -->