<%@page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="title" scope="request">
	<spring:message code="error.error"/>
</c:set>
<jsp:include page="errorHeader.jsp"/>
<div class="div-sub-menu-wrapper"></div>
<div class="div-middle-wrapper">
	<h1 class="error-header"><spring:message code="error.error"/>: 404</h1>
	<h2 class="error-header"><spring:message code="error.notFound"/></h2>
	<hr/>
	<br/>
	<c:if test="${not empty errorText}">
		<label class="label-error-text"><spring:message code="${errorText}"/></label>
	</c:if>
</div>
<jsp:include page="footer.jsp"/>

