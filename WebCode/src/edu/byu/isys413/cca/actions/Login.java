package edu.byu.isys413.cca.actions;

import edu.byu.isys413.cca.web.*;
import edu.byu.isys413.lifegood.*;

import javax.servlet.http.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login implements Action{

	 public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 HttpSession session = request.getSession(true);
		 
		 //get username and password, if one is blank send back null and keep on login screen
		 for(String name: new String[] {"username", "password"}){
			 if(request.getParameter(name) == null || request.getParameter(name).equals("")){
				 request.setAttribute("message", "Please fill in all Fields.");
				 return "login.jsp";
			 }
		 }
		 
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		LDAP ldap = new LDAP();
		
		Customer cust = BusinessObjectDAO.getInstance().searchForBO("Customer", new SearchCriteria("username", username), new SearchCriteria("password", password));
		
		
		//get customer from DB
		if(cust == null){
			request.setAttribute("message", "Login Failed Please Try Again");
			return "login.jsp";
		}
		
		
		LinkedList<BusinessObject> BOs =  (LinkedList<BusinessObject>) BusinessObjectDAO.getInstance().searchForAll("Store");
		 
	     //convert that list to an array list of conceptual products
	     LinkedList<Store> stores = new LinkedList<Store>();
	     
	     for(int i=0; i < BOs.size(); i++){
	    	
	    		Store store = (Store) BOs.get(i);
	    		stores.add(store);
	    	
	     }
		
		request.setAttribute("stores", stores);
		
        
         
        
		//set Session object (Log In Customer)
        session.setAttribute("userid", cust.getId());
		 
		 return "web_store.jsp";
	 }
}
