package edu.byu.isys413.lifegood;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AddProducts {
	protected Shell shell;
	
	
	private Text cpName;
	private Text cpDescription;
	private Text cpManufacturer;
	private Text cpAverageCost;
	private Text cpCommissionRate;
	private Text cpSKU;
	private Text ppCPsku;
	private Text ppName;
	private Text ppSerialNumber;
	private Text ppShelfLocation;
	private Text ppCost;
	private Text ppType;
	private Text ppCommissionRate;
	private Text cpPrice;
	private Text ppPrice;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AddProducts window = new AddProducts();
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
		createContents(null);
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
	public void createContents(final Store store) {
//		super(display, SWT.SHELL_TRIM);
		shell = new Shell(SWT.DIALOG_TRIM | SWT.MIN | SWT.MAX | SWT.APPLICATION_MODAL);
		shell.setSize(450, 336);
		shell.setLayout(new BorderLayout(0, 0));

		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.SOUTH);
		composite_1.setLayout(new RowLayout(SWT.HORIZONTAL));

		Button btnAddConceptualProduct = new Button(composite_1, SWT.NONE);
		btnAddConceptualProduct.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				if (cpName.getText().equals("") || cpDescription.getText().equals("") || cpManufacturer.getText().equals("") || cpAverageCost.getText().equals("")
						|| cpCommissionRate.getText().equals("") || cpSKU.getText().equals("") || cpPrice.getText().equals("")) {
					Shell shell = new Shell();
					MessageBox message = new MessageBox(shell, SWT.ICON_WARNING);
					message.setText("Warning");
					message.setMessage("You must fill in all fields");
					message.open();
					this.widgetSelected(e);
				} else {

					ConceptualProduct CP = null;
					try {
						CP = BusinessObjectDAO.getInstance().create("ConceptualProduct");
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					
						CP.setProductname(cpName.getText());
						CP.setDescription(cpDescription.getText());
						CP.setManufacturer(cpManufacturer.getText());
						CP.setAveragecost(Double.parseDouble(cpAverageCost.getText()));
						CP.setCommissionrate(Double.parseDouble(cpCommissionRate.getText()));
						CP.setSku(cpSKU.getText());
						CP.setType("Conceptual Product");
						CP.setPrice(Double.parseDouble(cpPrice.getText()));
					

					try {
						BusinessObjectDAO.getInstance().save(CP);
					} catch (Exception e1) {
						Shell shell = new Shell();
						MessageBox message = new MessageBox(shell, SWT.ICON_WARNING);
						message.setText("Warning");
						message.setMessage("Save Failed");
						message.open();
					}

					// CLear Out all fields
					cpName.setText("");
					cpDescription.setText("");
					cpManufacturer.setText("");
					cpAverageCost.setText("");
					cpCommissionRate.setText("");
					cpSKU.setText("");
					cpPrice.setText("");
				}
			}

		});
		btnAddConceptualProduct.setText("Add Conceptual Product");

		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setText("   ");

		Label lblNewLabel_4 = new Label(composite_1, SWT.NONE);

		Button btnReturnToMain = new Button(composite_1, SWT.NONE);

		btnReturnToMain.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btnReturnToMain.setText("Return To Main");

		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setLayoutData(new RowData(32, SWT.DEFAULT));
		label_2.setText("     ");

		Button btnAddPhysicalProduct = new Button(composite_1, SWT.NONE);
		btnAddPhysicalProduct.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ppCPsku.getText().equals("") || ppName.getText().equals("") || ppSerialNumber.getText().equals("")
						|| ppShelfLocation.getText().equals("") || ppCost.getText().equals("") || ppType.getText().equals("") || ppCommissionRate.getText().equals("")
						|| ppPrice.getText().equals("")) {
					Shell shell = new Shell();
					MessageBox message = new MessageBox(shell, SWT.ICON_WARNING);
					message.setText("Warning");
					message.setMessage("You must fill in all fields");
					message.open();
				} else {
					PhysicalProduct PP = null;
					try {
						PP = BusinessObjectDAO.getInstance().create("PhysicalProduct");
					} catch (DataException e1) {
						e1.printStackTrace();
					}
					PP.setStoreid(store.getId());
					
					ConceptualProduct CP = null;
					try {
						CP = BusinessObjectDAO.getInstance().searchForBO("ConceptualProduct", new SearchCriteria("sku", ppCPsku.getText()));
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					if(CP == null){
						Shell shell = new Shell();
						MessageBox message = new MessageBox(shell, SWT.ICON_WARNING);
						message.setText("Warning");
						message.setMessage("SKU is invalid");
						message.open();
					}else{
					
					PP.setConceptualproductid(CP.getId());
					PP.setProductname(ppName.getText());
					PP.setSerialnumber(ppSerialNumber.getText());
					PP.setShelflocation(ppShelfLocation.getText());
					PP.setCost(Double.parseDouble(ppCost.getText()));
					PP.setStatus("Active");
					PP.setType(ppType.getText());
					PP.setPhysicalproductcommissionrate(Double.parseDouble(ppCommissionRate.getText()));
					PP.setPrice(Double.parseDouble(ppPrice.getText()));
					PP.setType("Physical Product");
					

					try {
						BusinessObjectDAO.getInstance().save(PP);
					} catch (DataException e1) {
						e1.printStackTrace();
					}
					// Clear Out all fields
					ppCPsku.setText("");
					ppName.setText("");
					ppSerialNumber.setText("");
					ppShelfLocation.setText("");
					ppCost.setText("");
					ppType.setText("");
					ppCommissionRate.setText("");
					ppPrice.setText("");
					}
				    }
			}
		});
		btnAddPhysicalProduct.setText("Add Physical Product");

		Composite composite_2 = new Composite(shell, SWT.NONE);
		composite_2.setLayoutData(BorderLayout.WEST);
		RowLayout rl_composite_2 = new RowLayout(SWT.VERTICAL);
		rl_composite_2.center = true;
		composite_2.setLayout(rl_composite_2);

		Label lblConceptualProduct = new Label(composite_2, SWT.NONE);
		lblConceptualProduct.setText("Conceptual Product");

		Composite composite = new Composite(composite_2, SWT.NONE);
		composite.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label label = new Label(composite, SWT.NONE);
		label.setText("Product Name: ");

		cpName = new Text(composite, SWT.BORDER);

		Composite composite_4 = new Composite(composite_2, SWT.NONE);
		composite_4.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label lblNewLabel = new Label(composite_4, SWT.NONE);
		lblNewLabel.setText("Description: ");

		cpDescription = new Text(composite_4, SWT.BORDER);

		Composite composite_5 = new Composite(composite_2, SWT.NONE);
		composite_5.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label lblNewLabel_1 = new Label(composite_5, SWT.NONE);
		lblNewLabel_1.setText("Manufacturer: ");

		cpManufacturer = new Text(composite_5, SWT.BORDER);

		Composite composite_6 = new Composite(composite_2, SWT.NONE);
		composite_6.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label lblNewLabel_2 = new Label(composite_6, SWT.NONE);
		lblNewLabel_2.setText("Average Cost: ");

		cpAverageCost = new Text(composite_6, SWT.BORDER);

		Composite composite_7 = new Composite(composite_2, SWT.NONE);
		composite_7.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label lblNewLabel_3 = new Label(composite_7, SWT.NONE);
		lblNewLabel_3.setText("Commission Rate: ");

		cpCommissionRate = new Text(composite_7, SWT.BORDER);

		Composite composite_8 = new Composite(composite_2, SWT.NONE);
		composite_8.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label lblSku = new Label(composite_8, SWT.NONE);
		lblSku.setText("SKU:");

		cpSKU = new Text(composite_8, SWT.BORDER);

		Composite composite_17 = new Composite(composite_2, SWT.NONE);
		composite_17.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label lblPrice = new Label(composite_17, SWT.NONE);
		lblPrice.setText("Price: ");

		cpPrice = new Text(composite_17, SWT.BORDER);

		Composite composite_3 = new Composite(shell, SWT.NONE);
		composite_3.setLayoutData(BorderLayout.EAST);
		RowLayout rl_composite_3 = new RowLayout(SWT.VERTICAL);
		rl_composite_3.center = true;
		composite_3.setLayout(rl_composite_3);

		Label lblPhysicalProduct = new Label(composite_3, SWT.NONE);
		lblPhysicalProduct.setText("Physical Product");

		Composite composite_9 = new Composite(composite_3, SWT.NONE);
		composite_9.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label cpID = new Label(composite_9, SWT.NONE);
		cpID.setText("Conceptual Product SKU:");

		ppCPsku = new Text(composite_9, SWT.BORDER);

		Composite composite_11 = new Composite(composite_3, SWT.NONE);
		composite_11.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label lblProductName = new Label(composite_11, SWT.NONE);
		lblProductName.setText("Product Name: ");

		ppName = new Text(composite_11, SWT.BORDER);

		Composite composite_12 = new Composite(composite_3, SWT.NONE);
		composite_12.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label lblSerialNumber = new Label(composite_12, SWT.NONE);
		lblSerialNumber.setText("Serial Number:");

		ppSerialNumber = new Text(composite_12, SWT.BORDER);

		Composite composite_13 = new Composite(composite_3, SWT.NONE);
		composite_13.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label lblShelfLocation = new Label(composite_13, SWT.NONE);
		lblShelfLocation.setText("Shelf Location");

		ppShelfLocation = new Text(composite_13, SWT.BORDER);

		Composite composite_14 = new Composite(composite_3, SWT.NONE);
		composite_14.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label lblCost = new Label(composite_14, SWT.NONE);
		lblCost.setText("Cost: ");

		ppCost = new Text(composite_14, SWT.BORDER);

		Composite composite_15 = new Composite(composite_3, SWT.NONE);
		composite_15.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label lblNewLabel_6 = new Label(composite_15, SWT.NONE);
		lblNewLabel_6.setText("Type: ");

		ppType = new Text(composite_15, SWT.BORDER);

		Composite composite_16 = new Composite(composite_3, SWT.NONE);
		composite_16.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label lblNewLabel_7 = new Label(composite_16, SWT.NONE);
		lblNewLabel_7.setText("Comission Rate: ");

		ppCommissionRate = new Text(composite_16, SWT.BORDER);

		Composite composite_18 = new Composite(composite_3, SWT.NONE);
		composite_18.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label lblPrice_1 = new Label(composite_18, SWT.NONE);
		lblPrice_1.setText("Price: ");

		ppPrice = new Text(composite_18, SWT.BORDER);
	}
}
