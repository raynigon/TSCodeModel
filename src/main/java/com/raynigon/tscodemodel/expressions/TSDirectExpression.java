package com.raynigon.tscodemodel.expressions;

import com.raynigon.tscodemodel.types.TSExpression;
import com.raynigon.tscodemodel.types.TSType;

public class TSDirectExpression implements TSExpression {

	private String value;
	private TSType type;

	public TSDirectExpression(String value, TSType type) {
		this.value = value;
		this.type = type;
	}

	public String getValue() {
		return value;
	}
	
	@Override
	public TSType ReturnType() {
		return type;
	}

}
