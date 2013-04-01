/**
 * 
 */
package edu.byu.isys413.lifegood;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.mail.MessagingException;

/**
 * @author lifeisgood21
 *
 */
public class Rental extends RevenueSource {
	
	@BusinessObjectField
	private String forrentid;
	@BusinessObjectField
	private Date dateout;
	@BusinessObjectField
	private Date datein;
	@BusinessObjectField
	private Date datedue;
	@BusinessObjectField
	private String workordernumber;
	@BusinessObjectField
	private boolean remindersent;
	
	
	//Other Variables for Calculations
	
	private int numberofdaysoverdue;
	

	/**
	 * @return the numberofdaysoverdue
	 */
	public int getNumberofdaysoverdue() {
		return numberofdaysoverdue;
	}


	/**
	 * @param numberofdaysoverdue the numberofdaysoverdue to set
	 */
	public void setNumberofdaysoverdue(int numberofdaysoverdue) {
		this.numberofdaysoverdue = numberofdaysoverdue;
		this.setDirty();
	}


	public Rental(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	
	
	public void lateEmailBatch(){
		
		//get Rentals that havent had a reminder sent
		
		Date today = new Date();
		today.setHours(0);
		today.setMinutes(0);
		today.setSeconds(0);
		
		System.out.println(today.toString());
		
		List<BusinessObject> BOoverdues = null;
		try {
			BOoverdues = BusinessObjectDAO.getInstance().searchForList("Rental", new SearchCriteria("remindersent", false));
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Rental> overdues = new ArrayList<Rental>();
		
		for(int i = 0; i < BOoverdues.size(); i++){
			Rental rent = (Rental) BOoverdues.get(i);
			
			overdues.add(rent);
		}
		
		
		//compare dates and create list of rentals that need to have an email sent to them
		
		ArrayList<Rental> overdueNeedEmail = new ArrayList<Rental>();
		
		for(int i = 0; i < overdues.size(); i++){
			
			Date datetocompare = overdues.get(i).getDatedue();			
			
			if(today.getTime() > datetocompare.getTime()){
				
				overdueNeedEmail.add(overdues.get(i));
				
			}else{
				continue;
			}
		}
		
		
		
		
		//Loop through each rental
		for(int i = 0; i < overdueNeedEmail.size(); i++){

		   //get transaction
			
			Trans trans = null;
			try {
				trans = BusinessObjectDAO.getInstance().searchForBO("Trans", new SearchCriteria("id",overdueNeedEmail.get(i).getTransactionid()));
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//get Customer
			Customer cust = null;
			try {
				cust = BusinessObjectDAO.getInstance().searchForBO("Customer", new SearchCriteria("id",trans.getCustomerid()));
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			//send Email
			
			try {
				Mail.send(
						"scottbrasil21@gmail.com",
						"My Stuff",
						cust.getEmail(),
						"Confirmation Link",
						"Your rental is now 1 day overdue, you will incur late charges up to 10 days" +
						"overdue, and then your Credit Card will be charged the full 10 days of late charges" +
						"in addition to the replacement price of the item");
			} catch (Exception e) {
				System.out.println("Email did not send to "+cust.getEmail());
			}
		
			//set Reminder sent = true on rental
			overdueNeedEmail.get(i).setRemindersent(true);
		
			//Save Rental Object
			try {
				BusinessObjectDAO.getInstance().save(overdueNeedEmail.get(i));
			} catch (DataException e) {
				System.out.println("Saved!!");
			}
		
		}
		
		
		
	}
	
//-----------------------------------------------------Ten DAY CHARGE METHOD-------------------------------------------------------	
	public void tenDayCharge() {
		
		
		//---------------------------------------get all rentals with no date in-----------------------------------------------
		
		Date today = new Date();
		today.setHours(0);
		today.setMinutes(0);
		today.setSeconds(0);
		
		System.out.println(today.toString());
		
		List<BusinessObject> BOoverdues = null;
		try {
			BOoverdues = BusinessObjectDAO.getInstance().searchForList("Rental", new SearchCriteria("remindersent", true));
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<Rental> overdues = new ArrayList<Rental>();
		
		for(int i = 0; i < BOoverdues.size(); i++){
			Rental rent = (Rental) BOoverdues.get(i);
			
			overdues.add(rent);
		}
		
		//get out rentals with null datein
		ArrayList<Rental> newoverdues = new ArrayList<Rental>();
		
		for(int i = 0; i < overdues.size(); i++){

			if(overdues.get(i).getDatein() == null){
				newoverdues.add(overdues.get(i));
			}else{
				continue;
			}
		}
		
		//---------------------------------------get out rentals that are ten days late or older
		
		ArrayList<Rental> overdueNeedCharge = new ArrayList<Rental>();
		
	for(int ie = 0; ie < newoverdues.size(); ie++){
			
			Date datetocompare = newoverdues.get(ie).getDatedue();	
			
			int numberOfDaysOverdue = (int) Math.ceil((today.getTime() - datetocompare.getTime()) / 86400000);
			
			//add one to count today
			numberOfDaysOverdue = numberOfDaysOverdue + 1;
			
			newoverdues.get(ie).setNumberofdaysoverdue(numberOfDaysOverdue);
			
			if(numberOfDaysOverdue >= 10){
				
				overdueNeedCharge.add(newoverdues.get(ie));
				
			}else{
				continue;
			}
	}
		
		//------------------------create Transaction
		
for(int i = 0; i < overdueNeedCharge.size(); i++){
		
		Trans overdueTrans = null;
			
		try {
			overdueTrans = BusinessObjectDAO.getInstance().create("Trans");
		} catch (Exception e) {
			System.out.println("Creation of Transaction Failed");
		}
			
		//------------------------Create Two Revenue Sources, one Fee one sale
		RevenueSource RevSourSale = null;
		RevenueSource RevSourFee = null;
		try {
			RevSourFee = BusinessObjectDAO.getInstance().create("RevenueSource");
			RevSourSale = BusinessObjectDAO.getInstance().create("RevenueSource");
		} catch (Exception e) {
			System.out.println("Creations of RevSours failed");
}
		
		
		
		//------------------------set the values in the Fee Revenue Source
		
		Fee fee = null;
		
		try {
		fee = BusinessObjectDAO.getInstance().create("Fee");
		} catch (Exception e) {
			System.out.println("Creations of RevSours failed");
		}
		
		fee.setRentalid(overdueNeedCharge.get(i).getId());
				//-------------------------------------------compute fee for overdue rental
			
				
				ForRent forRent = null;
				ConceptualProduct CP = null;
				Product P = null;
				PhysicalProduct PP = null;
				ConceptualRental ConcepRent = null;
				try {
					// first get For Rent Object
					forRent = BusinessObjectDAO.getInstance().searchForBO("forrent", new SearchCriteria("id", overdueNeedCharge.get(i).getForrentid()));
					
					//ConceptualProduct	
					CP = BusinessObjectDAO.getInstance().searchForBO("ConceptualProduct", new SearchCriteria("physicalproductid",forRent.getId()));
				
					//ConceptualRental
					ConcepRent = BusinessObjectDAO.getInstance().searchForBO("ConceptualRental", new SearchCriteria("id", CP.getId()));
				} catch (Exception e) {
					System.out.println("Database Navigation failed");
					e.printStackTrace();
				}
				
				double amountofoverduefee = ConcepRent.getPriceperday() * overdueNeedCharge.get(i).getNumberofdaysoverdue();
				
				
		//set fee amount
		fee.setAmount(amountofoverduefee);
		
		//add fee to revenue Source
		RevSourFee.feeList.add(fee);
		RevSourFee.setDirty();
		
		
		
		//------------------------set the values in the Sale Revenue Source
		Sale sale = null;
		try {
			sale = BusinessObjectDAO.getInstance().create("Sale");
			} catch (Exception e) {
				System.out.println("Creations of Sale failed");
			}
		sale.setProductid(forRent.getId());
		sale.setCP(CP);
		sale.setP((Product) forRent);
		sale.setPP((PhysicalProduct) forRent);
		sale.setCR(ConcepRent);
		sale.setDirty();
		
		RevSourSale.saleList.add(sale);
		
		//-----------------------put the revenue Sources into transaction
		
		ArrayList<RevenueSource> RevSours = new ArrayList<RevenueSource>();
		RevSours.add(RevSourFee);
		RevSours.add(RevSourSale);
		
		overdueTrans.setRevenueSourceList(RevSours);
		
		
		//-----------------------get employee and customer from original transaction
		
		
		Trans trans = null;
		try {
			trans = BusinessObjectDAO.getInstance().searchForBO("Trans", new SearchCriteria("id",overdueNeedCharge.get(i).getTransactionid()));
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Customer cust = null;
		
		try {
			cust = BusinessObjectDAO.getInstance().searchForBO("Customer", new SearchCriteria("id",trans.getCustomerid()));
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Employee emp = null;
		
		try {
			emp = BusinessObjectDAO.getInstance().searchForBO("Customer", new SearchCriteria("id",trans.getEmployeeid()));
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		trans.setEmployee(emp);
		trans.setCustomer(cust);
		
		//-----------------------charge customer credit card
		
		trans.finishAndPay();
		
		
		//-----------------------give commission to employee
		
		
		
		//----------------------change status to inactive on rental to for sale and do appropriate things to make that change
		
		
		//-------------------------- set all values in transaction, SAVE EVERYTHING THAT WON't be in Finish and Pay
		
		
		
		//Finish and Pay
		
		
		
		
		//Save EveryTHING
		
		
		
		
		}//end of for loop for each 10 day overdue item
		
	}
	
	


	/**
	 * @return the forrentid
	 */
	public String getForrentid() {
		return forrentid;
	}


	/**
	 * @param forrentid the forrentid to set
	 */
	public void setForrentid(String forrentid) {
		this.forrentid = forrentid;
		this.setDirty();
	}


	/**
	 * @return the dateout
	 */
	public Date getDateout() {
		return dateout;
	}


	/**
	 * @param dateout the dateout to set
	 */
	public void setDateout(Date dateout) {
		this.dateout = dateout;
		this.setDirty();
	}


	/**
	 * @return the datein
	 */
	public Date getDatein() {
		return datein;
	}


	/**
	 * @param datein the datein to set
	 */
	public void setDatein(Date datein) {
		this.datein = datein;
		this.setDirty();
	}


	/**
	 * @return the datedue
	 */
	public Date getDatedue() {
		return datedue;
	}


	/**
	 * @param datedue the datedue to set
	 */
	public void setDatedue(Date datedue) {
		this.datedue = datedue;
		this.setDirty();
	}


	/**
	 * @return the workordernumber
	 */
	public String getWorkordernumber() {
		return workordernumber;
	}


	/**
	 * @param workordernumber the workordernumber to set
	 */
	public void setWorkordernumber(String workordernumber) {
		this.workordernumber = workordernumber;
		this.setDirty();
	}


	/**
	 * @return the remindersent
	 */
	public boolean isRemindersent() {
		return remindersent;
	}


	/**
	 * @param remindersent the remindersent to set
	 */
	public void setRemindersent(boolean remindersent) {
		this.remindersent = remindersent;
		this.setDirty();
	}


	


}
