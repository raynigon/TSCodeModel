package com.raynigon.tscodemodel.types;

public enum TSVisbility {

	PRIVATE("private"),
	PROTECTED("protected"),
	PUBLIC("public");
	
	private String name;

	private TSVisbility(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
