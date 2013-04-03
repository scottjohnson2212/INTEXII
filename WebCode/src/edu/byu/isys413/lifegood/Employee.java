

package edu.byu.isys413.lifegood;

import java.util.Date;

/**
 Employee
 */
public class Employee extends BusinessObject {

    @BusinessObjectField
    private String storeid = null;
    @BusinessObjectField
    private String firstname = null;
    @BusinessObjectField
    private String middlename = null;
    @BusinessObjectField
    private String lastname = null;
    @BusinessObjectField
    private Date hiredate = null;
    @BusinessObjectField
    private String phone = null;
    @BusinessObjectField
    private Double salary = null;
    @BusinessObjectField
    private String username = null;
    @BusinessObjectField
    private String password = null;
    
    
    //Commission Variable
    
    public Double amountToPay = 0.0;
    
    /**
	 * @return the amountToPay
	 */
	public void payEmployee(Employee e) {
		System.out.println("Employee:   "+e.getFirstname()+" "+e.getLastname()+ " Paid In Full Amount of: "+e.getAmountToPay());
	}

    /**
	 * @return the amountToPay
	 */
	public Double getAmountToPay() {
		return amountToPay;
	}


	/**
	 * @param amountToPay the amountToPay to set
	 */
	public void setAmountToPay(Double amountToPay) {
		this.amountToPay = amountToPay;
		this.setDirty();
	}


	/** Creates a new instance of BusinessObject */
    public Employee(String id) {
        super(id);
    }//constructor


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
	 * @return the hiredate
	 */
	public Date getHiredate() {
		return hiredate;
	}


	/**
	 * @param hiredate the hiredate to set
	 */
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
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
	 * @return the salary
	 */
	public Double getSalary() {
		return salary;
	}


	/**
	 * @param salary the salary to set
	 */
	public void setSalary(Double salary) {
		this.salary = salary;
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


}//class
