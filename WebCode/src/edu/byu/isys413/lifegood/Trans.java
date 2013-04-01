/**
 * 
 */
package edu.byu.isys413.lifegood;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

/**
 * @author lifeisgood21
 *
 */
public class Trans extends BusinessObject {

	@BusinessObjectField
	private String storeid;
	@BusinessObjectField
	private String customerid;
	@BusinessObjectField
	private String employeeid;
	@BusinessObjectField
	private Date date;
	@BusinessObjectField
	private double subtotal;
	@BusinessObjectField
	private double tax;
	@BusinessObjectField
	private double total;
	
	//objects
	
	private Employee employee = null;
	private Store store = null;
	private Customer customer = null;
	private RevenueSource RevSource = null;
	private ArrayList<RevenueSource> RevenueSourceList = null;
	private double calcSubTotal = 0;
	private double calcTax = 0;
	private double calcTotal = 0;
	private final double taxRate = .06;
	
	
	//Objects for Finish and Pay
	private Commission commission = null;
	private JournalEntry journalentry = null;
	private ArrayList<DebitCredit> debitCreditList = null;
	private DebitCredit debitCreditSR = null;
	private DebitCredit debitCreditC = null;
	private DebitCredit debitCreditTP = null;
	private DebitCredit debitCreditComExp = null;
	private DebitCredit debitCreditComP = null;
	private Payment payment = null;
	
	
	/**
	 * Calculate Subtotal, Total, Tax
	 */
	public void calculateTotals(ArrayList<RevenueSource> RevenueSourceList){
		calcSubTotal = 0;
		calcTax = 0;
		calcTotal = 0;
		
		
		for (int i = 0; i < RevenueSourceList.size(); i++){
			if(RevenueSourceList.get(i).getSaleList().get(0).getCP() == null){
				
				calcSubTotal = calcSubTotal + RevenueSourceList.get(i).getSaleList().get(0).getPP().getPrice();
				
				
			}else{
				
				calcSubTotal = (calcSubTotal + RevenueSourceList.get(i).getSaleList().get(0).getCP().getPrice()) * RevenueSourceList.get(i).getSaleList().get(0).getQuantity();
				
			}//else
			
			
		}//for loop
		
		//Calculate Tax and Total
		calcTax = calcSubTotal * taxRate;
		calcTotal = calcSubTotal + calcTax;
		
		
		//Set values in Business Object so they can be displayed
		this.setSubtotal(calcSubTotal);
		this.setTax(calcTax);
		this.setTotal(calcTotal);
	}
	
