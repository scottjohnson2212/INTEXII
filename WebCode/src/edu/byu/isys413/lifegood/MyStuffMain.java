package edu.byu.isys413.lifegood;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.text.TextViewer;

public class MyStuffMain {
	private static Text storeName;
	private static Text employeeName;

	/**
	 * Main Page of Buttons
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		final Display display = Display.getDefault();
		final Shell shlMyStuffSystem = new Shell(SWT.APPLICATION_MODAL);
		shlMyStuffSystem.setSize(389, 420);
		shlMyStuffSystem.setText("My Stuff System");
		shlMyStuffSystem.setLayout(new RowLayout(SWT.HORIZONTAL));
		try {
			CreateDB.main(null);
		} catch (Exception e1) {
			System.out.println("Create DB failed");
		}
		boolean loop = true;

		Store store = null;
		Employee employee = null;
		String username = null;

		// Login Loop
		while (loop) {
			// Username Dialog
			Shell shell = new Shell();
			InputDialog message = new InputDialog(shell, "Login", "Username: ", "enter username here", null);
			message.open();
			username = message.getValue();

			// get the password
			JPasswordField passwordField = new JPasswordField();
			passwordField.setEchoChar('*');
			Object[] obj = { "Please enter the password:\n\n", passwordField };
			Object stringArray[] = { "OK", "Cancel" };
			if (JOptionPane.showOptionDialog(null, obj, "Need password", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, stringArray, obj) == JOptionPane.YES_OPTION)
				;
			String password = new String(passwordField.getPassword());

			try {
				String id = "1";
				store = BusinessObjectDAO.getInstance().read(id);
				employee = BusinessObjectDAO.getInstance().searchForBO("Employee", new SearchCriteria("username", username), new SearchCriteria("password", password));
				
				if(store == null || employee == null){
					Shell shell2 = new Shell();
					MessageBox message2 = new MessageBox(shell, SWT.ICON_WARNING);
					message2.setText("Warning");
					message2.setMessage("You must fill in all fields");
					message2.open();
				}else{
					break;
				}
				
				
			} catch (Exception e) {
				Shell shell1 = new Shell();
				MessageBox message1 = new MessageBox(shell, SWT.ICON_WARNING);
				message1.setText("Warning");
				message1.setMessage("Login Failed Please Try Again");
				message1.open();
			}
		}

		final String usernameToWorkWith = username;
		final Store storeToWorkWith = store;

		Label lblLogInInfo = new Label(shlMyStuffSystem, SWT.NONE);
		lblLogInInfo.setAlignment(SWT.RIGHT);
		lblLogInInfo.setLayoutData(new RowData(201, SWT.DEFAULT));
		lblLogInInfo.setText("Log IN info:");

		storeName = new Text(shlMyStuffSystem, SWT.BORDER);
		storeName.setEnabled(false);
		storeName.setText(store.getCity());

		employeeName = new Text(shlMyStuffSystem, SWT.BORDER);
		employeeName.setEnabled(false);
		employeeName.setText(employee.getFirstname());

		Button empStoreButton = new Button(shlMyStuffSystem, SWT.NONE);
		final AddStores as = new AddStores();
		empStoreButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				as.open();
			}
		});
		empStoreButton.setLayoutData(new RowData(183, 89));
		empStoreButton.setText("Employee / Store");

		Button productsButton = new Button(shlMyStuffSystem, SWT.NONE);
		final AddProducts ap = new AddProducts();
		productsButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ap.open(storeToWorkWith);
			}
		});
		productsButton.setLayoutData(new RowData(183, 89));
		productsButton.setText("Products");

		Button salesButton = new Button(shlMyStuffSystem, SWT.NONE);

		final SalesProcessingWindow spw = new SalesProcessingWindow();
		salesButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				Store store1 = null;
				Employee employee1 = null;
				try {
					String id = "1";
					store1 = BusinessObjectDAO.getInstance().read(id);
					employee1 = BusinessObjectDAO.getInstance().searchForBO("Employee", new SearchCriteria("username", usernameToWorkWith));
				} catch (Exception e9) {
					System.out.println("Get Store and Employee failed");
				}
				spw.open(store1, employee1);
			}
		});
		salesButton.setLayoutData(new RowData(183, 89));
		salesButton.setText("Sales Processing");

		Button updateGeneralLedgerButton = new Button(shlMyStuffSystem, SWT.NONE);
		updateGeneralLedgerButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				GeneralLedger GL = null;
				try {
					GL = BusinessObjectDAO.getInstance().create("GeneralLedger");
				} catch (Exception e1) {
					System.out.println("General Ledger Did not work.");
				}
				GL.updateGeneralLedger();

			}
		});
		updateGeneralLedgerButton.setLayoutData(new RowData(183, 89));
		updateGeneralLedgerButton.setText("Update General Ledger");

		Button payCommissions = new Button(shlMyStuffSystem, SWT.NONE);
		payCommissions.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				Commission CO = null;
				try {
					CO = BusinessObjectDAO.getInstance().create("Commission");
				} catch (Exception e1) {
					System.out.println("Commission Did not work.");
				}
				CO.calculateAndPayMonthEndCommissions();

			}
		});
		payCommissions.setLayoutData(new RowData(183, 89));
		payCommissions.setText("Pay Commissions");

		Button lateBatch = new Button(shlMyStuffSystem, SWT.NONE);
		lateBatch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				Rental ren = null;

				try {
					ren = BusinessObjectDAO.getInstance().create("Rental");
				} catch (DataException e1) {
					System.out.println("Commission Did not work.");
				}

				ren.lateEmailBatch();

			}
		});
		lateBatch.setLayoutData(new RowData(181, 87));
		lateBatch.setText("Late Emails (overdue rentals)");

		Button tenDayLate = new Button(shlMyStuffSystem, SWT.NONE);
		tenDayLate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				Rental ren = null;

				try {
					ren = BusinessObjectDAO.getInstance().create("Rental");
				} catch (DataException e1) {
					System.out.println("Ten Day Commission Did not work.");
				}

				ren.tenDayCharge();

			}
		});
		tenDayLate.setLayoutData(new RowData(180, 79));
		tenDayLate.setText("10 Day Late Charges");

		shlMyStuffSystem.open();
		shlMyStuffSystem.layout();
		while (!shlMyStuffSystem.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
