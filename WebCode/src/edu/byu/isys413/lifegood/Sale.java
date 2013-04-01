/**
 * 
 */
package edu.byu.isys413.lifegood;

/**
 * @author lifeisgood21
 *
 */
public class Sale extends RevenueSource{
	
	@BusinessObjectField
	private String productid;
	@BusinessObjectField
	private int quantity;
	
	private Product P;
	private ConceptualProduct CP;
	private PhysicalProduct PP;
	private StoreProduct SP;
	private ConceptualRental CR;

	
	/**
	 * new ID
	 */
	public Sale(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the productid
	 */
	public String getProductid() {
		return productid;
	}


	/**
	 * @param productid the productid to set
	 */
	public void setProductid(String productid) {
		this.productid = productid;
		this.setDirty();
	}


	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}


	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
		this.setDirty();
	}


	/**
	 * @return the p
	 */
	public Product getP() {
		return P;
	}


	/**
	 * @param p the p to set
	 */
	public void setP(Product p) {
		P = p;
		this.setDirty();
	}


	/**
	 * @return the cP
	 */
	public ConceptualProduct getCP() {
		return CP;
	}


	/**
	 * @param cP the cP to set
	 */
	public void setCP(ConceptualProduct cP) {
		CP = cP;
		this.setDirty();
	}


	/**
	 * @return the pP
	 */
	public PhysicalProduct getPP() {
		return PP;
	}


	/**
	 * @param pP the pP to set
	 */
	public void setPP(PhysicalProduct pP) {
		PP = pP;
		this.setDirty();
	}


	/**
	 * @return the sP
	 */
	public StoreProduct getSP() {
		return SP;
	}


	/**
	 * @param sP the sP to set
	 */
	public void setSP(StoreProduct sP) {
		SP = sP;
		this.setDirty();
	}


	/**
	 * @return the cR
	 */
	public ConceptualRental getCR() {
		return CR;
	}


	/**
	 * @param cR the cR to set
	 */
	public void setCR(ConceptualRental cR) {
		CR = cR;
		this.setDirty();
	}


	
}