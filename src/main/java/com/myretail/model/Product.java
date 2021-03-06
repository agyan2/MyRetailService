package com.myretail.model;

/**
 * 
 * Product Model
 * @author agyan2
 *
 */
public class Product {

	private long id;

	private String name;

	private Price current_price;

	public Product() {
		super();
	}

	public Product(long id, String name, Price current_price) {
		super();
		this.id = id;
		this.name = name;
		this.current_price = current_price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Price getCurrent_price() {
		return current_price;
	}

	public void setCurrent_price(Price current_price) {
		this.current_price = current_price;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", current_price=");
		builder.append(current_price);
		builder.append("]");
		return builder.toString();
	}
}
