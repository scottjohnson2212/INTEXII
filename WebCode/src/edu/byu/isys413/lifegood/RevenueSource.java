/**
 * 
 */
package edu.byu.isys413.lifegood;

import java.util.ArrayList;

/**
 * @author lifeisgood21
 *
 */
public class RevenueSource extends BusinessObject{
	
	@BusinessObjectField
	private String transactionid;
	@BusinessObjectField
	private double amount;
	@BusinessObjectField
	private String type;
	
	
	ArrayList<Sale> saleList = null;
	ArrayList<Fee> feeList = null;
	ArrayList<Rental> rentalList = null;
	
	/**
	 * @return the saleList
	 */
	public ArrayList<Sale> getSaleList() {
		return saleList;
	}



	/**
	 * @param saleList the saleList to set
	 */
	public void setSaleList(ArrayList<Sale> saleList) {
		this.saleList = saleList;
		this.setDirty();
	}



	public RevenueSource(String id) {
		super(id);
		// TODO Auto-generated constructor stub
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
	 * @return the feeList
	 */
	public ArrayList<Fee> getFeeList() {
		return feeList;
	}



	/**
	 * @param feeList the feeList to set
	 */
	public void setFeeList(ArrayList<Fee> feeList) {
		this.feeList = feeList;
		this.setDirty();
	}



	/**
	 * @return the rentalList
	 */
	public ArrayList<Rental> getRentalList() {
		return rentalList;
	}



	/**
	 * @param rentalList the rentalList to set
	 */
	public void setRentalList(ArrayList<Rental> rentalList) {
		this.rentalList = rentalList;
		this.setDirty();
	}

	

}
