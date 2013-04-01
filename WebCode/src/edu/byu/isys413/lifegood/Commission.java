/**
 * 
 */
package edu.byu.isys413.lifegood;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

/**
 * @author lifeisgood21
 *
 */
public class Commission extends BusinessObject{

	@BusinessObjectField
	private String transactionid;
	@BusinessObjectField
	private String employeeid;
	@BusinessObjectField
	private double amount;
	@BusinessObjectField
	private Date date;
	
	//Objects for Calculations
	private ArrayList<Commission> commList = null;
	private List<BusinessObject> boList = null;
	private TreeMap<String, Employee> empMap = null;
	
	public Commission(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Calculate Pay Month End Commissions
	 */
	public void calculateAndPayMonthEndCommissions() {
		
		//get Commissions List
		commList = new ArrayList<Commission>();
		
		try {
			boList = BusinessObjectDAO.getInstance().searchForAll("Commission");
		} catch (DataException e1) {
			System.out.println("Commission Failed!");
		}
		
		for (int i = 0; i < boList.size(); i++){
			try {
				commList.add((Commission) BusinessObjectDAO.getInstance().searchForBO("Commission", new SearchCriteria("id", boList.get(i).getId())));
			} catch (DataException e) {
				System.out.println("Commission List Didn't work");
			}
		}//for
		
		///////////////////////Get Employee Map//////////////////////////////////////////
		
		empMap = new TreeMap<String, Employee>();
		
		try {
			boList = BusinessObjectDAO.getInstance().searchForAll("Employee");
		} catch (DataException e1) {
			System.out.println("Employee Failed!");
		}
		
		for (int i = 0; i < boList.size(); i++){
			try {
				empMap.put("Employee" + i , (Employee) BusinessObjectDAO.getInstance().searchForBO("Employee", new SearchCriteria("id", boList.get(i).getId())));
			} catch (DataException e) {
				System.out.println("Commission List Didn't work");
			}
		}//for
		
		
	
		
		//Logic
		for(Employee e : empMap.values()){
			for(int i = 0; i < commList.size(); i++){
				String empid = commList.get(i).getEmployeeid();
					if(e.getId().equals(empid)){
						e.setAmountToPay(e.getAmountToPay() + commList.get(i).getAmount());
					}else{
						continue;
					}
			}
			e.payEmployee(e);
		}
		
		
		
		
	}


	/**
	 * @return the transactionid
	 */
	public String getTransactionid() {
		return transactionid;
	}


	/**
	 * @param transactionid the transactionid to set
	 */
	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
		this.setDirty();
	}


	/**
	 * @return the employeeid
	 */
	public String getEmployeeid() {
		return employeeid;
	}


	/**
	 * @param employeeid the employeeid to set
	 */
	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
		this.setDirty();
	}


	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}


	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
		this.setDirty();
	}


	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}


	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
		this.setDirty();
	}





}