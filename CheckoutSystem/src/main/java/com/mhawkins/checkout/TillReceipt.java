package com.mhawkins.checkout;

import java.util.Map;

import com.mhawkins.store.ProductEnum;
import com.mhawkins.store.ProductSystem;

/**
 * Write the Till receipt.
 * 
 * @author Mark Hawkins
 *
 */
public class TillReceipt implements SaleOutput {

	@Override
	public void writeOutBasket(final Map<ProductEnum, Integer> basket,
			final ProductSystem productSystem) {

		float totalCost = 0.0f;
		for (ProductEnum item : basket.keySet()) {
			Integer itemCount = basket.get(item);

			float totalProductPrice = productSystem.getTotalProductPrice(item,
					itemCount);

			System.out.print("Product Item:'"
					+ item
					+ "'\tQuantity:"
					+ itemCount
					+ "\tUnit Cost:"
					+ productSystem.getProductItem(item).getUnitPrice()
					+ "\tPriced Quantity:"
					+ productSystem.getProductPricing(item).getPricedQuantity(
							itemCount));

			System.out.format("\tTotal:%.2f", totalProductPrice);
			System.out.println(" "+productSystem.getProductPricing(item).getInfo());
			totalCost += totalProductPrice;

		}
		System.out.format("%nTotal: £%5.2f%n", totalCost);

	}

}
