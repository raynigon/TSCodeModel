package com.raynigon.tscodemodel.types;

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
    public TSStatement call(TSMethod method, TSVar... args){
        return new CallStatement(this, method, args);
    }

    @Override
    public TSStatement call(String methodName, TSVar... args){
        return new CallStatement(this, methodName, args);
    }
}
