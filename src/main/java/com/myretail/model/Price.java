package com.myretail.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * Price Resource
 * @author agyan2
 *
 */
@Document(collection="price")
public class Price {
	@JsonIgnore
	@Id
	private String priceId;
	@JsonIgnore
	private long id;
	
	private double value;
	
	private String currency_code;

	public Price() {
		super();
	}

	public Price(String priceId, long id, double value, String currency_code) {
		super();
		this.priceId = priceId;
		this.id=id;
		this.value = value;
		this.currency_code = currency_code;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getCurrency_code() {
		return currency_code;
	}

	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPriceId() {
		return priceId;
	}

	public void setPriceId(String priceId) {
		this.priceId = priceId;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Price [priceId=");
		builder.append(priceId);
		builder.append(", id=");
		builder.append(id);
		builder.append(", value=");
		builder.append(value);
		builder.append(", currency_code=");
		builder.append(currency_code);
		builder.append("]");
		return builder.toString();
	}

}
