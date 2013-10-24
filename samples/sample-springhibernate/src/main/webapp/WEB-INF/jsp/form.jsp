<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">

  <head>
    <title><spring:message code="application.title"/></title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css"/>
  </head>

<body>
  <div id="main">

    <div id="header">
      <span class="title"><spring:message code="application.title"/></span><br/>
    </div>

    <div id="content">
	
	   <h1>Edit event: <c:out value="${command.title}"/> </h1>
       <br/>
       <form:form>
           <form:errors path="*" cssClass="errorBox" />

           <div class="first">
			   <form:label path="title">Event title:</form:label>
               <form:input path="title" />
               <form:errors path="title" cssClass="error" />
           </div>

           <div>
			   <form:label path="country">Country:</form:label>
               <form:select path="country">
                   <form:option value="" label="-- Select from list"/>
                   <form:options items="${countryList}" itemValue="code" itemLabel="name"/>
               </form:select>
               <form:errors path="country" cssClass="error"/>
           </div>

           <div>
			   <form:label path="subjects">Subjects:</form:label>
               <form:select path="subjects" items="${subjects}"/>
           </div>

           <div>
			   <form:label path="notes">Notes:</form:label>
               <form:textarea path="notes" rows="3" cols="20" />
               <form:errors path="notes" cssClass="error" />
           </div>

           <div>
               <input type="submit" value="Save Changes" />
           </div>
       </form:form>

	
       <br/>
       <h5>Participants registered:</h5>
      <c:forEach items="${command.participants}" var="person">
        <p><c:out value="${person.name.lastName}"/>, <c:out value="${person.name.firstName}"/></p>
      </c:forEach>
  </div>
  
  <div class="lefty">
    <div class="menu">
	    <a href="insert.htm">Insert Event</a>
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
