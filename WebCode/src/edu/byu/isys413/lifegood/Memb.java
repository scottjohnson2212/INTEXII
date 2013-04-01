/**
 * 
 */
package edu.byu.isys413.lifegood;

import java.util.Date;

/**
 * @author lifeisgood21
 * 
 */
public class Memb extends BusinessObject {

	@BusinessObjectField
	private String custid;
	@BusinessObjectField
	private String creditcardnumber;

	/**
	 * generate the custid
	 */
	public Memb(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the custid
	 */
	public String getCustid() {
		return custid;
	}

	/**
	 * @param custid
	 *            the custid to set
	 */
	public void setCustid(String custid) {
		this.custid = custid;
		this.setDirty();
	}

	/**
	 * @return the creditcardnumber
	 */
	public String getCreditcardnumber() {
		return creditcardnumber;
	}

	/**
	 * @param creditcardnumber
	 *            the creditcardnumber to set
	 */
	public void setCreditcardnumber(String creditcardnumber) {
		this.creditcardnumber = creditcardnumber;
		this.setDirty();
	}

}
