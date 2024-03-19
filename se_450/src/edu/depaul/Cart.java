package edu.depaul;

import java.util.ArrayList;

public class Cart {
	private String name;
	private ArrayList<Product> contents;
	private double priceTotal;
	
	public Cart(String name, ArrayList<Product> contents, double priceTotal) {
		this.name = name;
		this.contents = contents;
		this.priceTotal = priceTotal;
	}

	@Override
	public String toString() {
		return "Cart [name=" + name + ", contents=" + contents + ", priceTotal=" + priceTotal + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Product> getContents() {
		return contents;
	}

	public void setContents(ArrayList<Product> contents) {
		this.contents = contents;
	}

	public double getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(double priceTotal) {
		this.priceTotal = priceTotal;
	}
	
}
