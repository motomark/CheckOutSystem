package com.mhawkins.pricing;

/**
 * The Three for Two Strategy.
 * 
 * @author Mark Hawkins
 *
 */
public class ThreeForTwo implements PriceStrategy {

	@Override
	public int getPricedQuantity(final int realQuantity) {
		final int amount = realQuantity / 3;
		return realQuantity - amount;
	}

	@Override
	public String getInfo() {
		return "Three for Two.";
	}

}
