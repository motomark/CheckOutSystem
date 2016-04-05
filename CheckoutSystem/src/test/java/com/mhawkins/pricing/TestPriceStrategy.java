package com.mhawkins.pricing;

import org.junit.Assert;
import org.junit.Test;

import com.mhawkins.pricing.Bogof;
import com.mhawkins.pricing.Default;
import com.mhawkins.pricing.ThreeForTwo;

/**
 * Unit Test the Price Strategy Algorithms.
 * 
 * @author Mark Hawkins
 *
 */
public class TestPriceStrategy {

	/**
	 * Test the Default 1 for 1 strategy.
	 */
	@Test
	public void testDefault() {
		Default def = new Default();
		Assert.assertEquals("1 Should Yield 1", 1, def.getPricedQuantity(1));

		Assert.assertEquals("2 Should Yield 2", 2, def.getPricedQuantity(2));

		Assert.assertEquals("3 Should Yield 3", 3, def.getPricedQuantity(3));

		Assert.assertEquals("4 Should Yield 4", 4, def.getPricedQuantity(4));

	}

	/**
	 * Test the Buy One Get One Free Strategy.
	 */
	@Test
	public void testBogOff() {
		Bogof bogoff = new Bogof();

		Assert.assertEquals("2 Should Yield 1", 1, bogoff.getPricedQuantity(2));

		Assert.assertEquals("3 Should Yield 2", 2, bogoff.getPricedQuantity(3));

		Assert.assertEquals("4 Should Yield 2", 2, bogoff.getPricedQuantity(4));

		Assert.assertEquals("5 Should Yield 3", 3, bogoff.getPricedQuantity(5));

		Assert.assertEquals("6 Should Yield 3", 3, bogoff.getPricedQuantity(6));

		Assert.assertEquals("7 Should Yield 4", 4, bogoff.getPricedQuantity(7));

		Assert.assertEquals("8 Should Yield 4", 4, bogoff.getPricedQuantity(8));

	}

	/**
	 * Test the Three For Two Strategy.
	 */
	@Test
	public void testThreeForTwo() {
		ThreeForTwo tft = new ThreeForTwo();
		Assert.assertEquals("1 for 3 should yield 1", 1,
				tft.getPricedQuantity(1));
		Assert.assertEquals("2 for 3 should yield 2", 2,
				tft.getPricedQuantity(2));
		Assert.assertEquals("3 for 3 should yield 2", 2,
				tft.getPricedQuantity(3));
		Assert.assertEquals("6 for 3 should yield 4", 4,
				tft.getPricedQuantity(6));
		Assert.assertEquals("9 for 3 should yield 6", 6,
				tft.getPricedQuantity(9));
		Assert.assertEquals("10 for 3 should yield 7", 7,
				tft.getPricedQuantity(10));
	}

}
