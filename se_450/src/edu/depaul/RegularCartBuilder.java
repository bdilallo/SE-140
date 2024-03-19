package edu.depaul;

import java.util.ArrayList;

public class RegularCartBuilder implements Cart_Builder{
	private String name;
	private ArrayList<Product> contents;
	private double priceTotal;

	@Override
	public Cart_Builder setName(String cartName) {
		this.name = cartName;
		return this;
	}

	@Override
	public Cart_Builder setContents(ArrayList<Product> cartContents) {
		this.contents = cartContents;
		return this;
	}

	@Override
	public Cart_Builder setTotal(double cartTotal) {
		this.priceTotal = cartTotal;
		return this;
	}

	@Override
	public Cart build() {
		return new Cart(name, contents, priceTotal);
	}

}
