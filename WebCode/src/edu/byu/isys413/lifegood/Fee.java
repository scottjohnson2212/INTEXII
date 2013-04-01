/**
 * 
 */
package edu.byu.isys413.lifegood;

/**
 * @author lifeisgood21
 *
 */
public class Fee extends RevenueSource {
	
	@BusinessObjectField
	private String rentalid;
	@BusinessObjectField
	private double amount;

	public Fee(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the rentalid
	 */
	public String getRentalid() {
		return rentalid;
	}

	/**
	 * @param rentalid the rentalid to set
	 */
	public void setRentalid(String rentalid) {
		this.rentalid = rentalid;
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
