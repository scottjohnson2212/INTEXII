package edu.byu.isys413.lifegood;

/**
 * @author lifeisgood21
 *
 */
public class ConceptualRental extends ConceptualProduct{
	
	@BusinessObjectField
	private double priceperday;
	@BusinessObjectField
	private double replacementprice;
	
	

	public ConceptualRental(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the priceperday
	 */
	public double getPriceperday() {
		return priceperday;
	}



	/**
	 * @param priceperday the priceperday to set
	 */
	public void setPriceperday(double priceperday) {
		this.priceperday = priceperday;
		this.setDirty();
	}



	/**
	 * @return the replacementprice
	 */
	public double getReplacementprice() {
		return replacementprice;
	}



	/**
	 * @param replacementprice the replacementprice to set
	 */
	public void setReplacementprice(double replacementprice) {
		this.replacementprice = replacementprice;
		this.setDirty();
	}

}
