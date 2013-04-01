/**
 * 
 */
package edu.byu.isys413.lifegood;

import java.util.Date;

/**
 * @author lifeisgood21
 *
 */
public class ForRent extends PhysicalProduct{
	
	@BusinessObjectField
	private int timesrented;	

	public ForRent(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the timesrented
	 */
	public int getTimesrented() {
		return timesrented;
	}

	/**
	 * @param timesrented the timesrented to set
	 */
	public void setTimesrented(int timesrented) {
		this.timesrented = timesrented;
		this.setDirty();
	}

}
