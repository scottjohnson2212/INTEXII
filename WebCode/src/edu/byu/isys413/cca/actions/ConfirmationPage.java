
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
public class ConfirmationPage implements Action{

	@Override
	public String process(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession(true);
		
		Customer cust = BusinessObjectDAO.getInstance().searchForBO("customer", new SearchCriteria("validationcode",request.getParameter("vid")));
		
		cust.setValidated(true);
		
		session.setAttribute("userid", cust.getId());
		
		return "login.jsp";
	}

}
