/**
 * 
 */
package edu.byu.isys413.lifegood;

/**
 * @author lifeisgood21
 *
 */
public class Payment extends BusinessObject {

	@BusinessObjectField
	private String transactionid;
	@BusinessObjectField
	private double chargeamount;
	@BusinessObjectField
	private String type;
	
	public Payment(String id) {
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
	 * @return the chargeamount
	 */
	public double getChargeamount() {
		return chargeamount;
	}

	/**
	 * @param chargeamount the chargeamount to set
	 */
	public void setChargeamount(double chargeamount) {
		this.chargeamount = chargeamount;
		this.setDirty();
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
		this.setDirty();
	}
	
	

}
