package com.mhawkins.pricing;

/**
 * Regular Pricing Strategy. No Adjust.
 * 
 * @author Mark Hawkins
 *
 */
public class Default implements PriceStrategy {

	@Override
	
	public int getPricedQuantity(final int realQuantity) {
		return realQuantity;
	}

	@Override
	public String getInfo() {
		return "";
	}

}
