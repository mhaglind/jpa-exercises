<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
  <head>
    <title><spring:message code="application.title"/></title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <link rel="icon" href="<c:url value="/favicon.ico"/>" type="image/x-icon" />
    <link rel="shortcut icon" href="<c:url value="/favicon.ico"/>" type="image/x-icon" />

    <link href="<c:url value="css/style.css"/>" rel="stylesheet" type="text/css"/>

  </head>

<body>
  <div id="main">

    <div id="header">
      <span class="title"><spring:message code="application.title"/></span><br/>
    </div>


    <div id="content">
       <div id="block">
       	<h5>Personal information:</h5>
       	<p><c:out value="${person.name.firstName}"/>, <c:out value="${person.name.lastName}"/></p>
		       	
        <h5>Attending courses:</h5>
	      <c:forEach items="${courseList}" var="course">
	        <p><c:out value="${course.title}"/><br/>
		        Instructor: <c:out value="${course.instructor}"/>,
		        Date: <c:out value="${course.date}"/>,
		        Notes: <c:out value="${course.notes}"/>
	        </p>
	      </c:forEach>        

      </div>

    </div>

  <div class="lefty">
    <div class="menu">
      <c:forEach items="${eventList}" var="event">
        <a href="form.htm?id=<c:out value="${event.id}"/>"><c:out value="${event.title}"/> (<c:out value="${event.country}"/>)</a>
      </c:forEach>

    </div>
    
    
    <div class="menu">
       <a href="<c:url value="/info.htm"/>">Information</a>
    </div>
  </div>

</div>

</body>
</html>
