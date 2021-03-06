package edu.byu.isys413.lifegood;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.RowData;

public class AddStores {

	protected Shell shell;
	private Text sManagerPhone;
	private Text sPhone;
	private Text sAddress;
	private Text sCity;
	private Text sState;
	private Text sZip;
	private Text eStoreCity;
	private Text eFirstName;
	private Text eMiddleName;
	private Text eLastName;
	private Text eHireDate;
	private Text ePhone;
	private Text eUsername;
	private Text ePassword;
	private Text eSalary;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AddStores window = new AddStores();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell(SWT.DIALOG_TRIM | SWT.MIN | SWT.MAX | SWT.APPLICATION_MODAL);
		shell.setSize(478, 393);
		shell.setText("SWT Application");
		shell.setLayout(new BorderLayout(0, 0));

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(BorderLayout.WEST);
		RowLayout rl_composite = new RowLayout(SWT.VERTICAL);
		rl_composite.center = true;
		composite.setLayout(rl_composite);

		Label lblStore = new Label(composite, SWT.NONE);
		lblStore.setText("Store");

		Composite composite_3 = new Composite(composite, SWT.NONE);
		composite_3.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label lblManagerName = new Label(composite_3, SWT.NONE);
		lblManagerName.setText("Manager Phone: ");

		sManagerPhone = new Text(composite_3, SWT.BORDER);

		Composite composite_4 = new Composite(composite, SWT.NONE);
		composite_4.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite_4.setBounds(0, 0, 151, 27);

		Label lblPhone = new Label(composite_4, SWT.NONE);
		lblPhone.setText("Phone: ");

		sPhone = new Text(composite_4, SWT.BORDER);

		Composite composite_5 = new Composite(composite, SWT.NONE);
		composite_5.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite_5.setBounds(0, 0, 163, 27);

		Label lblAddress = new Label(composite_5, SWT.NONE);
		lblAddress.setText("Address:");

		sAddress = new Text(composite_5, SWT.BORDER);

		Composite composite_6 = new Composite(composite, SWT.NONE);
		composite_6.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite_6.setBounds(0, 0, 161, 27);

		Label lblCity = new Label(composite_6, SWT.NONE);
		lblCity.setText("City: ");

		sCity = new Text(composite_6, SWT.BORDER);

		Composite composite_7 = new Composite(composite, SWT.NONE);
		composite_7.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite_7.setBounds(0, 0, 184, 27);

		Label lblState = new Label(composite_7, SWT.NONE);
		lblState.setText("State:");

		sState = new Text(composite_7, SWT.BORDER);

		Composite composite_8 = new Composite(composite, SWT.NONE);
		composite_8.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite_8.setBounds(0, 0, 109, 27);

		Label lblZip = new Label(composite_8, SWT.NONE);
		lblZip.setText("Zip: ");

