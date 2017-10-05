package com.raynigon.tscodemodel.types;

public interface TSVar extends TSExpression{

    String getName();
    
    TSType getType();
    
    TSExpression Call(String methodName, TSVar... params);
    
    TSExpression Assign(TSExpression value);
}
