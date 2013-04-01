<%@ page import="edu.byu.isys413.lifegood.CreateDB" %>
<jsp:include page="/header.jsp">
  <jsp:param name="homepage" value="MyStuff Online" />
</jsp:include>

	<%CreateDB.main(null); %>


<div id="menu">
          <ul>                                        
              <li><a class="current" href="index.jsp" title="">Home</a></li>
              <li><a href="login.jsp" title="">Login</a></li>
              <li><a href="web_store.jsp" title="">Web Store</a></li>
              <!--li><a href="#" title=""></a></li>
              <li><a href="contact.html" title="">contact us</a></li-->
          </ul>
</div>
</div>

<div class="green_box">
    	<div class="clock">
        <img src="images/clock.png" alt="" title="">     
        </div>
        <div class="text_content">
        <h1>Welcome to our Online Web Store</h1>
        <p class="green">
        Please feel free to look around and purchase our products
        we thank you for your business and look forward to serving
        you and all your MyStuff needs!
        </p>
        <div class="read_more"><a href="web_store.jsp">WebStore</a></div>
        </div>
        
        <div id="right_nav">
            <ul>                                        
                <li><a href="web_store.jsp" title="">Web Store</a></li>
                <li><a href="login.jsp" title="">Customer Login</a></li>
                <!--li><a class="current" href="###################" title="">About MyStuff</a></li>
                <li><a href="#####################" title="">Lorem ipsum dolor sit amet cons</a></li>
                <li><a href="contact.html" title="">Lorem ipsum dolor sit amet cons</a></li-->
            </ul>
        </div>       
        
    
    </div><!--end of green box-->
    
    <div id="main_content">
    	<div id="left_content">
        <h2>About MyStuff</h2>
        <p>
We are committed to providing the best possible
products and service to our customers.         
        </p>
        
         <div id="left_nav">
            <ul>                                        
                <li><a title="">Online Purchasing</a></li>
                <li><a title="">In-Store Rentals</a></li>
                <li><a title="">Photo Developing</a></li>
                <li><a title="">Photo Backup</a></li>
            </ul>
        </div>
        
        <!-- p class="clear">
        <img src="images/pic1.jpg" alt="" title="" class="left_img">
        "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed 
do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim 
ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut 
aliquip ex ea commodo consequat. 
        </p>   
        <div class="read_more_link"><a href="#">read more</a></div-->     
        
        
        
        </div><!--end of left content-->



    	<div id="right_content">
        <h2>Our Products</h2>
        	<div class="products_box">
  <img src="images/box_icon.gif" alt="" title="" class="box_img">
 <h3>Cameras</h3>  
            <p>         
We offer a wide selection of Cameras that range from
a college students budget, to full scale photographer
equipment for the trained professional/enthusiastic
hobbyist.        
            </p>
            <div class="read_more_link"><a href="##########################">see more</a></div>
            </div>
            
            
         	<div class="products_box">
  <img src="images/box_icon.gif" alt="" title="" class="box_img">
 <h3>Mobile Devices</h3>  
            <p>         
Everything you, your family, or your business could need
to satisfy all of your mobile needs. Including Mp3 Players, Tablets, and
Smart Phones.       
            </p>
            <div class="read_more_link"><a href="###############################">see more</a></div>
            </div>
            
            
            
            
            <!-- div class="contact_information">
            <h4>Free Customers Support</h4>
            <p>
            <img src="images/phone_icon.gif" alt="" title="" class="box_img">
            0700 679 122 489<br>
            0700 679 122 489
            </p>
            <br><br>
            <p>
            <img src="images/contact_icon.gif" alt="" title="" class="box_img">
            info@company.com<br>
            contact@company.com
            </p>            
            
            </div-->         
            
            
            
            
            
            
        
        </div><!--end of right content--> 


    
    <div style=" clear:both;"></div>

<jsp:include page="/footer.jsp"/>
