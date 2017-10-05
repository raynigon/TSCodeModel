package com.raynigon.tscodemodel.expressions;

import com.raynigon.tscodemodel.types.TSExpression;
import com.raynigon.tscodemodel.types.TSType;
import com.raynigon.tscodemodel.types.TSVar;

public class TSAssignExpression implements TSExpression{

    private TSVar variable;
	private TSExpression expression;

	public TSAssignExpression(TSVar var, TSExpression expression){
    	this.variable = var;
    	this.expression = expression;
    }

	public TSVar getVariable() {
		return variable;
	}
	
	public TSExpression getAssignedValue(){
		return expression;
	}
	
	@Override
	public TSType ReturnType() {
		return this.variable.getType();
	}

}
