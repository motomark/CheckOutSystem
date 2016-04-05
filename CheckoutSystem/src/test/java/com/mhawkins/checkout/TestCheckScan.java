package com.mhawkins.checkout;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mhawkins.pricing.Bogof;
import com.mhawkins.pricing.Default;
import com.mhawkins.pricing.PriceStrategy;
import com.mhawkins.pricing.ThreeForTwo;
import com.mhawkins.store.ProductEnum;
import com.mhawkins.store.ProductItem;
import com.mhawkins.store.ProductSystem;

/**
 * Perform some Scan Checks at different price options.
 * 
 * @author Mark Hawkins
 *
 */
public class TestCheckScan {

	private static ProductSystem ps;
	private static TillScan tillScan;

	/**
	 * Initialise the Product System.
	 */
	@BeforeClass
	public static void init() {
		ps = ProductSystem.getInstance();
		ps.addProduct(new ProductItem(ProductEnum.Orange, 0.25f));
		ps.addProduct(new ProductItem(ProductEnum.Apple, 0.60f));
	}

	/**
	 * Tear down between tests.
	 */
	@Before
	public void clearPricing() {
		ps.clearPricing();
		tillScan = new TillScan(ps);

	}

	/**
	 * Test the Default Pricing.
	 */
	@Test
	public void testDefault() {
		PriceStrategy defaultPrice = new Default();
		ps.addPricing(ProductEnum.Apple, defaultPrice);
		ps.addPricing(ProductEnum.Orange, defaultPrice);

		tillScan.start();
		tillScan.scan(ProductEnum.Apple);
		tillScan.scan(ProductEnum.Apple);
		tillScan.scan(ProductEnum.Orange);
		tillScan.scan(ProductEnum.Apple);

		Assert.assertEquals(
				"3 Apples at default price",
				String.format("%.2f",
						ps.getTotalProductPrice(ProductEnum.Apple, 3)), "1.80");

		Assert.assertEquals(
				"1 Orange at default price",
				String.format("%.2f",
						ps.getTotalProductPrice(ProductEnum.Orange, 1)), "0.25");

	}

	/**
	 * Test the BuyOnegetOneFree.
	 */
	@Test
	public void testBuyOneGetOfree() {
		PriceStrategy bogof = new Bogof();
		ps.addPricing(ProductEnum.Apple, bogof);
		ps.addPricing(ProductEnum.Orange, bogof);

		tillScan.start();
		tillScan.scan(ProductEnum.Apple);
		tillScan.scan(ProductEnum.Apple);
		tillScan.scan(ProductEnum.Orange);
		tillScan.scan(ProductEnum.Apple);

		Assert.assertEquals(
				"3 Apples at bogof price = 1.20",
				String.format("%.2f",
						ps.getTotalProductPrice(ProductEnum.Apple, 3)), "1.20");

		Assert.assertEquals(
				"1 Orange at bogof price = 0.25",
				String.format("%.2f",
						ps.getTotalProductPrice(ProductEnum.Orange, 1)), "0.25");

	}

	/**
	 * 
	 * Tests the 3 for 2.
	 */
	@Test
	public void testThreeForTwo() {
		PriceStrategy threeForTwo = new ThreeForTwo();
		ps.addPricing(ProductEnum.Apple, threeForTwo);
		ps.addPricing(ProductEnum.Orange, threeForTwo);

		tillScan.start();
		tillScan.scan(ProductEnum.Apple);
		tillScan.scan(ProductEnum.Apple);
		tillScan.scan(ProductEnum.Orange);
		tillScan.scan(ProductEnum.Apple);

		Assert.assertEquals(
				"3 Apples at Three For Two price = 1.20 ",
				String.format("%.2f",
						ps.getTotalProductPrice(ProductEnum.Apple, 3)), "1.20");

		Assert.assertEquals(
				"1 Orange at Three For Two price 0.25 ",
				String.format("%.2f",
						ps.getTotalProductPrice(ProductEnum.Orange, 1)), "0.25");

	}

}
