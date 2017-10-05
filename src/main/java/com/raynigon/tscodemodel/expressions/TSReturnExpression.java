package com.raynigon.tscodemodel.expressions;

import com.raynigon.tscodemodel.types.TSExpression;
import com.raynigon.tscodemodel.types.TSSimpleType;
import com.raynigon.tscodemodel.types.TSType;

public class TSReturnExpression implements TSExpression {

	private TSExpression value;

	public TSReturnExpression(TSExpression value) {
		this.value = value;
	}

	public TSExpression getReturnValue(){
		return value;
	}
	
	@Override
	public TSType ReturnType() {
		return TSSimpleType.VOID;
	}

}
