/**
 * 
 */
package edu.byu.isys413.cca.actions;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.byu.isys413.cca.web.*;
import edu.byu.isys413.lifegood.*;

/**
 * @author lifeisgood21
 *
 */
public class WebStore implements Action {

	@Override
	public String process(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//----------------------------------------------PRODUCTS ARRAY
		JSONArray productsJSONArray = new JSONArray();

		
		
		
	//--------------------------------------------------store parameter and Search Parameters
		String storeParm = request.getParameter("store_parm");
		String searchParm = request.getParameter("search_parm");

		Store store = BusinessObjectDAO.getInstance().searchForBO("Store", new SearchCriteria("city", storeParm));

	//----------------------------------------------get a list of corresponding business objects
		List<BusinessObject> BOs =  BusinessObjectDAO.getInstance().searchForList("ConceptualProduct", new SearchCriteria("productname", "%"+searchParm+"%", SearchCriteria.LIKE));
 
     //convert that list to an array list of conceptual products
     ArrayList<ConceptualProduct> CPs = new ArrayList<ConceptualProduct>();
     
     for(int i=0; i < BOs.size(); i++){
    	String classname = BOs.get(i).getClass().toString();
    	if(classname.contains("ConceptualRental")){
    		continue;
    	}else{
    		ConceptualProduct CP = (ConceptualProduct) BOs.get(i);
    		CPs.add(CP);
    	}
    	
    	 
     }
System.out.println("ConceptualProducts: "+CPs.size());

     	request.setAttribute("products", CPs);
     
     for(int i=0; i < CPs.size(); i++){	
     	
    	 JSONObject j = new JSONObject();
    	 
    	 
    	 j.put("sku", CPs.get(i).getSku());
     	//Get out CONCEPTUAL PRODUCT ATTRS
		j.put("product_name", CPs.get(i).getProductname());	
		j.put("product_description", CPs.get(i).getDescription());
		j.put("product_price_new", CPs.get(i).getPrice());
		
		String cpId = CPs.get(i).getId();
		
		//Get out PHYSICAL PRODUCT ATTRS
		PhysicalProduct PP = BusinessObjectDAO.getInstance().searchForBO("PhysicalProduct", new SearchCriteria("conceptualproductid", cpId), new SearchCriteria("storeid",store.getId()));
		if(PP == null){
			j.put("product_price_used", 0);
		}else{
			j.put("product_price_used", PP.getPrice());
		}

		//Get out STORE PRODUCT ATTRS
		StoreProduct SP = BusinessObjectDAO.getInstance().searchForBO("StoreProduct", new SearchCriteria("conceptualproductid", CPs.get(i).getId()));
		
		j.put("product_qty_new", SP.getQuantityonhand());

System.out.println("Quantity new      : "+SP.getQuantityonhand());


		

		
		//get Count of Inventory!
		List<PhysicalProduct> PPList = BusinessObjectDAO.getInstance().searchForList("PhysicalProduct", 
				new SearchCriteria("productname", PP.getProductname()),
				new SearchCriteria("storeid", store.getId()),
				new SearchCriteria("status", "Active"));
		int quantity;

		if(PPList == null){
			quantity = 0;
		}else{
			quantity = PPList.size();
		}
		
		
		j.put("product_qty_used", quantity);
		j.put("store", store.getCity());

System.out.println("Quantity Used      : "+Integer.toString(quantity));

		productsJSONArray.put(j);
		
     }//end for loop for all products
     
     //create new Object
     JSONObject nj = new JSONObject();
     nj.put("products", productsJSONArray);
     request.setAttribute("products", nj);
     
     request.setAttribute("store", store.getCity());
     
		return "webstore_json.jsp";
	}

}