	public void finishAndPay(){
		Date date = new Date();
		this.setDate(date);
		
		
		try {
			commission = BusinessObjectDAO.getInstance().create("Commission");
			debitCreditSR = BusinessObjectDAO.getInstance().create("DebitCredit");
			debitCreditC = BusinessObjectDAO.getInstance().create("DebitCredit");
			debitCreditTP = BusinessObjectDAO.getInstance().create("DebitCredit");
			debitCreditComExp = BusinessObjectDAO.getInstance().create("DebitCredit");
			debitCreditComP = BusinessObjectDAO.getInstance().create("DebitCredit");
			journalentry = BusinessObjectDAO.getInstance().create("JournalEntry");
			payment = BusinessObjectDAO.getInstance().create("Payment");
			debitCreditList = new ArrayList<DebitCredit>();
		} catch (Exception e) {
			Shell shell = new Shell();
			MessageBox message = new MessageBox(shell, SWT.ICON_WARNING);
			message.setText("Warning");
			message.setMessage("Finish and Pay did not work correctly!");
			message.open();
		}
		
		//Update Quantities in the Store Product / or status in PhysicalProduct and save them
		try {
		int quantityonhand = 0;
		
		for (int i = 0; i < RevenueSourceList.size(); i++){
			
			if(RevenueSourceList.get(i).getSaleList().get(0).getCP() == null){
				RevenueSourceList.get(i).getSaleList().get(0).getPP().setStatus("Inactive");
				BusinessObjectDAO.getInstance().save(RevenueSourceList.get(i).getSaleList().get(0).getPP());
			}else{
				quantityonhand = RevenueSourceList.get(i).getSaleList().get(0).getSP().getQuantityonhand();
				quantityonhand = quantityonhand - RevenueSourceList.get(i).getSaleList().get(0).getQuantity();
				RevenueSourceList.get(i).getSaleList().get(0).getSP().setQuantityonhand(quantityonhand);
				BusinessObjectDAO.getInstance().save(RevenueSourceList.get(i).getSaleList().get(0).getSP());
				
			}
		}
		} catch (Exception e) {
			System.out.println("Saving the Store Product/Physical Product did not work");
		}
		
		
/////////////////////////////////////////SaveCommissions///////////////////////////////////////////////////

		commission.setTransactionid(this.getId());
		commission.setEmployeeid(this.getEmployee().getId());
		commission.setDate(date);
		
		double commissionamount = 0;
		double comrate = 0;
		double calcAmount = 0;
		
		for (int i = 0; i < RevenueSourceList.size(); i++){
			
		comrate = RevenueSourceList.get(i).getSaleList().get(0).getP().getFullCommissionRate();
		calcAmount = comrate * RevenueSourceList.get(i).getSaleList().get(0).getP().getPrice();
		
		calcAmount = calcAmount * RevenueSourceList.get(i).getSaleList().get(0).getQuantity();
		
		commissionamount = commissionamount + calcAmount;
		
		calcAmount = 0;
		
		}
		
		commission.setAmount(commissionamount);
		
		
		System.out.println("Amount of Commission Paid:   " + Double.toString(commissionamount));
		
		try {
			BusinessObjectDAO.getInstance().save(commission);
		} catch (Exception e) {
			System.out.println("Saving commission Failed");
		}
		
/////////////////////////////////////////////////SaveJournalEntry/////////////////////////////////////////////

		journalentry.setTransactionid(this.getId());
		journalentry.setDate(date);
		
		debitCreditC.setJournalentryid(journalentry.getId());
		debitCreditC.setDebitb(true);
		debitCreditC.setGeneralledgername("Cash");
		debitCreditC.setAmount(this.getTotal());
		
		debitCreditSR.setJournalentryid(journalentry.getId());
		debitCreditSR.setDebitb(false);
		debitCreditSR.setGeneralledgername("Sales Revenue");
		debitCreditSR.setAmount(this.getSubtotal());
		
		debitCreditTP.setJournalentryid(journalentry.getId());
		debitCreditTP.setDebitb(false);
		debitCreditTP.setGeneralledgername("Sales Tax Payable");
		debitCreditTP.setAmount(this.getTax());
		
		debitCreditComExp.setJournalentryid(journalentry.getId());
		debitCreditComExp.setDebitb(true);
		debitCreditComExp.setGeneralledgername("Commissions Expense");
		debitCreditComExp.setAmount(this.commission.getAmount());
		
		debitCreditComP.setJournalentryid(journalentry.getId());
		debitCreditComP.setDebitb(false);
		debitCreditComP.setGeneralledgername("Commissions Payable");
		debitCreditComP.setAmount(this.commission.getAmount());
		
		
		
		debitCreditList.add(0, debitCreditC);
		   debitCreditList.add(1, debitCreditSR);
		   debitCreditList.add(2, debitCreditTP);
		debitCreditList.add(3, debitCreditComExp);
		   debitCreditList.add(4, debitCreditComP);
		   
		
		journalentry.setDebitCreditList(debitCreditList);
		
		try {
			BusinessObjectDAO.getInstance().save(journalentry);
		} catch (Exception e) {
			System.out.println("Saving Journal Entry Failed");
		}
		
		try {
			
			for (int i = 0; i < journalentry.getDebitCreditList().size(); i++){
			DebitCredit dc = journalentry.getDebitCreditList().get(i);
			BusinessObjectDAO.getInstance().save(dc);
			}
			
		} catch (Exception e) {
			System.out.println("Saving Journal Entry Failed");
		}
		
		
////////////////////////////////////SaveSale//////////////////////////////////////////////
		try {
			
			for (int i = 0; i < RevenueSourceList.size(); i++){
			Sale SL = RevenueSourceList.get(i).getSaleList().get(0);
			BusinessObjectDAO.getInstance().save(SL);
			}
			
		} catch (Exception e) {
			System.out.println("Saving Sales Failed");
		}
		
		

		
		
///////////////////////////////////SaveTransaction with customer id if you have one.///////////////////////////////////////////
		this.setStoreid(store.getId());
		this.setEmployeeid(employee.getId());
		if(customer == null){
			//do nothing
		}else{
			this.setCustomerid(customer.getId());
		}
		
		try {
			
			BusinessObjectDAO.getInstance().save(this);
			
			
		} catch (Exception e) {
			System.out.println("Saving Sales Failed");
		}
		
		
////////////////////////////////////////////////////////SavePayment/////////////////////////////////////////////////////////
		
		try {
			
			payment.setTransactionid(this.getId());
			payment.setChargeamount(this.getTotal());
			payment.setType("Cash");
			
			BusinessObjectDAO.getInstance().save(payment);
			
			
		} catch (Exception e) {
			System.out.println("Saving Sales Failed");
		}
		
		
	}

	
	public Trans(String id) {
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
	 * @return the customerid
	 */
	public String getCustomerid() {
		return customerid;
	}

	/**
	 * @param customerid the customerid to set
	 */
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
		this.setDirty();
	}

	/**
	 * @return the employeeid
	 */
	public String getEmployeeid() {
		return employeeid;
	}

