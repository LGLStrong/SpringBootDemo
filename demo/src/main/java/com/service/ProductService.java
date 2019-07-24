package com.service;

import java.util.Collection;

import com.model.Product;

public interface ProductService {
	public abstract void createProduct(Product product);

	public abstract void updateProduct(String id, Product product);

	public abstract void deleteProduct(String id);

	public abstract Collection<Product> getProducts();
	
	/**
	 * Before Java 8, interfaces could have only abstract methods. The
	 * implementation of these methods has to be provided in a separate class. So,
	 * if a new method is to be added in an interface, then its implementation code
	 * has to be provided in the class implementing the same interface. To overcome
	 * this issue, Java 8 has introduced the concept of default methods which allow
	 * the interfaces to have methods with implementation without affecting the
	 * classes that implement the interface.
	 */
	default void test() {

	};
	
	/**
	 * since 1.8
	 */
	static int getInstance() {
		return 1;
	}
}
