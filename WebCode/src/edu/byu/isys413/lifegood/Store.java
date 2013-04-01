/**
 * 
 */
package edu.byu.isys413.lifegood;

/**
 * @author lifeisgood21
 *
 */
public class Store extends BusinessObject{
	
	@BusinessObjectField
	private String managerid = null;
	@BusinessObjectField
	private String phone = null;
	@BusinessObjectField
	private String address = null;
	@BusinessObjectField
	private String city = null;
	@BusinessObjectField
	private String state = null;
	@BusinessObjectField
	private String zip = null;
	
	
	/** Creates a new instance of BusinessObject */
	public Store(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the managerid
	 */
	public String getManagerid() {
		return managerid;
	}


	/**
	 * @param managerid the managerid to set
	 */
	public void setManagerid(String managerid) {
		this.managerid = managerid;
		this.setDirty();
	}


	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}


	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
		this.setDirty();
	}


	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}


	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
		this.setDirty();
	}


	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}


	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
		this.setDirty();
	}


	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}


	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
		this.setDirty();
	}


	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}


	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
		this.setDirty();
	}

}
