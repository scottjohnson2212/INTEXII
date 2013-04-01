/**
 * 
 */
package edu.byu.isys413.lifegood;

/**
 * @author lifeisgood21
 *
 */
public class Customer extends BusinessObject {

	
	@BusinessObjectField
	private String firstname;
	@BusinessObjectField
	private String middlename;
	@BusinessObjectField
	private String lastname;
	@BusinessObjectField
	private String phone;
	@BusinessObjectField
	private String address;
	@BusinessObjectField
	private String city;
	@BusinessObjectField
	private String state;
	@BusinessObjectField
	private String zip;
	@BusinessObjectField
	private String email;
	@BusinessObjectField
	private String username;
	@BusinessObjectField
	private String password;
	@BusinessObjectField
	private String validationcode;
	@BusinessObjectField
	private boolean validated;
	
	
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
		this.setDirty();
	}


	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}


	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
		this.setDirty();
	}


	public Customer(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}


	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
		this.setDirty();
	}


	/**
	 * @return the middlename
	 */
	public String getMiddlename() {
		return middlename;
	}


	/**
	 * @param middlename the middlename to set
	 */
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
		this.setDirty();
	}


	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}


	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
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


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
		this.setDirty();
	}


	/**
	 * @return the validationcode
	 */
	public String getValidationcode() {
		return validationcode;
	}


	/**
	 * @param validationcode the validationcode to set
	 */
	public void setValidationcode(String validationcode) {
		this.validationcode = validationcode;
		this.setDirty();
	}


	/**
	 * @return the validated
	 */
	public boolean isValidated() {
		return validated;
	}


	/**
	 * @param validated the validated to set
	 */
	public void setValidated(boolean validated) {
		this.validated = validated;
		this.setDirty();
	}

	
	
}
