<jsp:include page="/header.jsp">
  <jsp:param name="newcustomer" value="New Customer Page" />
</jsp:include>

<div id="menu">
            <ul>                                        
                <li><a href="index.jsp" title="">Home</a></li>
                <li><a class="current" href="login.jsp" title="">Login</a></li>
                <li><a href="web_store.jsp" title="">Purchase Products</a></li>                
                <!--li><a href="#" title=""></a></li>
                <li><a href="contact.html" title="">contact us</a></li-->
            </ul>
        </div>
</div>


<h2>Contact form</h2>
         <div id="contact_form">
         <form action="edu.byu.isys413.cca.actions.NewAccount.action">
            <div class="form_row">
            <label>First Name:</label><input type="text" name="firstname" class="contact_input" />
            </div>
            
            <div class="form_row">
            <label>Middle Name:</label><input type="text" name="middlename" class="contact_input" />
            </div>                    
            
             <div class="form_row">
            <label>Last Name:</label><input type="text" name="lastname" class="contact_input" />
            </div>                   
            
            <div class="form_row">
            <label>Address:</label><textarea type="text" name="address" class="contact_textarea"></textarea>
            </div> 
            
            <div class="form_row">
            <label>City:</label><input type="text" name="city" class="contact_input" />
            </div>
            
            <div class="form_row">
            <label>State:</label><input type="text" name="state" class="contact_input" />
            </div>   
            
            <div class="form_row">
            <label>Zip:</label><input type="text" name="zip" class="contact_input" />
            </div>
            
            <div class="form_row">
            <label>Phone:</label><input type="text" name="phone" class="contact_input" />
            </div>   
            
            <div class="form_row">
            <label>Credit Card Number:</label><input type="text" name="creditcard" class="contact_input" />
            </div>   
            
            <div class="form_row">
            <label>E-mail(username):</label><input type="text" name="email" class="contact_input" />
            </div>   
            
            <div class="form_row">
            <label>Password:</label><input type="text" name="password" class="contact_input" />
            </div>
            
		<div class="send_button"><input type="submit" value="Send Confirmation Email" /></div>               
		</form>
        </div>



<jsp:include page="/footer.jsp"/>