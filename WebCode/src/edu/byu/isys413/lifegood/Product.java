/**
 * 
 */
package edu.byu.isys413.lifegood;

/**
 * @author lifeisgood21
 *
 */
public abstract class Product extends BusinessObject{
	
	@BusinessObjectField
	private String type;
	@BusinessObjectField
	private double price;
	
	double fullcommissionrate = 0;
	
	/**
	 * Get the full commission Rate
	 */
	public abstract double getFullCommissionRate();
	
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
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}


	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
		this.setDirty();
	}


	public Product(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	

}
