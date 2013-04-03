
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
public class FinishTransaction implements Action{

	@Override
	public String process(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		
		
		//create things needed for finish and pay
		String storeName = request.getParameter("cust_store");
		String custEmail = request.getParameter("cust_email");
		String concprod = request.getParameter("product_sku");
		String physprod = request.getParameter("product_serialnumber");
		
		//create transaction and other objects needed
		Trans trans = BusinessObjectDAO.getInstance().create("Trans");
		Store STR = BusinessObjectDAO.getInstance().searchForBO("Store", new SearchCriteria("city", storeName));
		Customer cust = BusinessObjectDAO.getInstance().searchForBO("Customer", new SearchCriteria("email", custEmail));;
		Employee EMP = BusinessObjectDAO.getInstance().searchForBO("Employee", new SearchCriteria("id", STR.getManagerid()));
		RevenueSource RevSour = BusinessObjectDAO.getInstance().create("RevenueSource");
		Date today = new Date();
		trans.setEmployee(EMP);
		trans.setStore(STR);
		trans.setCustomer(cust);
		
		
		
		PhysicalProduct PP = null;
		ConceptualProduct CP = null;
		
		if(concprod == null){
			PP = BusinessObjectDAO.getInstance().searchForBO("PhysicalProduct", new SearchCriteria("serialnumber", physprod));
		}else{
			CP = BusinessObjectDAO.getInstance().searchForBO("ConceptualProduct", new SearchCriteria("sku", concprod));
		}
		
		//add variables to the transaction object
		
		double subtotal = Double.parseDouble(request.getParameter("product_subtotal"));
		double tax = Double.parseDouble(request.getParameter("product_tax"));
		double total = Double.parseDouble(request.getParameter("product_total"));
		
		
		trans.setStoreid(STR.getId());
		trans.setCustomerid(cust.getId());
		trans.setEmployeeid(EMP.getId());
		trans.setDate(today);
		trans.setSubtotal(subtotal);
		trans.setTax(tax);
		trans.setTotal(total);
		
		//add variables to the Revenue Source
		
		RevSour.setTransactionid(trans.getId());
		RevSour.setAmount(total);
		RevSour.setType("sale");
		
			//create Sale and add to Sale List then put into transaction
			ArrayList<Sale> saleList = new ArrayList<Sale>();
			Sale sale = BusinessObjectDAO.getInstance().create("Sale");
			if(PP == null){
				StoreProduct SP = BusinessObjectDAO.getInstance().searchForBO("StoreProduct", new SearchCriteria("conceptualproductid", CP.getId()), new SearchCriteria("storeid", STR.getId()));
				String quantityOrdered = request.getParameter("product_quantity");
				sale.setProductid(CP.getId());
				sale.setQuantity(Integer.parseInt(quantityOrdered));
				sale.setP(CP);
				sale.setPP(null);
				sale.setCP(CP);
				sale.setSP(SP);
				
				saleList.add(sale);
				
			}else{
				
				Product P = (Product) BusinessObjectDAO.getInstance().searchForBO("Product", new SearchCriteria("id", PP.getId()));
				
				sale.setProductid(PP.getId());
				sale.setQuantity(1);
				sale.setP(P);
				sale.setPP(PP);
				sale.setCP(null);
				sale.setSP(null);
				saleList.add(sale);
			}
		RevSour.setSaleList(saleList);
		
		ArrayList<RevenueSource> RevSours = new ArrayList<RevenueSource>();
		RevSours.add(RevSour);
		trans.setRevenueSourceList(RevSours);
		
		//Call Finish and Pay
		
		try{
		trans.finishAndPay();
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		
		
		
		
		
		return "finish_transaction.jsp";
	}
	
	
	
	
	
	
	

}