	/**
	 * @param employeeid the employeeid to set
	 */
	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
		this.setDirty();
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
		this.setDirty();
	}

	/**
	 * @return the subtotal
	 */
	public double getSubtotal() {
		return subtotal;
	}

	/**
	 * @param subtotal the subtotal to set
	 */
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
		this.setDirty();
	}

	/**
	 * @return the tax
	 */
	public double getTax() {
		return tax;
	}

	/**
	 * @param tax the tax to set
	 */
	public void setTax(double tax) {
		this.tax = tax;
		this.setDirty();
	}

	/**
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
		this.setDirty();
	}

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
		this.setDirty();
	}

	/**
	 * @return the store
	 */
	public Store getStore() {
		return store;
	}

	/**
	 * @param store the store to set
	 */
	public void setStore(Store store) {
		this.store = store;
		this.setDirty();
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
		this.setDirty();
	}

	/**
	 * @return the revSource
	 */
	public RevenueSource getRevSource() {
		return RevSource;
	}

	/**
	 * @param revSource the revSource to set
	 */
	public void setRevSource(RevenueSource revSource) {
		RevSource = revSource;
		this.setDirty();
	}

	/**
	 * @return the revenueSourceList
	 */
	public ArrayList<RevenueSource> getRevenueSourceList() {
		return RevenueSourceList;
	}

	/**
	 * @param revenueSourceList the revenueSourceList to set
	 */
	public void setRevenueSourceList(ArrayList<RevenueSource> revenueSourceList) {
		RevenueSourceList = revenueSourceList;
		this.setDirty();
	}

	/**
	 * @return the calcSubTotal
	 */
	public double getCalcSubTotal() {
		return calcSubTotal;
	}

	/**
	 * @param calcSubTotal the calcSubTotal to set
	 */
	public void setCalcSubTotal(double calcSubTotal) {
		this.calcSubTotal = calcSubTotal;
		this.setDirty();
	}

	/**
	 * @return the calcTax
	 */
	public double getCalcTax() {
		return calcTax;
	}

	/**
	 * @param calcTax the calcTax to set
	 */
	public void setCalcTax(double calcTax) {
		this.calcTax = calcTax;
		this.setDirty();
	}

	/**
	 * @return the calcTotal
	 */
	public double getCalcTotal() {
		return calcTotal;
	}

	/**
	 * @param calcTotal the calcTotal to set
	 */
	public void setCalcTotal(double calcTotal) {
		this.calcTotal = calcTotal;
		this.setDirty();
	}

	/**
	 * @return the commission
	 */
	public Commission getCommission() {
		return commission;
	}

	/**
	 * @param commission the commission to set
	 */
	public void setCommission(Commission commission) {
		this.commission = commission;
		this.setDirty();
	}

	/**
	 * @return the journalentry
	 */
	public JournalEntry getJournalentry() {
		return journalentry;
	}

	/**
	 * @param journalentry the journalentry to set
	 */
	public void setJournalentry(JournalEntry journalentry) {
		this.journalentry = journalentry;
		this.setDirty();
	}

	/**
	 * @return the debitCreditList
	 */
	public ArrayList<DebitCredit> getDebitCreditList() {
		return debitCreditList;
	}

	/**
	 * @param debitCreditList the debitCreditList to set
	 */
	public void setDebitCreditList(ArrayList<DebitCredit> debitCreditList) {
		this.debitCreditList = debitCreditList;
		this.setDirty();
	}

	/**
	 * @return the debitCreditSR
	 */
	public DebitCredit getDebitCreditSR() {
		return debitCreditSR;
	}

	/**
	 * @param debitCreditSR the debitCreditSR to set
	 */
	public void setDebitCreditSR(DebitCredit debitCreditSR) {
		this.debitCreditSR = debitCreditSR;
		this.setDirty();
	}

	/**
	 * @return the debitCreditC
	 */
	public DebitCredit getDebitCreditC() {
		return debitCreditC;
	}

	/**
	 * @param debitCreditC the debitCreditC to set
	 */
	public void setDebitCreditC(DebitCredit debitCreditC) {
		this.debitCreditC = debitCreditC;
		this.setDirty();
	}

	/**
	 * @return the debitCreditTP
	 */
	public DebitCredit getDebitCreditTP() {
		return debitCreditTP;
	}

	/**
	 * @param debitCreditTP the debitCreditTP to set
	 */
	public void setDebitCreditTP(DebitCredit debitCreditTP) {
		this.debitCreditTP = debitCreditTP;
		this.setDirty();
	}

	/**
	 * @return the debitCreditComExp
	 */
	public DebitCredit getDebitCreditComExp() {
		return debitCreditComExp;
	}

	/**
	 * @param debitCreditComExp the debitCreditComExp to set
	 */
	public void setDebitCreditComExp(DebitCredit debitCreditComExp) {
		this.debitCreditComExp = debitCreditComExp;
		this.setDirty();
	}

	/**
	 * @return the debitCreditComP
	 */
	public DebitCredit getDebitCreditComP() {
		return debitCreditComP;
	}

	/**
	 * @param debitCreditComP the debitCreditComP to set
	 */
	public void setDebitCreditComP(DebitCredit debitCreditComP) {
		this.debitCreditComP = debitCreditComP;
		this.setDirty();
	}

	/**
	 * @return the payment
	 */
	public Payment getPayment() {
		return payment;
	}

	/**
	 * @param payment the payment to set
	 */
	public void setPayment(Payment payment) {
		this.payment = payment;
		this.setDirty();
	}

	/**
	 * @return the taxRate
	 */
	public double getTaxRate() {
		return taxRate;
	}

}
