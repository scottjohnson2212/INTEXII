/**
 * 
 */
package edu.byu.isys413.lifegood;

/**
 * @author lifeisgood21
 *
 */
public class DebitCredit extends BusinessObject {

	@BusinessObjectField
	private String journalentryid;
	@BusinessObjectField
	private boolean debitb;
	@BusinessObjectField
	private String generalledgername;
	@BusinessObjectField
	private double amount;
	
	
	public DebitCredit(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the journalentryid
	 */
	public String getJournalentryid() {
		return journalentryid;
	}


	/**
	 * @param journalentryid the journalentryid to set
	 */
	public void setJournalentryid(String journalentryid) {
		this.journalentryid = journalentryid;
		this.setDirty();
	}


	/**
	 * @return the debitb
	 */
	public boolean isDebitb() {
		return debitb;
	}


	/**
	 * @param debitb the debitb to set
	 */
	public void setDebitb(boolean debitb) {
		this.debitb = debitb;
		this.setDirty();
	}


	/**
	 * @return the generalledgername
	 */
	public String getGeneralledgername() {
		return generalledgername;
	}


	/**
	 * @param generalledgername the generalledgername to set
	 */
	public void setGeneralledgername(String generalledgername) {
		this.generalledgername = generalledgername;
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
	
	

}
