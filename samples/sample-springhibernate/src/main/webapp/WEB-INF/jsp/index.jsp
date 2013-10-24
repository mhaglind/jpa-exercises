<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">

<head>
<title><spring:message code="application.title" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<link href="<c:url value="/css/green.css"/>" rel="stylesheet"
	type="text/css" />
</head>

<body>

<!-- 
	header START
-->
<div id="header">
	
	<div id="logo">
		<h1><a href="#"><spring:message code="application.title" /></a></h1>
		<p><em><spring:message code="application.subtitle" /></em></p>
	</div>
	<hr />
	
	<div id="menu">
		<ul>
			<li class="current_page_item"><a href="#" class="first"><spring:message code="captainslog.title" /></a></li>
			<li><a href="#"><spring:message code="about.title" /></a></li>
		</ul>
	</div>
</div>
<!-- 
	header END
-->


<!-- 
	main START
-->
<div id="wrapper">
	<div id="wrapper-bgtop">
		<div id="wrapper-bgbtm">
			<div id="page">
			
				<!-- 
					content START
				-->
				<div id="content">
				
					<div class="post">						
						<h2 class="title"><a href="#">Latest Loggings </a></h2>
						<div class="entry">
							
								<c:forEach items="${items}" var="item">
									<p class="meta"><c:out value="${item}" /></p>
								</c:forEach>
																					
							<!--<p class="meta">Sunday, April 26, 2009 7:27</a></p> -->
						</div>						
					</div>
					
					<div class="post">												
						<div class="entry">
							<form method="post" action="/log-module-ui-springmvc/logit.html">
								<textarea class="meta" name="logit" cols="82" rows="5"></textarea>								
								<input type="submit" value="Log It!" />
							</form>				
							 <!-- <div><a href="#" class="links">Log</a></div> -->
						</div>						
					</div>
										
				</div>
				<!-- 
					content END
				-->
				
				<!-- 
					sidebar START
				-->
				<div id="sidebar">
					<ul>
						<li>
							<h2>Aliquam tempus</h2>
							<p>Mauris vitae nisl nec metus placerat perdiet est. Phasellus dapibus semper urna. Pellentesque ornare, orci in consectetuer hendrerit, volutpat.</p>
						</li>
						<li>
							<h2>Pellenteque ornare </h2>
							<ul>
								<li><a href="#">Nec metus sed donec</a></li>
								<li><a href="#">Magna lacus bibendum mauris</a></li>
								<li><a href="#">Velit semper nisi molestie</a></li>
								<li><a href="#">Eget tempor eget nonummy</a></li>
								<li><a href="#">Nec metus sed donec</a></li>
							</ul>
						</li>						
					</ul>
				</div>
				<!-- 
					sidebar START
				-->
				
			</div>			
			<div style="clear: both;">&nbsp;</div>
		</div>
	</div>
</div>
<!-- 
	main END
-->

<!-- 
	footer START
-->
<div id="footer-bgcontent">
	<div id="footer">
		<p>Copyright (c) 2011 evolutionaryarchitecture.net</p>
	</div>
</div>
<!-- 
	footer END
-->

</body>
</html>
