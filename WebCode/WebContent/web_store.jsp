<jsp:include page="/header.jsp">
	<jsp:param name="webstore" value="Web Store" />
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

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<div id="main_content">

	<div align="center">
		Please Select A Store to Search From! <select id="store_dropdown">
		<% LinkedList<Store> stores = (LinkedList<Store>) request.getAttribute("stores"); %>
		<%for(int i=0; i < stores.size(); i++){ %>
			
			<option><%= stores.get(i).getCity() %></option>
		<%} %>
		</select>
	</div>

	<div id="left_content">

		<div class="search_box">
			<div class="search_title">Search For Any Product</div>
			<input id="search_product" class="search_input" type="text" onkeydown="myFunction()">
			<input src="images/search.gif" class="submit" type="image">
			<div class="subsearch">"You must select a store first."</div>
		</div>

		
		<h2>Products</h2>
		<p>We are committed to providing the best possible products and
			service to our customers.</p>

		<div id="left_nav"></div>

	</div>
	<!--end of left content-------------------------------------------------------------------------->



	<div id="right_content">

		<h2>Our Products</h2>
		<div id="products_box">
			<div id="product"></div>
		</div>

		<!--  div class="products_box">
			<img src="images/box_icon.gif" alt="" title="" class="box_img">
			<h3>Mobile Devices</h3>
			<p>Everything youasdf asdfasdfasdf asd fasdfasdf asdf asdfasdf
				asdfasdf sadfa s dfsdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdf</p>
			<p>
				<b>Price(new): &nbsp&nbsp&nbsp&nbsp $10.99 | Price(used):
					&nbsp&nbsp&nbsp&nbsp $4.99</b>
			</p>

			<div align="center">
				<h4>Quantity Available</h4>
				<font size="2">New: 5 | Used: 1</font>
			</div>
			<div class="read_more_link">
				<a href="">see more</a>
			</div>
		</div-->



	</div>
	<!--end of right content-->

	<div style="clear: both;"></div>

<script type="text/javascript">
    /** 
    * Submits the guess to the server.  This is the event code, very much
    * like an actionPerformed in Java.
    */
    function myFunction(){  // define the handler function for click event inline (it doesn't need a name because we're just using it one time)
    // get the value of the guess element
      var guessValue = document.getElementById("search_product").value;
      var e = document.getElementById("store_dropdown");
      var storeValue = e.options[e.selectedIndex].value;
      // send to the server (this is relative to our current page)
      $.ajax({
        url: "edu.byu.isys413.cca.actions.WebStore.action",
        data: { // this is an embedded JSON object we'll send to server
          search_parm: guessValue,
          store_parm: storeValue,
        },
        dataType: 'json',  // tell JQuery to expect JSON back from the server
        success: function(ret) {  // this is called as soon as the server returns
  			console.log(ret);
        	$("#product").html("");
        	
        for(var i = 0 ; i < ret.products.length; i++){
        	var product = ret.products[i];
        	$("#product").append("<div class='products_box'><form action='edu.byu.isys413.cca.actions.PurchasePage.action'><img src='images/box_icon.gif' alt='' title='' class='box_img'>"+"<h3>"+product.product_name+"</h3>"+"<h3><input type='text' name='product_store'value='"+product.store+"' readonly></h3>"+"<h3><input type='text' name='product_sku'value="+product.sku+" readonly></h3>"+"<p>"+product.product_description+"</p>"+"<p>"+product.product_description+"</p>"+"<p><b>Price: " +product.product_price_new+ "    |     Approximate Price(used): " +product.product_price_used+ "</b></p>"+"<h4>Quantity Available</h4><font size='2'>New:        " +product.product_qty_new+ "  |  Used:      " +product.product_qty_used+ "</font>"+"<div class='read_more_link'><input type='submit' value='Purchase' /></div></form></div>");
        	
        }
        }//success
      });//ajax
    };//click
</script>

	<%
		}//endifstatementforlogin
	%>
	
<jsp:include page="/footer.jsp" />