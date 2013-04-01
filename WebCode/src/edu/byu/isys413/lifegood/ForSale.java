/**
 * 
 */
package edu.byu.isys413.lifegood;

/**
 * @author lifeisgood21
 *
 */
public class ForSale extends PhysicalProduct{
	
	@BusinessObjectField
	private String newused;

	public ForSale(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the newused
	 */
	public String getNewused() {
		return newused;
	}

	/**
	 * @param newused the newused to set
	 */
	public void setNewused(String newused) {
		this.newused = newused;
		this.setDirty();
	}

}
