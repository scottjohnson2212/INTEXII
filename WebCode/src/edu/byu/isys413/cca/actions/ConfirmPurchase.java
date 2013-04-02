/**
 * 
 */
package edu.byu.isys413.cca.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.byu.isys413.cca.web.*;
import edu.byu.isys413.lifegood.*;

/**
 * @author lifeisgood21
 *
 */
public class ConfirmPurchase implements Action{

	@Override
	public String process(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String custEmail = (String) request.getParameter("cust_email");
		
		System.out.println(request.getParameter("store_name"));
		
		
		String storename = (String) request.getParameter("store_name");
		
		
		//get customer information
		
		Customer cust = BusinessObjectDAO.getInstance().searchForBO("Customer", new SearchCriteria("email", custEmail));
	
		//get purchase information
		
		request.setAttribute("store", request.getParameter("product_store"));
		
		if(request.getParameter("product_sku") == null){
//			    PHYSICAL PRODUCT
//			product_name=Ipad
//			product_price=128.0
//			product_serialnumber=BEX93RVN5VS
//			cust_email=scott.johnson2212%40gmail.com
			String serialnum = request.getParameter("product_serialnumber");

			PhysicalProduct PP = BusinessObjectDAO.getInstance().searchForBO("PhysicalProduct", new SearchCriteria("serialnumber", serialnum));
			
			double subtotal = PP.getPrice();
			
			double tax = subtotal * .06;
			
			double total = subtotal + tax;
			
			request.setAttribute("physical_product", "<div class='product_box'><form action='edu.byu.isys413.cca.actions.FinishTransaction.action'>" +
											"<div class='form_row'><label>First Name:</label> <input type='text' name='cust_firstname'value='"+cust.getFirstname()+"' readonly></div>" +
											"<div class='form_row'><label>Middle Name:</label> <input type='text' name='cust_middlename' value='"+cust.getMiddlename()+"' readonly></div>" +
											"<div class='form_row'><label>Last Name:</label> <input type='text' name='cust_lastname' value='"+cust.getLastname()+"' readonly></div>" +
											"<div class='form_row'><label>Phone:</label> <input type='text' name='cust_phone' value='"+cust.getPhone()+"' readonly></div>" +
											"<div class='form_row'><label>Shipping Address:</label> <input type='text' name='cust_address' value='"+cust.getAddress()+"' readonly></div>" +
											"<div class='form_row'><label>City:</label> <input type='text' name='cust_city' value='"+cust.getCity()+"' readonly></div>" +
											"<div class='form_row'><label>State:</label> <input type='text' name='cust_state' value='"+cust.getState()+"' readonly></div>" +
											"<div class='form_row'><label>Zip:</label> <input type='text' name='cust_zip' value='"+cust.getZip()+"' readonly></div>" +
											"<div class='form_row'><label>Email:</label> <input type='text' name='cust_email' value='"+cust.getEmail()+"' readonly></div>" +
											"<div class='form_row'><label>Store:</label> <input type='text' name='cust_store' value='"+storename+"' readonly></div>"+
											"<div class='form_row'><label>Product Name:</label> <input type='text' name='product_name'value='"+PP.getProductname()+"' readonly></div>" +
											"<div class='form_row'><label>Serial Number:</label> <input type='text' name='product_serialnumber' value='"+PP.getSerialnumber()+"' readonly></div>" +
											"<div class='form_row'><label>Price:</label> <input type='text' name='product_price' value='"+PP.getPrice()+"' readonly></div>" +
											"<div class='form_row'><label>Subtotal:</label> <input type='text' name='product_subtotal' value='"+subtotal+"' readonly></div>" +
											"<div class='form_row'><label>Tax:</label> <input type='text' name='product_tax' value='"+tax+"' readonly></div>" +
											"<div class='form_row'><label>Total:</label> <input type='text' name='product_total' value='"+total+"' readonly></div>" +
											"<input type='submit' value='Purchase' /></form></div>");
			
		}else{
//			CONCEPTUAL PRODUCT
//		product_name=Ipad
//		product_sku=8I0D3A1W4
//		product_price=40.0
//		product_quantity=1
//		cust_email=scott.johnson2212%40gmail.com
			String sku = request.getParameter("product_sku");
			
			ConceptualProduct CP = BusinessObjectDAO.getInstance().searchForBO("ConceptualProduct", new SearchCriteria("sku", sku));
			
			double subtotal = CP.getPrice() * Integer.parseInt((String) request.getParameter("product_quantity"));
			
			double tax = subtotal * .06;
			
			double total = subtotal + tax;
			
			
			
			request.setAttribute("conceptual_product", "<div class='product_box'><form action='edu.byu.isys413.cca.actions.FinishTransaction.action'>" +
											"<div class='form_row'><label>First Name:</label> <input type='text' name='cust_firstname'value='"+cust.getFirstname()+"' readonly></div>" +
											"<div class='form_row'><label>Middle Name:</label> <input type='text' name='cust_middlename' value='"+cust.getMiddlename()+"' readonly></div>" +
											"<div class='form_row'><label>Last Name:</label> <input type='text' name='cust_lastname' value='"+cust.getLastname()+"' readonly></div>" +
											"<div class='form_row'><label>Phone:</label> <input type='text' name='cust_phone' value='"+cust.getPhone()+"' readonly></div>" +
											"<div class='form_row'><label>Shipping Address:</label> <input type='text' name='cust_address' value='"+cust.getAddress()+"' readonly></div>" +
											"<div class='form_row'><label>City:</label> <input type='text' name='cust_city' value='"+cust.getCity()+"' readonly></div>" +
											"<div class='form_row'><label>State:</label> <input type='text' name='cust_state' value='"+cust.getState()+"' readonly></div>" +
											"<div class='form_row'><label>Zip:</label> <input type='text' name='cust_zip' value='"+cust.getZip()+"' readonly></div>" +
											"<div class='form_row'><label>Email:</label> <input type='text' name='cust_email' value='"+cust.getEmail()+"' readonly></div>" +
											"<div class='form_row'><label>Store:</label> <input type='text' name='cust_store' value='"+storename+"' readonly></div>" +
											"<div class='form_row'><label>Product Name:</label> <input type='text' name='product_name' value='"+CP.getProductname()+"' readonly></div>" +
											"<div class='form_row'><label>Quantity Ordered:</label> <input type='text' name='product_quantity' value='"+request.getParameter("product_quantity")+"' readonly></div>" +
											"<div class='form_row'><label>Serial Number:</label> <input type='text' name='product_sku' value='"+CP.getSku()+"' readonly></div>" +
											"<div class='form_row'><label>Subtotal:</label> <input type='text' name='product_subtotal' value='"+subtotal+"' readonly></div>" +
											"<div class='form_row'><label>Tax:</label> <input type='text' name='product_tax' value='"+tax+"' readonly></div>" +
											"<div class='form_row'><label>Total:</label> <input type='text' name='product_total' value='"+total+"' readonly></div>" +
											"<input type='submit' value='Purchase' /></form></div>");
		}

		return "confirmation_page.jsp";
		
	}

}
