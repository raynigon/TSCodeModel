package com.raynigon.tscodemodel.types;

public class CallStatement implements TSStatement{

    public CallStatement(TSVar var, TSMethod method, TSVar... args){
        // TODO Auto-generated constructor stub
    }

    public CallStatement(TSVar var, String methodName, TSVar... args){
        // TODO Auto-generated constructor stub
    }

    @Override
    public TSStatement call(TSMethod method, TSVar... args){
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TSStatement call(String methodName, TSVar... args){
        // TODO Auto-generated method stub
        return null;
    }

}
