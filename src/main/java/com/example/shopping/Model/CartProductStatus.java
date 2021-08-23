package com.example.shopping.Model;

public enum CartProductStatus {
ACTIVE("ACTIVE"), INACTIVE("INACTIVE");

	private final String value;
CartProductStatus(String value) {
	this.value=value;

}
public String getValue() {
	return value;
}
}
