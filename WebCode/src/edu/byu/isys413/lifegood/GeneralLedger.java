/**
 * 
 */
package edu.byu.isys413.lifegood;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @author lifeisgood21
 *
 */
public class GeneralLedger extends BusinessObject{
	
	@BusinessObjectField
	private String accountname;
	@BusinessObjectField
	private double balance;
	
	//Objects
	private TreeMap<String, GeneralLedger> generalLedger = null;
	private ArrayList<DebitCredit> debCredList = null;
	private List<BusinessObject> boList = null;
	
	public GeneralLedger(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	
	
	public void updateGeneralLedger(){
		generalLedger = new TreeMap<String, GeneralLedger>();
		GeneralLedger C = null, SR = null, CE=null, STP = null, CP= null;
		try {
			C = BusinessObjectDAO.getInstance().searchForBO("GeneralLedger", new SearchCriteria("accountname", "Cash"));
			SR = BusinessObjectDAO.getInstance().searchForBO("GeneralLedger", new SearchCriteria("accountname", "Sales Revenue"));
			CE = BusinessObjectDAO.getInstance().searchForBO("GeneralLedger", new SearchCriteria("accountname", "Commissions Expense"));
			STP = BusinessObjectDAO.getInstance().searchForBO("GeneralLedger", new SearchCriteria("accountname", "Sales Tax Payable"));
			CP = BusinessObjectDAO.getInstance().searchForBO("GeneralLedger", new SearchCriteria("accountname", "Commissions Payable"));
		} catch (DataException e) {
			System.out.println("Copy of General Ledger Failed!");
		}
		// Create General Ledger Map
		generalLedger.put("Cash", C);
		generalLedger.put("SalRev", SR);
		generalLedger.put("ComExp", CE);
		generalLedger.put("SalTaxPay", STP);
		generalLedger.put("ComPay", CP);
		
		
		//Create List of Debit Credits
		debCredList = new ArrayList<DebitCredit>();
		
		try {
			boList = BusinessObjectDAO.getInstance().searchForAll("DebitCredit");
		} catch (DataException e1) {
			System.out.println("Debits and Credits Failed!");
		}
		
		for (int i = 0; i < boList.size(); i++){
			DebitCredit DC = null;
			try {
				debCredList.add((DebitCredit) BusinessObjectDAO.getInstance().searchForBO("DebitCredit", new SearchCriteria("id", boList.get(i).getId())));
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}//for
		//loop through and apply debit credits
		for (int i = 0; i < debCredList.size(); i++){
			String glname = debCredList.get(i).getGeneralledgername();
				for(GeneralLedger gl :generalLedger.values()){
					String name = gl.getAccountname();
					if(glname.equals(name)){
						
						if(gl.getAccountname().contains("Expense") || gl.getAccountname().contains("Cash")){
								if(debCredList.get(i).isDebitb()){//////////////////////////////////////////////////////////////////////
									gl.setBalance(gl.getBalance() + debCredList.get(i).getAmount());
								}else{
									gl.setBalance(gl.getBalance() - debCredList.get(i).getAmount());
								}

						}else{/////////////////////////////////////////////////
							if(debCredList.get(i).isDebitb()){
								gl.setBalance(gl.getBalance() - debCredList.get(i).getAmount());
							}else{
								gl.setBalance(gl.getBalance() + debCredList.get(i).getAmount());
							}
							
						}////////////////////////////////////////////////////////
					}else{
						continue;
					}
				}//inner For
		}//outer for
		
		
		//Save General Ledger to the Database
		for (GeneralLedger gl : generalLedger.values()){
			try {
				BusinessObjectDAO.getInstance().save(gl);
			} catch (DataException e) {
				System.out.println("Failed to Save General Ledger!!!");
			}
		}
	}
	
	

	/**
	 * @return the accountname
	 */
	public String getAccountname() {
		return accountname;
	}

	/**
	 * @param accountname the accountname to set
	 */
	public void setAccountname(String accountname) {
		this.accountname = accountname;
		this.setDirty();
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
		this.setDirty();
	}
	

}
