package com.raynigon.tscodemodel.expressions;

import com.raynigon.tscodemodel.types.TSMethod;
import com.raynigon.tscodemodel.types.TSMethodDef;
import com.raynigon.tscodemodel.types.TSSimpleType;
import com.raynigon.tscodemodel.types.TSType;
import com.raynigon.tscodemodel.types.TSExpression;
import com.raynigon.tscodemodel.types.TSVar;

public class TSCallExpression implements TSExpression{

	private TSVar variable;
	private TSMethod method;
	private TSVar[] params;
	
    public TSCallExpression(TSVar var, TSMethod method, TSVar... args){
        this.method = method;
        this.variable = var;
        this.params = args;
    }

    public TSCallExpression(TSVar var, String methodName, TSVar... args){
        this.method = new TSMethodDef(null, methodName, TSSimpleType.ANY);
        this.variable = var;
        this.params = args;
    }

    public TSVar getVariable() {
		return variable;
	}
    
    public String getMethodName() {
		return method.getName();
	}
    
    public TSVar[] getParams() {
		return params;
	}
    
	@Override
	public TSType ReturnType() {
		return method.getReturnType();
	}

}
