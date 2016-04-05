package com.mhawkins.pricing;

/**
 * Buy One Get One Free Pricing.
 * 
 * @author Mark Hawkins
 *
 */
public class Bogof implements PriceStrategy {

	@Override
	public int getPricedQuantity(final int realQuantity) {
		return (realQuantity / 2) + realQuantity % 2;

	}

	@Override
	public String getInfo() {
		return "Buy One Get One Free.";
	}

}
