package com.raynigon.tscodemodel.expressions;

import com.raynigon.tscodemodel.types.TSClass;
import com.raynigon.tscodemodel.types.TSExpression;
import com.raynigon.tscodemodel.types.TSType;
import com.raynigon.tscodemodel.types.TSVar;

public class TSConstructExpression implements TSExpression{

	private TSClass clazz;
	
    public TSConstructExpression(TSClass clazz, TSVar[] args){
    	this.clazz = clazz;
    }

	@Override
	public TSType ReturnType() {
		return clazz;
	}

}
