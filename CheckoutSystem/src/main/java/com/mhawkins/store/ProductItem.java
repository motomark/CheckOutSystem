package com.mhawkins.store;

/**
 * Value Object for individual Product Item e.g. Apple/Orange or anything.
 * 
 * @author Mark Hawkins.
 *
 */
public class ProductItem {

	/**
	 * Unique Product Id.
	 */
	private ProductEnum productId;

	/**
	 * Description text. for now this is the Enum name.
	 */
	private String description;

	/**
	 * The Unit Price for this Product.
	 */
	private float unitPrice;

	/**
	 * Constructor.
	 * 
	 * @param product
	 *            ProductEnum
	 * @param unitPrice
	 *            float
	 */
	public ProductItem(ProductEnum product, float unitPrice) {
		this.productId = product;
		this.description = product.name();
		this.unitPrice = unitPrice;

	}

	/**
	 *
	 * @return ProductEnum the product id.
	 */
	public ProductEnum getProductId() {
		return productId;
	}

	/**
	 * 
	 * @return float - return the unit price.
	 */
	public float getUnitPrice() {
		return unitPrice;
	}

	/**
	 * 
	 * @param description
	 *            String.
	 */
	public void setDescription(final String pDescription) {
		this.description = pDescription;
	}

	/**
	 * 
	 * @param pUnitPrice
	 *            .
	 */
	public void setUnitPrice(final float pUnitPrice) {
		this.unitPrice = pUnitPrice;
	}

	/**
	 * 
	 * @return String description.
	 */
	public String getDescription() {
		return description;
	}

}
