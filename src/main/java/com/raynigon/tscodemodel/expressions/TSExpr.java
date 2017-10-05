package com.raynigon.tscodemodel.expressions;

import com.raynigon.tscodemodel.types.TSExpression;
import com.raynigon.tscodemodel.types.TSSimpleType;

public class TSExpr {

	public static TSExpression Add(TSExpression x, TSExpression y) {
		return new TSMathExpression(x,y, TSMathExpression.ADD);
	}
	
	public static TSExpression Substract(TSExpression x, TSExpression y) {
		return new TSMathExpression(x,y, TSMathExpression.SUBSTRACT);
	}
	
	public static TSExpression Multiply(TSExpression x, TSExpression y) {
		return new TSMathExpression(x,y, TSMathExpression.MULTIPLY);
	}
	
	public static TSExpression Divide(TSExpression x, TSExpression y) {
		return new TSMathExpression(x,y, TSMathExpression.DIVIDE);
	}
	
	public static TSExpression Modulo(TSExpression x, TSExpression y) {
		return new TSMathExpression(x,y, TSMathExpression.MODULO);
	}


	public static TSExpression Lit(int value) {
		return new TSDirectExpression(String.valueOf(value), TSSimpleType.NUMBER);
	}
	
	public static TSExpression Lit(boolean value) {
		return new TSDirectExpression(value?"True":"False", TSSimpleType.BOOLEAN);
	}
	
	public static TSExpression Lit(String value) {
		return new TSDirectExpression("\""+value+"\"", TSSimpleType.STRING);
	}
}
