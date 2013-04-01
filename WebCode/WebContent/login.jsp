<jsp:include page="/header.jsp">
  <jsp:param name="loginpage" value="Login Page" />
</jsp:include>

<%
if(request.getAttribute("message") != null){

out.println(request.getAttribute("message"));
}%>

<div id="menu">
            <ul>                                        
                <li><a href="index.jsp" title="">Home</a></li>
                <li><a class="current" href="login.jsp" title="">Login</a></li>
                <li><a href="web_store.jsp" title="">Web Store</a></li>                
                <!--li><a href="#" title=""></a></li>
                <li><a href="contact.html" title="">contact us</a></li-->
            </ul>
        </div>
</div>

<div id="login" align="center">



<form action="edu.byu.isys413.cca.actions.Login.action">
	<div id="username">Username:	<input type="text" name="username" size="25" /></div>
	<div id="password">Password:		<input type="password" name="password" size="25" /></div>
	<div id="login_button"><input type="submit" value="Login" /></div>
	<a href="new_customer.jsp">Create New Membership</a>
</form>


</div>


<jsp:include page="/footer.jsp"/>