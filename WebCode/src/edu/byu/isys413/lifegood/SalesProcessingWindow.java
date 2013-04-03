package edu.byu.isys413.lifegood;

import java.util.*;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import swing2swt.layout.FlowLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class SalesProcessingWindow {

	protected Shell shell;
	private Text textEmployee;
	private Text textStore;
	private Text textFirstName;
	private Text textAddress;
	private Text textCity;
	private Text textState;
	private Text textZip;
	private Text textSearchParam;
	private Text textSerialNumber;
	private Text textSKU;
	private Text textSubTotal;
	private Text textTax;
	private Text textTotal;

	// Business Objects to be used
	private Trans trans = null;
	private Customer customer = null;
	private ConceptualProduct conceptualproduct = null;
	private PhysicalProduct physicalproduct = null;
	private StoreProduct storeproduct = null;
	// Create Lists Needed
	ArrayList<RevenueSource> RevenueSourceList = new ArrayList<RevenueSource>();

	// end of Business Objects

	private Text textEmail;
	private Text textPhone;
	private Text textLastName;
	private Text textMiddleName;
	private Table table;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SalesProcessingWindow window = new SalesProcessingWindow();
			window.open(null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open(Store store, Employee employee) {
		Display display = Display.getDefault();
		createContents(employee, store);
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create the shell.
	 * 
	 * @param display
	 * @return
	 */
	public void createContents(final Employee employee, final Store store) {

		shell = new Shell(SWT.DIALOG_TRIM | SWT.MIN | SWT.MAX | SWT.APPLICATION_MODAL);
		shell.setSize(856, 434);
		shell.setLayout(new BorderLayout(0, 0));

		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.EAST);
		composite_1.setLayout(new RowLayout(SWT.VERTICAL));

		// Create Business Objects needed
		try {
			trans = BusinessObjectDAO.getInstance().create("Trans");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// //////////////////////////Table
		// Creation//////////////////////////////////////

		final TableViewer tableViewer = new TableViewer(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLayoutData(new RowData(311, 189));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		TableViewerColumn tableViewerProduct = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnProduct = tableViewerProduct.getColumn();
		tblclmnProduct.setWidth(128);
		tblclmnProduct.setText("Product");

		TableViewerColumn tableViewerQuantity = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnQuantity = tableViewerQuantity.getColumn();
		tblclmnQuantity.setWidth(100);
		tblclmnQuantity.setText("Qty.");

		TableViewerColumn tableViewerPrice = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnPrice = tableViewerPrice.getColumn();
		tblclmnPrice.setWidth(100);
		tblclmnPrice.setText("Price");

		trans.setStore(store);
		trans.setEmployee(employee);

		// Get Product Name
		tableViewerProduct.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				RevenueSource RS = (RevenueSource) element;
				String pname;
				if (RS.getSaleList().get(0).getCP() == null) {
					pname = RS.getSaleList().get(0).getPP().getProductname();
				} else {
					pname = RS.getSaleList().get(0).getCP().getProductname();
				}
				return pname;
			}
		});

		// Gets Quantity
		tableViewerQuantity.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				RevenueSource RS = (RevenueSource) element;
				return Integer.toString(RS.getSaleList().get(0).getQuantity());
			}
		});

		// Gets Price
		tableViewerPrice.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				RevenueSource RS = (RevenueSource) element;
				Double pprice;
				if (RS.getSaleList().get(0).getCP() == null) {
					pprice = RS.getSaleList().get(0).getPP().getPrice();
				} else {
					pprice = RS.getSaleList().get(0).getCP().getPrice() * RS.getSaleList().get(0).getQuantity();
				}
				return Double.toString(pprice);
			}
		});

		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setInput(RevenueSourceList);
		// ////////////////////////////TABLE
		// CREATION///////////////////////////////////////

		Composite composite_24 = new Composite(composite_1, SWT.NONE);
		RowLayout rl_composite_24 = new RowLayout(SWT.HORIZONTAL);
		rl_composite_24.marginLeft = 150;
		composite_24.setLayout(rl_composite_24);
		composite_24.setLayoutData(new RowData(334, 32));

		Label lblSubtotal = new Label(composite_24, SWT.NONE);
		lblSubtotal.setLayoutData(new RowData(52, 21));
		lblSubtotal.setText("Subtotal:");

		textSubTotal = new Text(composite_24, SWT.BORDER);
		textSubTotal.setLayoutData(new RowData(117, SWT.DEFAULT));

		Composite composite_25 = new Composite(composite_1, SWT.NONE);
		composite_25.setLayoutData(new RowData(335, SWT.DEFAULT));
		RowLayout rl_composite_25 = new RowLayout(SWT.HORIZONTAL);
		rl_composite_25.marginLeft = 150;
		composite_25.setLayout(rl_composite_25);

		Label lblTax = new Label(composite_25, SWT.NONE);
		lblTax.setLayoutData(new RowData(52, 21));
		lblTax.setText("Tax:");

		textTax = new Text(composite_25, SWT.BORDER);
		textTax.setLayoutData(new RowData(117, -1));

		Composite composite_26 = new Composite(composite_1, SWT.NONE);
		composite_26.setLayoutData(new RowData(335, SWT.DEFAULT));
		RowLayout rl_composite_26 = new RowLayout(SWT.HORIZONTAL);
		rl_composite_26.marginLeft = 150;
		composite_26.setLayout(rl_composite_26);

		Label lblTotal = new Label(composite_26, SWT.NONE);
		lblTotal.setLayoutData(new RowData(52, 21));
		lblTotal.setText("Total:");

		textTotal = new Text(composite_26, SWT.BORDER);
		textTotal.setLayoutData(new RowData(117, -1));

		Composite composite_27 = new Composite(composite_1, SWT.NONE);
		composite_27.setLayoutData(new RowData(337, SWT.DEFAULT));
		RowLayout rl_composite_27 = new RowLayout(SWT.HORIZONTAL);
		rl_composite_27.marginLeft = 250;
		composite_27.setLayout(rl_composite_27);

		Button finishAndPayButton = new Button(composite_27, SWT.NONE);
		finishAndPayButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				trans.finishAndPay();

				textSearchParam.setText("");
				textFirstName.setText("");
				textMiddleName.setText("");
				textLastName.setText("");
				textCity.setText("");
				textState.setText("");
				textZip.setText("");
				textPhone.setText("");
				textEmail.setText("");
				textAddress.setText("");

				RevenueSourceList.clear();
				tableViewer.refresh();
				textSubTotal.setText("");
				textTax.setText("");
				textTotal.setText("");
			}

		});
		finishAndPayButton.setLayoutData(new RowData(84, SWT.DEFAULT));
		finishAndPayButton.setText("Finish and Pay");

		Composite composite_2 = new Composite(shell, SWT.NONE);
		composite_2.setLayoutData(BorderLayout.NORTH);
		composite_2.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label lblNewLabel = new Label(composite_2, SWT.NONE);
		lblNewLabel.setLayoutData(new RowData(275, 41));
		lblNewLabel.setText("Sales Processing");

		Label lblNewLabel_1 = new Label(composite_2, SWT.NONE);
		lblNewLabel_1.setAlignment(SWT.RIGHT);
		lblNewLabel_1.setLayoutData(new RowData(277, SWT.DEFAULT));
		lblNewLabel_1.setText("Log IN info:");

		textStore = new Text(composite_2, SWT.BORDER);
		textStore.setEnabled(false);
		textStore.setText(store.getCity());

		textEmployee = new Text(composite_2, SWT.BORDER);
		textEmployee.setEnabled(false);
		textEmployee.setText(employee.getFirstname());

		Composite composite = new Composite(shell, SWT.NO_REDRAW_RESIZE);
		composite.setLayoutData(BorderLayout.WEST);
		RowLayout rl_composite = new RowLayout(SWT.VERTICAL);
		rl_composite.spacing = 2;
		rl_composite.pack = false;
		composite.setLayout(rl_composite);

		Composite composite_9 = new Composite(composite, SWT.NONE);
		composite_9.setLayoutData(new RowData(238, 30));
		composite_9.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label lblSearchByPhone = new Label(composite_9, SWT.NONE);
		lblSearchByPhone.setLayoutData(new RowData(101, SWT.DEFAULT));
		lblSearchByPhone.setText("Search By Phone:");

		textSearchParam = new Text(composite_9, SWT.BORDER);
		textSearchParam.setLayoutData(new RowData(111, SWT.DEFAULT));

		Composite composite_3 = new Composite(composite, SWT.NONE);
		composite_3.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label custFirstName = new Label(composite_3, SWT.NONE);
		custFirstName.setLayoutData(new RowData(81, SWT.DEFAULT));
		custFirstName.setText("First Name:");

		textFirstName = new Text(composite_3, SWT.BORDER);
		textFirstName.setEnabled(false);

		Composite composite_4 = new Composite(composite, SWT.NONE);
		composite_4.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label lblMiddleName = new Label(composite_4, SWT.NONE);
		lblMiddleName.setLayoutData(new RowData(82, SWT.DEFAULT));
		lblMiddleName.setText("Middle Name:");

		textMiddleName = new Text(composite_4, SWT.BORDER);
		textMiddleName.setEnabled(false);

		Composite composite_5 = new Composite(composite, SWT.NONE);
		composite_5.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label label_2 = new Label(composite_5, SWT.NONE);
		label_2.setLayoutData(new RowData(82, SWT.DEFAULT));
		label_2.setText("Last Name:");

		textLastName = new Text(composite_5, SWT.BORDER);
		textLastName.setEnabled(false);

		Composite composite_6 = new Composite(composite, SWT.NONE);
		composite_6.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label label_1 = new Label(composite_6, SWT.NONE);
		label_1.setLayoutData(new RowData(82, SWT.DEFAULT));
		label_1.setText("Phone:");

		textPhone = new Text(composite_6, SWT.BORDER);
		textPhone.setEnabled(false);

		Composite composite_14 = new Composite(composite, SWT.NONE);
		RowLayout rl_composite_14 = new RowLayout(SWT.HORIZONTAL);
		composite_14.setLayout(rl_composite_14);

		Label label = new Label(composite_14, SWT.NONE);
		label.setLayoutData(new RowData(81, SWT.DEFAULT));
		label.setText("Email:");

		textEmail = new Text(composite_14, SWT.BORDER);
		textEmail.setEnabled(false);

		Composite composite_16 = new Composite(composite, SWT.NONE);
		RowLayout rl_composite_16 = new RowLayout(SWT.HORIZONTAL);
		rl_composite_16.marginLeft = 130;
		composite_16.setLayout(rl_composite_16);

		Button newCustomerButton = new Button(composite_16, SWT.NONE);
		newCustomerButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				try {
					customer = BusinessObjectDAO.getInstance().create("Customer");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				textFirstName.setEnabled(true);
				textMiddleName.setEnabled(true);
				textLastName.setEnabled(true);
				textPhone.setEnabled(true);
				textEmail.setEnabled(true);
				textAddress.setEnabled(true);
				textCity.setEnabled(true);
				textState.setEnabled(true);
				textZip.setEnabled(true);
			}
		});
		newCustomerButton.setText("New Customer");

		Composite composite_17 = new Composite(composite, SWT.NONE);
		composite_17.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label productSerialNumber = new Label(composite_17, SWT.NONE);
		productSerialNumber.setText("Serial Number:");

		textSerialNumber = new Text(composite_17, SWT.BORDER);
		textSerialNumber.setLayoutData(new RowData(121, SWT.DEFAULT));

		Composite composite_20 = new Composite(composite, SWT.NONE);
		composite_20.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label productSKU = new Label(composite_20, SWT.NONE);
		productSKU.setLayoutData(new RowData(80, SWT.DEFAULT));
		productSKU.setText("SKU:");

		textSKU = new Text(composite_20, SWT.BORDER);
		textSKU.setLayoutData(new RowData(121, SWT.DEFAULT));

		Composite composite_7 = new Composite(shell, SWT.NONE);
		composite_7.setLayoutData(BorderLayout.CENTER);
		composite_7.setLayout(new RowLayout(SWT.HORIZONTAL));

		Composite composite_13 = new Composite(composite_7, SWT.NONE);
		composite_13.setLayoutData(new RowData(141, 32));
		composite_13.setLayout(new RowLayout(SWT.HORIZONTAL));

		Button searchByPhoneButton = new Button(composite_13, SWT.NONE);
		searchByPhoneButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				if (textSearchParam.getText().equals("")) {
					Shell shell = new Shell();
					MessageBox message = new MessageBox(shell, SWT.ICON_WARNING);
					message.setText("Warning");
					message.setMessage("You must enter a Phone Number");
					message.open();
				} else {
					try {
						customer = BusinessObjectDAO.getInstance().searchForBO("Customer", new SearchCriteria("phone", textSearchParam.getText()));
						textSearchParam.setText("");

						trans.setCustomer(customer);

						textFirstName.setText(customer.getFirstname());
						textMiddleName.setText(customer.getMiddlename());
						textLastName.setText(customer.getLastname());
						textPhone.setText(customer.getPhone());
						textEmail.setText(customer.getEmail());
						textAddress.setText(customer.getAddress());
						textCity.setText(customer.getCity());
						textState.setText(customer.getState());
						textZip.setText(customer.getZip());

						textFirstName.setEnabled(false);
						textMiddleName.setEnabled(false);
						textLastName.setEnabled(false);
						textPhone.setEnabled(false);
						textEmail.setEnabled(false);
						textAddress.setEnabled(false);
						textCity.setEnabled(false);
						textState.setEnabled(false);
						textZip.setEnabled(false);

					} catch (Exception e1) {
						Shell shell = new Shell();
						MessageBox message = new MessageBox(shell, SWT.ICON_WARNING);
						message.setText("Warning");
						message.setMessage("Customer Search Returned No Results\n" + "Format must be: (xxx) xxx-xxxx");
						textSearchParam.setText("");
						message.open();
					}// catch
				}// else
				trans.setCustomer(customer);
			}// selectionwidget
		});
		searchByPhoneButton.setText("Search");

		Composite composite_8 = new Composite(composite_7, SWT.NONE);
		composite_8.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite_8.setLayoutData(new RowData(163, 27));

		Label custAddress = new Label(composite_8, SWT.NONE);
		custAddress.setLayoutData(new RowData(57, SWT.DEFAULT));
		custAddress.setText("Address:");

		textAddress = new Text(composite_8, SWT.BORDER);
		textAddress.setEnabled(false);
		textAddress.setLayoutData(new RowData(85, SWT.DEFAULT));

		Composite composite_10 = new Composite(composite_7, SWT.NONE);
		composite_10.setLayoutData(new RowData(163, SWT.DEFAULT));
		composite_10.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label custCity = new Label(composite_10, SWT.NONE);
		custCity.setLayoutData(new RowData(57, -1));
		custCity.setText("City:");

		textCity = new Text(composite_10, SWT.BORDER);
		textCity.setEnabled(false);
		textCity.setLayoutData(new RowData(85, SWT.DEFAULT));

		Composite composite_11 = new Composite(composite_7, SWT.NONE);
		composite_11.setLayoutData(new RowData(162, SWT.DEFAULT));
		composite_11.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label custState = new Label(composite_11, SWT.NONE);
		custState.setLayoutData(new RowData(57, -1));
		custState.setText("State:");

		textState = new Text(composite_11, SWT.BORDER);
		textState.setEnabled(false);
		textState.setLayoutData(new RowData(87, SWT.DEFAULT));

		Composite composite_12 = new Composite(composite_7, SWT.NONE);
		composite_12.setLayoutData(new RowData(163, SWT.DEFAULT));
		composite_12.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label custZip = new Label(composite_12, SWT.NONE);
		custZip.setLayoutData(new RowData(57, -1));
		custZip.setText("Zip:");

		textZip = new Text(composite_12, SWT.BORDER);
		textZip.setEnabled(false);
		textZip.setLayoutData(new RowData(85, SWT.DEFAULT));

		Composite composite_15 = new Composite(composite_7, SWT.NONE);
		RowLayout rl_composite_15 = new RowLayout(SWT.HORIZONTAL);
		rl_composite_15.marginTop = 11;
		composite_15.setLayout(rl_composite_15);
		composite_15.setLayoutData(new RowData(144, 44));

		Button saveCustomerButton = new Button(composite_15, SWT.NONE);
		saveCustomerButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (customer == null) {
						throw new Exception();
					}

					setCustomerData(customer);

					BusinessObjectDAO.getInstance().save(customer);

					textFirstName.setEnabled(false);
					textMiddleName.setEnabled(false);
					textLastName.setEnabled(false);
					textPhone.setEnabled(false);
					textEmail.setEnabled(false);
					textAddress.setEnabled(false);
					textCity.setEnabled(false);
					textState.setEnabled(false);
					textZip.setEnabled(false);

					trans.setCustomer(customer);
				} catch (Exception e1) {
					Shell shell = new Shell();
					MessageBox message = new MessageBox(shell, SWT.ICON_WARNING);
					message.setText("Warning");
					message.setMessage("Saving Failed");
					message.open();
				}
			}
		});
		saveCustomerButton.setLayoutData(new RowData(90, 27));
		saveCustomerButton.setText("Save Customer");

		Composite composite_18 = new Composite(composite_7, SWT.NONE);
		composite_18.setLayoutData(new RowData(142, 26));

		Composite composite_19 = new Composite(composite_7, SWT.NONE);
		composite_19.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite_19.setLayoutData(new RowData(141, 30));

		Button addSerialToTransactionButton = new Button(composite_19, SWT.NONE);

		addSerialToTransactionButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ArrayList<Sale> SaleList = new ArrayList<Sale>();
				Product P = null;
				ConceptualProduct CP = null;
				PhysicalProduct PP = null;
				StoreProduct SP = null;
				String serialnum = textSerialNumber.getText();
				ConceptualRental ConcRen = null;

				try {

					PP = BusinessObjectDAO.getInstance().searchForBO("PhysicalProduct", new SearchCriteria("serialnumber", serialnum));
					P = BusinessObjectDAO.getInstance().searchForBO("Product", new SearchCriteria("id", PP.getId()));
					CP = BusinessObjectDAO.getInstance().searchForBO("ConceptualProduct", new SearchCriteria("id", PP.getConceptualproductid()));
					ConcRen = (ConceptualRental) BusinessObjectDAO.getInstance().searchForBO("ConceptualRental", new SearchCriteria("id", CP.getId()));
					if (PP.getType() == "For Sale") {

						if (PP.getStoreid().equals(store.getId())) {
							SP = null;

							RevenueSource RS = null;
							Sale SL = null;
							RS = BusinessObjectDAO.getInstance().create("RevenueSource");
							SL = BusinessObjectDAO.getInstance().create("Sale");

							SL.setP(P);
							SL.setCP(CP);
							SL.setPP(PP);
							SL.setSP(SP);
							SL.setQuantity(1);

							SaleList.add(SL);
							RS.setSaleList(SaleList);
							RevenueSourceList.add(RS);
							trans.setRevenueSourceList(RevenueSourceList);
							trans.calculateTotals(RevenueSourceList);
							tableViewer.refresh();
							textSerialNumber.setText("");

							textSubTotal.setText(Double.toString(trans.getSubtotal()));
							textTax.setText(Double.toString(trans.getTax()));
							textTotal.setText(Double.toString(trans.getTotal()));

						} else {
							throw new Exception();
						}

					} else {
						// This is a Rental

						SP = null;
						RevenueSource RS = null;
						Rental Ren = null;
						RS = BusinessObjectDAO.getInstance().create("RevenueSource");
						Ren = BusinessObjectDAO.getInstance().create("Rental");

						ForRent forrent = BusinessObjectDAO.getInstance().searchForBO("Forrent", new SearchCriteria("id", PP.getId()));

						Date today = new Date();

						Ren.setForrentid(forrent.getId());
						Ren.setDateout(today);

						// get date due
						Date datein = new Date();
						datein.setDate(today.getDay() + 5);

						Ren.setDatein(datein);
						Ren.setWorkordernumber(null);
						Ren.setRemindersent(false);
						// set RS values
						RS.setAmount(ConcRen.getPriceperday() * 5);
						RS.setType("rental");
						RS.rentalList.add(Ren);

						// Finish Up the rest
						RevenueSourceList.add(RS);
						trans.setRevenueSourceList(RevenueSourceList);
						trans.calculateTotals(RevenueSourceList);
						tableViewer.refresh();
						textSerialNumber.setText("");

						textSubTotal.setText(Double.toString(trans.getSubtotal()));
						textTax.setText(Double.toString(trans.getTax()));
						textTotal.setText(Double.toString(trans.getTotal()));

					}

				} catch (Exception e1) {
					Shell shell = new Shell();
					MessageBox message = new MessageBox(shell, SWT.ICON_WARNING);
					message.setText("Warning");
					message.setMessage("Invalid Serial Number Try Again");
					message.open();
					textSKU.setText("");
				}

			}

		});
		addSerialToTransactionButton.setText("Add To Transaction");

		Composite composite_21 = new Composite(composite_7, SWT.NONE);
		composite_21.setLayoutData(new RowData(141, SWT.DEFAULT));
		composite_21.setLayout(new RowLayout(SWT.HORIZONTAL));

		// ADD CONCEPTUAL PRODUCT
		Button addSKUToTransactionButton = new Button(composite_21, SWT.NONE);
		addSKUToTransactionButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ArrayList<Sale> SaleList = new ArrayList<Sale>();
				Product P = null;
				ConceptualProduct CP = null;
				PhysicalProduct PP = null;
				StoreProduct SP = null;
				String SKU = textSKU.getText();

				// Get Associated Objects
				try {

					CP = BusinessObjectDAO.getInstance().searchForBO("ConceptualProduct", new SearchCriteria("sku", SKU));
					P = BusinessObjectDAO.getInstance().searchForBO("Product", new SearchCriteria("id", CP.getId()));
					PP = null;
					SP = BusinessObjectDAO.getInstance().searchForBO("StoreProduct", new SearchCriteria("storeid", store.getId()),
							new SearchCriteria("conceptualproductid", CP.getId()));

					// GET SALE OBJECT
					RevenueSource RS = null;
					Sale SL = null;
					SL = BusinessObjectDAO.getInstance().create("Sale");
					RS = BusinessObjectDAO.getInstance().create("RevenueSource");

					SL.setP(P);
					SL.setCP(CP);
					SL.setPP(PP);
					SL.setSP(SP);

					// QUANTITY DIALOG
					Shell shell = new Shell();
					InputDialog message = new InputDialog(shell, "Important", "Quantity", "1", null);
					message.open();
					SL.setQuantity(Integer.parseInt(message.getValue()));

					// COMPUTE TOTALS AND ADD TO LIST
					SaleList.add(SL);
					RS.setSaleList(SaleList);
					RevenueSourceList.add(RS);
					trans.setRevenueSourceList(RevenueSourceList);
					trans.calculateTotals(RevenueSourceList);
					tableViewer.refresh();
					textSKU.setText("");

					textSubTotal.setText(Double.toString(trans.getSubtotal()));
					textTax.setText(Double.toString(trans.getTax()));
					textTotal.setText(Double.toString(trans.getTotal()));
				} catch (Exception e1) {
					Shell shell = new Shell();
					MessageBox message = new MessageBox(shell, SWT.ICON_WARNING);
					message.setText("Warning");
					message.setMessage("Invalid SKU Number Try Again");
					message.open();
					textSKU.setText("");
				}

			}
		});
		addSKUToTransactionButton.setText("Add To Transaction");

		Composite composite_22 = new Composite(composite_7, SWT.NONE);
		composite_22.setLayout(new RowLayout(SWT.VERTICAL));

		Label label_3 = new Label(composite_22, SWT.NONE);
		label_3.setText("     ");

		Label label_4 = new Label(composite_22, SWT.NONE);
		label_4.setText("          ");

		Button btnReturnToMain = new Button(composite_22, SWT.NONE);

		btnReturnToMain.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();

			}
		});
		btnReturnToMain.setText("Return To Main");
	}

	/**
	 * sets the customers data
	 */
	protected Customer setCustomerData(Customer customer) {
		customer.setFirstname(textFirstName.getText());
		customer.setMiddlename(textMiddleName.getText());
		customer.setLastname(textLastName.getText());
		customer.setPhone(textPhone.getText());
		customer.setEmail(textEmail.getText());
		customer.setAddress(textAddress.getText());
		customer.setCity(textCity.getText());
		customer.setState(textState.getText());
		customer.setZip(textZip.getText());
		return customer;
	}

}
