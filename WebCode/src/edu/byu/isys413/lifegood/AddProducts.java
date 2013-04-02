package edu.byu.isys413.lifegood;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
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

public class AddProducts extends Shell {
	private Text cpName;
	private Text cpDescription;
	private Text cpManufacturer;
	private Text cpAverageCost;
	private Text cpCommissionRate;
	private Text cpSKU;
	private Text ppCPID;
	private Text ppStoreID;
	private Text ppName;
	private Text ppSerialNumber;
	private Text ppShelfLocation;
	private Text ppCost;
	private Text ppType;
	private Text ppCommissionRate;
	private Text cpPrice;
	private Text ppPrice;

	/**
	 * Create the shell.
	 * @param display
	 */
	public AddProducts(Display display) {
		super(display, SWT.SHELL_TRIM);
		setLayout(new BorderLayout(0, 0));
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.SOUTH);
		composite_1.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		Button btnAddConceptualProduct = new Button(composite_1, SWT.NONE);
		btnAddConceptualProduct.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ConceptualProduct CP = null;
				try {
					CP = BusinessObjectDAO.getInstance().create("conceptualproduct");
				} catch (DataException e1) {
					// TODO Auto-generated catch block
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
				} catch (DataException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
								
			}
		});
		btnAddConceptualProduct.setText("Add Conceptual Product");
		
		Label lblNewLabel_4 = new Label(composite_1, SWT.NONE);
		
		Label lblNewLabel_5 = new Label(composite_1, SWT.NONE);
		lblNewLabel_5.setLayoutData(new RowData(142, SWT.DEFAULT));
		lblNewLabel_5.setText("             ");
		
		Button btnAddPhysicalProduct = new Button(composite_1, SWT.NONE);
		btnAddPhysicalProduct.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PhysicalProduct PP = null;
				try {
					PP = BusinessObjectDAO.getInstance().create("physicalproduct");
				} catch (DataException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PP.setStoreid(ppStoreID.getText());
				PP.setConceptualproductid(ppCPID.getText());
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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAddPhysicalProduct.setText("Add Physical Product");
		
		Composite composite_2 = new Composite(this, SWT.NONE);
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
		
		Composite composite_3 = new Composite(this, SWT.NONE);
		composite_3.setLayoutData(BorderLayout.EAST);
		RowLayout rl_composite_3 = new RowLayout(SWT.VERTICAL);
		rl_composite_3.center = true;
		composite_3.setLayout(rl_composite_3);
		
		Label lblPhysicalProduct = new Label(composite_3, SWT.NONE);
		lblPhysicalProduct.setText("Physical Product");
		
		Composite composite_9 = new Composite(composite_3, SWT.NONE);
		composite_9.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		Label cpID = new Label(composite_9, SWT.NONE);
		cpID.setText("Conceptual Product ID:");
		
		ppCPID = new Text(composite_9, SWT.BORDER);
		
		Composite composite_10 = new Composite(composite_3, SWT.NONE);
		composite_10.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		Label lblStoreId = new Label(composite_10, SWT.NONE);
		lblStoreId.setText("Store ID: ");
		
		ppStoreID = new Text(composite_10, SWT.BORDER);
		
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
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("SWT Application");
		setSize(450, 414);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
