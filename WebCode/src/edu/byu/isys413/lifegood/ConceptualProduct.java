/**
 * 
 */
package edu.byu.isys413.lifegood;

/**
 * @author lifeisgood21
 * 
 */
public class ConceptualProduct extends Product {

	@BusinessObjectField
	private String productname;
	@BusinessObjectField
	private String description;
	@BusinessObjectField
	private String manufacturer;
	@BusinessObjectField
	private double averagecost;
	@BusinessObjectField
	private double commissionrate;
	@BusinessObjectField
	private String sku;
	
	
	
	/**
	 * constructor
	 */
	public ConceptualProduct(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getFullCommissionRate() {
		return this.getCommissionrate();
	}



	/**
	 * @return the productname
	 */
	public String getProductname() {
		return productname;
	}



	/**
	 * @param productname the productname to set
	 */
	public void setProductname(String productname) {
		this.productname = productname;
		this.setDirty();
	}



	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}



	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
		this.setDirty();
	}



	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}



	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
		this.setDirty();
	}



	/**
	 * @return the averagecost
	 */
	public double getAveragecost() {
		return averagecost;
	}



	/**
	 * @param averagecost the averagecost to set
	 */
	public void setAveragecost(double averagecost) {
		this.averagecost = averagecost;
		this.setDirty();
	}



	/**
	 * @return the commissionrate
	 */
	public double getCommissionrate() {
		return commissionrate;
	}



	/**
	 * @param commissionrate the commissionrate to set
	 */
	public void setCommissionrate(double commissionrate) {
		this.commissionrate = commissionrate;
		this.setDirty();
	}



	/**
	 * @return the sku
	 */
	public String getSku() {
		return sku;
	}



	/**
	 * @param sku the sku to set
	 */
	public void setSku(String sku) {
		this.sku = sku;
		this.setDirty();
	}

	public void addConceptualProduct() {
		
		
	}

	
	

}
