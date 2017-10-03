package com.raynigon.tscodemodel.types;

final class TSThisType implements TSVar{
    
    private TSType type;
    
    public TSThisType(TSClassDef parent){
        type = parent;
    }

    @Override
    public TSStatement call(String methodName, TSVar... args){
        return new CallStatement(this, methodName, args);
    }

    @Override
    public TSStatement call(TSMethod method, TSVar... args){
        return new CallStatement(this, method, args);
    }

    @Override
    public TSType getType(){
        return type;
    }

    @Override
    public String getName(){
        return "this";
    }
}