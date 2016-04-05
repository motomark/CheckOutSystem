package com.mhawkins.pricing;

/**
 * Strategy Pattern Interface.
 * 
 * Realizers of this Interface will hold their specific pricing strategy
 * algorithm.
 * 
 * @author Mark Hawkins
 *
 */
public interface PriceStrategy {

	/**
	 * 
	 * @return String text description of the price strategy.
	 */
	String getInfo();

	/**
	 * 
	 * 
	 * @param realQuantity
	 *            int the real quantity scanned/selected.
	 * @return int - the adjusted quantity based on the Strategy.
	 */
	int getPricedQuantity(final int realQuantity);

}