		sZip = new Text(composite_8, SWT.BORDER);

		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.EAST);
		RowLayout rl_composite_1 = new RowLayout(SWT.VERTICAL);
		rl_composite_1.center = true;
		composite_1.setLayout(rl_composite_1);

		Label lblEmployee = new Label(composite_1, SWT.NONE);
		lblEmployee.setText("Employee");

		Composite composite_10 = new Composite(composite_1, SWT.NONE);
		composite_10.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label lblStoreCity = new Label(composite_10, SWT.NONE);
		lblStoreCity.setText("Store City:");

		eStoreCity = new Text(composite_10, SWT.BORDER);

		Composite composite_11 = new Composite(composite_1, SWT.NONE);
		composite_11.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label lblFirstName = new Label(composite_11, SWT.NONE);
		lblFirstName.setText("First Name: ");

		eFirstName = new Text(composite_11, SWT.BORDER);

		Composite composite_12 = new Composite(composite_1, SWT.NONE);
		composite_12.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label lblMiddleName = new Label(composite_12, SWT.NONE);
		lblMiddleName.setText("Middle Name: ");

		eMiddleName = new Text(composite_12, SWT.BORDER);

		Composite composite_13 = new Composite(composite_1, SWT.NONE);
		composite_13.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label lblLastName = new Label(composite_13, SWT.NONE);
		lblLastName.setText("Last Name: ");

		eLastName = new Text(composite_13, SWT.BORDER);

		Composite composite_14 = new Composite(composite_1, SWT.NONE);
		composite_14.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label lblHireDateyyyymmdd = new Label(composite_14, SWT.NONE);
		lblHireDateyyyymmdd.setText("Hire Date (dd-mm-yyyy): ");

		eHireDate = new Text(composite_14, SWT.BORDER);

		Composite composite_15 = new Composite(composite_1, SWT.NONE);
		composite_15.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label lblPhone_1 = new Label(composite_15, SWT.NONE);
		lblPhone_1.setText("Phone: ");

		ePhone = new Text(composite_15, SWT.BORDER);

		Composite composite_16 = new Composite(composite_1, SWT.NONE);
		composite_16.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label lblUsername = new Label(composite_16, SWT.NONE);
		lblUsername.setText("Username: ");

		eUsername = new Text(composite_16, SWT.BORDER);

		Composite composite_17 = new Composite(composite_1, SWT.NONE);
		composite_17.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label lblPassword = new Label(composite_17, SWT.NONE);
		lblPassword.setText("Password: ");

		ePassword = new Text(composite_17, SWT.BORDER);

		Composite composite_9 = new Composite(composite_1, SWT.NONE);
		composite_9.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label lblSalary = new Label(composite_9, SWT.NONE);
		lblSalary.setText("Salary:");

		eSalary = new Text(composite_9, SWT.BORDER);

		Composite composite_2 = new Composite(shell, SWT.NONE);
		composite_2.setLayoutData(BorderLayout.SOUTH);
		composite_2.setLayout(new RowLayout(SWT.HORIZONTAL));

		Button btnAddStore = new Button(composite_2, SWT.NONE);
		btnAddStore.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				if (sManagerPhone.getText().equals("") || sPhone.getText().equals("") || sAddress.getText().equals("") || sCity.getText().equals("")
						|| sState.getText().equals("") || sZip.getText().equals("")) {
					Shell shell = new Shell();
					MessageBox message = new MessageBox(shell, SWT.ICON_WARNING);
					message.setText("Warning");
					message.setMessage("You must fill in all fields");
					message.open();
					this.widgetSelected(e);
				} else {
					
					Employee E = null;
					Store S = null;
					try {
						S = BusinessObjectDAO.getInstance().create("Store");
						E = BusinessObjectDAO.getInstance().searchForBO("Employee", new SearchCriteria("phone", sManagerPhone.getText()));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					S.setManagerid(E.getId());
					S.setPhone(sPhone.getText());
					S.setAddress(sAddress.getText());
					S.setCity(sCity.getText());
					S.setState(sState.getText());
					S.setZip(sZip.getText());
					
					try {
						BusinessObjectDAO.getInstance().save(S);
					} catch (DataException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					sManagerPhone.setText("");
					sPhone.setText("");
					sAddress.setText("");
					sCity.setText("");
					sState.setText("");
					sZip.setText("");
				}

			}
		});
		btnAddStore.setLayoutData(new RowData(127, SWT.DEFAULT));
		btnAddStore.setText("Add Store");

		Label label = new Label(composite_2, SWT.NONE);
		label.setText("   ");

		Label label_1 = new Label(composite_2, SWT.NONE);

		Button button_1 = new Button(composite_2, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		button_1.setText("Return To Main");

		Label label_2 = new Label(composite_2, SWT.NONE);
		label_2.setLayoutData(new RowData(64, SWT.DEFAULT));
		label_2.setText("     ");

		Button btnAddEmployee = new Button(composite_2, SWT.NONE);
		btnAddEmployee.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				if (eStoreCity.getText().equals("") || eFirstName.getText().equals("") || eMiddleName.getText().equals("") || eLastName.getText().equals("")
						|| eHireDate.getText().equals("") || ePhone.getText().equals("") || eUsername.getText().equals("") || ePassword.getText().equals("")
						|| eSalary.getText().equals("")) {
					Shell shell = new Shell();
					MessageBox message = new MessageBox(shell, SWT.ICON_WARNING);
					message.setText("Warning");
					message.setMessage("You must fill in all fields");
					message.open();
					this.widgetSelected(e);
				} else {
					
					Employee E = null;
					Store S = null;
					try {
						E = BusinessObjectDAO.getInstance().create("Employee");
						S = BusinessObjectDAO.getInstance().searchForBO("Store", new SearchCriteria("city", eStoreCity.getText()));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					E.setStoreid(S.getId());
					E.setFirstname(eFirstName.getText());
					E.setMiddlename(eMiddleName.getText());
					E.setLastname(eLastName.getText());
					
					SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
					try {
						E.setHiredate(sdf.parse(eHireDate.getText()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					E.setPhone(ePhone.getText());
					E.setUsername(eUsername.getText());
					E.setPassword(ePassword.getText());
					E.setSalary(Double.parseDouble(eSalary.getText()));
					
					
					try {
						BusinessObjectDAO.getInstance().save(E);
					} catch (DataException e1) {
						e1.printStackTrace();
					}

					//Clear Out all Fields
					eStoreCity.setText("");
					eFirstName.setText("");
					eMiddleName.setText("");
					eLastName.setText("");
					eHireDate.setText("");
					ePhone.setText("");
					eUsername.setText("");
					ePassword.setText("");
					eSalary.setText("");
					
					
					

				}

			}
		});
		btnAddEmployee.setLayoutData(new RowData(145, SWT.DEFAULT));
		btnAddEmployee.setText("Add Employee");

	}
}
