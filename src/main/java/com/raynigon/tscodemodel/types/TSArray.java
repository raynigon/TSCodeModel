package com.raynigon.tscodemodel.types;

public class TSArray implements TSType{

    private TSType type;

    public TSArray(TSType arrayType){
        type = arrayType;
    }

    @Override
    public String getName(){
        return "Array<"+type.getName()+">";
    }

    @Override
    public String getModulePath(){
        return type.getModulePath();
    }

    public TSType getArrayType(){
        return type;
    }    
}
