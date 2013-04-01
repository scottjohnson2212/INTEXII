/**
 * 
 */
package edu.byu.isys413.lifegood;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author lifeisgood21
 *
 */
public class JournalEntry extends BusinessObject{

	@BusinessObjectField
	private String transactionid;
	@BusinessObjectField
	private Date date;
	
	//DebitCredit List
	private ArrayList<DebitCredit> debitCreditList = null;
	
	
	/**
	 * @return the debitCreditList
	 */
	public ArrayList<DebitCredit> getDebitCreditList() {
		return debitCreditList;
	}


	/**
	 * @param debitCreditList the debitCreditList to set
	 */
	public void setDebitCreditList(ArrayList<DebitCredit> debitCreditList) {
		this.debitCreditList = debitCreditList;
		this.setDirty();
	}


	public JournalEntry(String id) {
		super(id);
		// TODO Auto-generated constructor stub
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
