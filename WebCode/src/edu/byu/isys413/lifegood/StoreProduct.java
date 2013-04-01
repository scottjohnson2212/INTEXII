/**
 * 
 */
package edu.byu.isys413.lifegood;

/**
 * @author lifeisgood21
 *
 */
public class StoreProduct extends BusinessObject{
	
	@BusinessObjectField
	private String storeid;
	@BusinessObjectField
	private String conceptualproductid;
	@BusinessObjectField
	private int quantityonhand;
	
	public StoreProduct(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the storeid
	 */
	public String getStoreid() {
		return storeid;
	}

	/**
	 * @param storeid the storeid to set
	 */
	public void setStoreid(String storeid) {
		this.storeid = storeid;
		this.setDirty();
	}

	/**
	 * @return the conceptualproductid
	 */
	public String getConceptualproductid() {
		return conceptualproductid;
	}

	/**
	 * @param conceptualproductid the conceptualproductid to set
	 */
	public void setConceptualproductid(String conceptualproductid) {
		this.conceptualproductid = conceptualproductid;
		this.setDirty();
	}

	/**
	 * @return the quantityonhand
	 */
	public int getQuantityonhand() {
		return quantityonhand;
	}

	/**
	 * @param quantityonhand the quantityonhand to set
	 */
	public void setQuantityonhand(int quantityonhand) {
		this.quantityonhand = quantityonhand;
		this.setDirty();
	}


}

