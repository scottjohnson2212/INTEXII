/**
 * 
 */
package edu.byu.isys413.cca.actions;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.byu.isys413.cca.web.*;
import edu.byu.isys413.lifegood.*;

/**
 * @author lifeisgood21
 *
 */
public class PurchasePage implements Action{

	@Override
	public String process(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		String sku = (String) request.getParameter("product_sku");
		String storeName = request.getParameter("product_store");
		
		request.setAttribute("product_store", storeName);
		//get conceptual product and store
		ConceptualProduct CP = BusinessObjectDAO.getInstance().searchForBO("ConceptualProduct", new SearchCriteria("sku", sku));
		Store STR = BusinessObjectDAO.getInstance().searchForBO("Store", new SearchCriteria("city", storeName));
		StoreProduct SP = BusinessObjectDAO.getInstance().searchForBO("StoreProduct", new SearchCriteria("storeid", STR.getId()), new SearchCriteria("conceptualproductid",CP.getId()));
		//get physical product Options for purchase
		
		List<BusinessObject> BOs = BusinessObjectDAO.getInstance().searchForList("PhysicalProduct", new SearchCriteria("conceptualproductid", CP.getId()), new SearchCriteria("storeid", STR.getId()));
		
		ArrayList<PhysicalProduct> PPs = new ArrayList<PhysicalProduct>();
		for(int i=0; i < BOs.size(); i++){
	    		PhysicalProduct PP = (PhysicalProduct) BOs.get(i);
	    		PPs.add(PP);
	     }
		CP.getPrice();
		
		request.setAttribute("cproduct", "<div class='product_box'><form action='edu.byu.isys413.cca.actions.ConfirmPurchase.action'><div class='form_row'><label>Product Name:</label> <input type='text' name='product_name'value='"+CP.getProductname()+"' readonly></div>" +
											"<div class='form_row'><label>Product SKU:</label> <input type='text' name='product_sku' value='"+CP.getSku()+"' readonly></div>" +
											"<div class='form_row'><label>Price:</label> <input type='text' name='product_price' value='"+Double.toString(CP.getPrice())+"' readonly></div>" +
											"<div class='form_row'><label>Select Quantity:</label> <input type='text' name='product_quantity'></div>" +
											"<div class='form_row'><label>Enter Email To Purchase:</label> <input type='text' name='cust_email'></div>" +
											"<div class='form_row'><label>Store:</label> <input type='text' name='store_name' value='"+storeName+"' readonly></div>" +
											"<input type='submit' value='Purchase' /></form></div>");
		
		//create html page from scratch
		
		for(int i = 0; i < PPs.size(); i++){
			request.setAttribute("pproduct"+i, "<div class='product_box'><form action='edu.byu.isys413.cca.actions.ConfirmPurchase.action'><div class='form_row'><label>Product Name:</label> <input type='text' name='product_name'value='"+PPs.get(i).getProductname()+"' readonly></div>" +
					"<div class='form_row'><label>Product Price:</label> <input type='text' name='product_price'value='"+PPs.get(i).getPrice()+"' readonly></div>" +
							"<div class='form_row'><label>Serial Number:</label> <input type='text' name='product_serialnumber'value='"+PPs.get(i).getSerialnumber()+"' readonly></div>" +
									"<div class='form_row'><label>Enter Email To Purchase:</label> <input type='text' name='cust_email'></div>" +
									"<div class='form_row'><label>Store:</label> <input type='text' name='store_name' value='"+storeName+"' readonly></div>" +
									"<input type='submit' value='Purchase' /></form></div>");
			
		}	
		
		request.setAttribute("count", PPs.size());
		request.setAttribute("none", "");
		
		return "purchase_page.jsp";
	}

}
