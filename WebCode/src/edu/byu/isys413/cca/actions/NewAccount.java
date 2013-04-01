package edu.byu.isys413.cca.actions;

import java.math.BigInteger;
import java.util.Random;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import edu.byu.isys413.cca.web.*;
import edu.byu.isys413.lifegood.*;

public class NewAccount implements Action {

	@Override
	public String process(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession(true);

		for (String name : new String[] { "firstname", "lastname",
				"middlename", "address", "city", "state", "zip", "phone",
				"email", "password", "creditcard" }) {
			if (request.getParameter(name) == null
					|| request.getParameter(name).equals("")) {
				request.setAttribute("message", "Please fill in all Fields.");
				return "new_customer.jsp";
			}
		}
		// create customer and membership
		Customer cust = BusinessObjectDAO.getInstance().create("Customer");
		Memb membership = BusinessObjectDAO.getInstance().create("Memb");

		System.out.println("Hello World!");

		// set customer values
		cust.setFirstname((String) request.getParameter("firstname"));
		cust.setMiddlename((String) request.getParameter("middlename"));
		cust.setLastname((String) request.getParameter("lastname"));
		cust.setAddress((String) request.getParameter("address"));
		cust.setCity((String) request.getParameter("city"));
		cust.setState((String) request.getParameter("state"));
		cust.setZip((String) request.getParameter("zip"));
		cust.setPhone((String) request.getParameter("phone"));
		cust.setEmail((String) request.getParameter("email"));
		cust.setUsername((String) request.getParameter("email"));
		cust.setPassword((String) request.getParameter("password"));

		// set Membership Values
		membership.setCreditcardnumber((String) request
				.getParameter("creditcard"));
		membership.setCustid(cust.getId());

		// Generate and set customer validation code
		Random random = new Random();
		String ValCode = new BigInteger(32, random).toString();

		cust.setValidationcode(ValCode);

		// Send Email

		Mail.send(
				"scottbrasil21@gmail.com",
				"My Stuff",
				cust.getEmail(),
				"Confirmation Link",
				"Thank you for creating your account your Validation Code is "
						+ ValCode
						+ ", please click here to validate your account \n\n         "
						+ "http://localhost:8080/WebCode/edu.byu.isys413.cca.actions.ConfirmationPage.action?vid="
						+ ValCode);

		// Save Customer And Membership
		BusinessObjectDAO.getInstance().save(cust);
		BusinessObjectDAO.getInstance().save(membership);

		return "waiting_page.jsp";
	}
}
