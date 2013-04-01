<%@ page import="edu.byu.isys413.lifegood.CreateDB" %>
<jsp:include page="/header.jsp">
  <jsp:param name="homepage" value="MyStuff Online" />
</jsp:include>

<div id="menu">
          <ul>                                        
              <li><a href="index.jsp" title="">Home</a></li>
              <li><a href="login.jsp" title="">Login</a></li>
              <li><a class="current" href="web_store.jsp" title="">Web Store</a></li>
              <!--li><a href="#" title=""></a></li>
              <li><a href="contact.html" title="">contact us</a></li-->
          </ul>
</div>
<%
	if (session.getAttribute("userid") == null) {
%>
<p>
	You must be logged in to access this page! Please Click Here to Login:
	<a href="login.jsp">Login Page</a>
</p>
<%
	} else {
%>

<div class="green_box">

</div>

<div id="main_content">

<div id="left_content">

<h3>Customer and Product Information</h3>
<% if(request.getAttribute("physical_product") == null){ %>
		<%=request.getAttribute("conceptual_product")%>
<% }else{%>
	<%=request.getAttribute("physical_product")%>
	
<% } %>

</div>

</div>





<%} %>
<jsp:include page="/footer.jsp"/>
