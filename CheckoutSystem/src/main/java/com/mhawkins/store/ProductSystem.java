package com.mhawkins.store;

import java.util.HashMap;
import java.util.Map;

import com.mhawkins.pricing.PriceStrategy;

/**
 * Product System.
 * 
 * The Main Service layer for storing and retrieving Product information.
 * 
 * @author Mark
 *
 */
public class ProductSystem {

	/**
	 * Singleton Instance. We only have one product system.
	 */
	private static ProductSystem productSystem;

	/**
	 * Map database of Product Offers.
	 */
	private static Map<ProductEnum, PriceStrategy> pricing;

	/**
	 * Map database of ALL products.
	 */
	private static Map<ProductEnum, ProductItem> products;

	/**
	 * Construct the ProductSystem. private constructor - only ever one of
	 * these.
	 */
	private ProductSystem() {
		pricing = new HashMap<ProductEnum, PriceStrategy>();
		products = new HashMap<ProductEnum, ProductItem>();

	}

	/**
	 * Factory Singleton method.
	 * 
	 * @return ProductSystem
	 */
	public static ProductSystem getInstance() {
		if (productSystem == null) {
			productSystem = new ProductSystem();
		}
		return productSystem;
	}

	/**
	 * Add product to the database.
	 * 
	 * @param productItem
	 *            ProductItem
	 */
	public void addProduct(final ProductItem productItem) {
		products.put(productItem.getProductId(), productItem);
	}

	/**
	 * Remove Product from the database.
	 * 
	 * @param productItem
	 *            ProductItem
	 */
	public void removeProduct(final ProductItem productItem) {
		products.remove(productItem.getProductId());
	}

	/**
	 * Add Pricing info to the database.
	 * 
	 * This will be a Pricing Strategy.
	 * 
	 * @param productId
	 *            ProductEnum id
	 * @param priceStrategy
	 *            PriceStrategy - the pricing strategy.
	 */
	public void addPricing(final ProductEnum productId,
			final PriceStrategy priceStrategy) {
		pricing.put(productId, priceStrategy);
	}

	/**
	 * Remove pricnig info.
	 * 
	 * @param productId
	 *            ProductEnum id
	 */
	public void removePricing(final ProductEnum productId) {
		pricing.remove(productId);
	}

	/**
	 * Retrieve a Product from the database by id.
	 * 
	 * @param productId
	 *            ProductEnum
	 * @return ProductItem
	 */
	public ProductItem getProductItem(final ProductEnum productId) {
		return products.get(productId);
	}

	/**
	 * Retrieve a ProductOffer Price Strategy from the database via product Id.
	 * 
	 * @param productId
	 *            ProductEnum productId
	 * @return PriceStrategy
	 */
	public PriceStrategy getProductPricing(final ProductEnum productId) {
		return pricing.get(productId);
	}

	/**
	 * Calculate the total product price.
	 * 
	 * @param productId
	 *            ProductEnum
	 * @param count
	 *            int
	 * @return float
	 */
	public float getTotalProductPrice(final ProductEnum productId,
			final int count) {
		return productSystem.getProductPricing(productId).getPricedQuantity(
				count)
				* productSystem.getProductItem(productId).getUnitPrice();

	}

	public void clearPricing() {
		pricing.clear();
	}

}
