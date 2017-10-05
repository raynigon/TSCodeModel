package com.raynigon.tscodemodel.types;

import com.raynigon.tscodemodel.expressions.TSCallExpression;

public class TSParam implements TSVar{

    private String name;
    private TSType type;
    private TSMethod method;

    public TSParam(String name, TSType type, TSMethod method){
        this.name = name;
        this.type = type;
        this.method = method;
    }
    
    public String getName(){
        return name;
    }
    
    public TSType getType(){
        return type;
    }
    
    public TSMethod getMethod(){
        return method;
    }

    @Override
    public TSExpression Call(String methodName, TSVar... args){
        return new TSCallExpression(this, methodName, args);
    }

	@Override
	public TSType ReturnType() {
		return type;
	}

	@Override
	public TSExpression Assign(TSExpression value) {
		// TODO Auto-generated method stub
		return null;
	}
}
