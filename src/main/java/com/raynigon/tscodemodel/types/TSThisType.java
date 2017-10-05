package com.raynigon.tscodemodel.types;

import com.raynigon.tscodemodel.expressions.TSCallExpression;

final class TSThisType implements TSVar{
    
    private TSType type;
    
    public TSThisType(TSClassDef parent){
        type = parent;
    }

    @Override
    public TSExpression Call(String methodName, TSVar... args){
        return new TSCallExpression(this, methodName, args);
    }

    public TSExpression call(TSMethod method, TSVar... args){
        return new TSCallExpression(this, method, args);
    }

    @Override
    public TSType getType(){
        return type;
    }

    @Override
    public String getName(){
        return "this";
    }

	@Override
	public TSType ReturnType() {
		return type;
	}

	@Override
	public TSExpression Assign(TSExpression value) {
		throw new IllegalArgumentException("Its not allowed to assign something to the this pointer");
	}
}