/**
 * 
 */
package edu.byu.isys413.lifegood;

import java.util.Date;

/**
 * @author lifeisgood21
 *
 */
public class PhysicalProduct extends Product{
	
	@BusinessObjectField
	private String storeid;
	@BusinessObjectField
	private String conceptualproductid;
	@BusinessObjectField
	private String productname;
	@BusinessObjectField
	private String serialnumber;
	@BusinessObjectField
	private String shelflocation;
	@BusinessObjectField
	private Date datepurchased;
	@BusinessObjectField
	private double cost;
	@BusinessObjectField
	private String status;
	@BusinessObjectField
	private String type;
	@BusinessObjectField
	private double physicalproductcommissionrate;
	
	/**
	 * constructor
	 */
	public PhysicalProduct(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * get Full commission rate
	 */
	public double getFullCommissionRate(){
		ConceptualProduct CP = null;
		try {
			CP = BusinessObjectDAO.getInstance().searchForBO("ConceptualProduct", new SearchCriteria("id", this.getConceptualproductid()));
		} catch (Exception e) {
			System.out.println("Failed to get Conceptual Product Associated with This Physical Product "+ this.getId());
		}		
		fullcommissionrate = CP.getCommissionrate() + this.getPhysicalproductcommissionrate();
		return fullcommissionrate;
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
	 * @return the serialnumber
	 */
	public String getSerialnumber() {
		return serialnumber;
	}

	/**
	 * @param serialnumber the serialnumber to set
	 */
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
		this.setDirty();
	}

	/**
	 * @return the shelflocation
	 */
	public String getShelflocation() {
		return shelflocation;
	}

	/**
	 * @param shelflocation the shelflocation to set
	 */
	public void setShelflocation(String shelflocation) {
		this.shelflocation = shelflocation;
		this.setDirty();
	}

	/**
	 * @return the datepurchased
	 */
	public Date getDatepurchased() {
		return datepurchased;
	}

	/**
	 * @param datepurchased the datepurchased to set
	 */
	public void setDatepurchased(Date datepurchased) {
		this.datepurchased = datepurchased;
		this.setDirty();
	}

	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(double cost) {
		this.cost = cost;
		this.setDirty();
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * @return the physicalproductcommissionrate
	 */
	public double getPhysicalproductcommissionrate() {
		return physicalproductcommissionrate;
	}

	/**
	 * @param physicalproductcommissionrate the physicalproductcommissionrate to set
	 */
	public void setPhysicalproductcommissionrate(
			double physicalproductcommissionrate) {
		this.physicalproductcommissionrate = physicalproductcommissionrate;
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


}
