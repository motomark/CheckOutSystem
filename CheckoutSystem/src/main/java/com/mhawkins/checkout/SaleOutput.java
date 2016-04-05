package com.mhawkins.checkout;

import java.util.Map;

import com.mhawkins.store.ProductEnum;
import com.mhawkins.store.ProductSystem;

public interface SaleOutput {
	public void writeOutBasket(final Map<ProductEnum, Integer> basket,
			final ProductSystem productSystem);

}
