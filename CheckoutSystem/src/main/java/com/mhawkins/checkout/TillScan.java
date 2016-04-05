package com.mhawkins.checkout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mhawkins.pricing.Bogof;
import com.mhawkins.pricing.PriceStrategy;
import com.mhawkins.pricing.ThreeForTwo;
import com.mhawkins.store.ProductEnum;
import com.mhawkins.store.ProductItem;
import com.mhawkins.store.ProductSystem;

/**
 * A Till Scanner perform scan functions.
 * 
 * @author Mark Hawkins.
 *
 */
public class TillScan {

	/**
	 * Reference to the main product system (database).
	 */
	private ProductSystem productSystem;

	/**
	 * Basket of goods scanned.
	 */
	private Map<ProductEnum, Integer> basket;

	/**
	 * List of Output Devices.
	 */
	private List<SaleOutput> outputList;

	/**
	 * Constructor. Inject a reference to the ProductSystem.
	 * 
	 * @param pProductSystem
	 *            ProductSystem
	 */
	public TillScan(final ProductSystem pProductSystem) {
		this.productSystem = pProductSystem;
		basket = new HashMap<ProductEnum, Integer>();
	}

	public void setOutputList(List<SaleOutput> outputList) {
		this.outputList = outputList;
	}

	/**
	 * Start a scan session. E.g. New customer.
	 */
	public void start() {
		// ensure the basket is clear.
		synchronized (basket) {
			basket.clear();
		}

	}

	/**
	 * Scan a Product Item and add to the basket.
	 * 
	 * @param itemName
	 *            ProductEnum the unique ID from the bar code scanner.
	 */
	public void scan(final ProductEnum itemName) {

		synchronized (basket) {

			if (basket.get(itemName) == null) {
				basket.put(itemName, 1);
			} else {
				Integer count = basket.get(itemName);
				count++;
				basket.put(itemName, count);
			}

		}

	}

	/**
	 * Called by the operator via a button on the till to end the scan session
	 * and total everything up.
	 */
	public void end() {

		// Decouple from the Output.
		Thread t = new Thread() {
			@Override
			public void run() {
				if (outputList != null) {
					for (SaleOutput out : outputList) {
						out.writeOutBasket(basket, productSystem);
					}

				} else {
					System.err.println("Cannot Output to device.");
				}

				synchronized (basket) {
					basket.clear();
				}
			}

		};

		t.start();

	}

	/**
	 * Main method to run the Scanner Application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// This uses our mocked version of the Product System.
		ProductSystem ps = ProductSystem.getInstance();
		PriceStrategy threeForTwo = new ThreeForTwo();
		PriceStrategy bogof = new Bogof();
		
		ps.addProduct(new ProductItem(ProductEnum.Orange, 0.25f));
		ps.addProduct(new ProductItem(ProductEnum.Apple, 0.60f));

		ps.addPricing(ProductEnum.Orange, threeForTwo);
		ps.addPricing(ProductEnum.Apple, bogof);

		List<SaleOutput> outputList = new ArrayList<SaleOutput>();
		outputList.add(new TillReceipt());

		// Till Scanner - composed of reference to Product System.
		TillScan tillScanner = new TillScan(ps);
		tillScanner.setOutputList(outputList);

		// Start the scan session.
		tillScanner.start();

		// Scan Products.
		tillScanner.scan(ProductEnum.Apple);
		tillScanner.scan(ProductEnum.Apple);

		tillScanner.scan(ProductEnum.Orange);

		tillScanner.scan(ProductEnum.Apple);

		// End the Scan Session.
		tillScanner.end();
	}

}
