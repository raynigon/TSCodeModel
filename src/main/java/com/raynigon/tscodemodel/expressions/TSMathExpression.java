package com.raynigon.tscodemodel.expressions;

import com.raynigon.tscodemodel.types.TSExpression;
import com.raynigon.tscodemodel.types.TSSimpleType;
import com.raynigon.tscodemodel.types.TSType;

public class TSMathExpression implements TSExpression {

	public static final String ADD = "+";

	public static final String SUBSTRACT = "-";

	public static final String MULTIPLY = "*";

	public static final String DIVIDE = "/";

	public static final String MODULO = "%";
	
	private TSExpression x;
	private TSExpression y;
	private String operation;
	
	public TSMathExpression(TSExpression x, TSExpression y, String operation) {
		this.x = x;
		this.y = y;
		this.operation = operation;
	}

	public TSExpression getFirst(){
		return x;
	}

	public TSExpression getSecond(){
		return y;
	}
	
	public String getOperation() {
		return operation;
	}

	@Override
	public TSType ReturnType() {
		return TSSimpleType.NUMBER;
	}
}
