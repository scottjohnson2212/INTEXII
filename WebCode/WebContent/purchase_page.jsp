<jsp:include page="/header.jsp">
	<jsp:param name="purchasepage" value="Purchase Page" />
</jsp:include>
<%@page import="java.util.*" %>
<%@page import="edu.byu.isys413.lifegood.*" %>

<div id="menu">
	<ul>
		<li><a href="index.jsp" title="">Home</a></li>
		<li><a href="login.jsp" title="">Login</a></li>
		<li><a class="current" href="web_store.jsp" title="">Web
				Store</a></li>
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

<h3>Used Products</h3>

<%int count = Integer.parseInt(request.getAttribute("count").toString()); %>
<% for(int i = 0; i < count;i++){ %>
	<%String productName = "pproduct"+i; %>
	<%if(request.getAttribute(productName) == null){%>
		<%=request.getAttribute("none")%>
	<%}else{%>
		<%=request.getAttribute(productName)%>
	<% }%>
	
<% } %>
</div>



<div id="right_content">

<h3>New Product</h3>

	<%=request.getAttribute("cproduct")%>






</div>


</div>





<%}//end else statement for login %>


<jsp:include page="/footer.jsp" />