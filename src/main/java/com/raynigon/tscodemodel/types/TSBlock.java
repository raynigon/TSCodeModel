package com.raynigon.tscodemodel.types;

import java.util.ArrayList;
import java.util.List;

public class TSBlock implements TSStatement{

    private TSClassDef parent;
    private List<TSStatement> statments;

    public TSBlock(TSClassDef parent){
        this.parent = parent;
        statments = new ArrayList<>();
    }
    
    @Override
    public TSStatement call(TSMethod method, TSVar... args){
        TSStatement result = new CallStatement(new TSThisType(parent), method, args);
        addStatement(result);
        return result;
    }

    @Override
    public TSStatement call(String methodName, TSVar... args){
        TSStatement result = new CallStatement(new TSThisType(parent), methodName, args);
        addStatement(result);
        return result;
    }
    
    public TSStatement assign(TSVar var, TSStatement statment){
        TSStatement result = new AssignStatement(var, statment);
        addStatement(result);
        return result;
    }
    
    public void addStatement(TSStatement statment){
        statments.add(statment);
    }

    public TSStatement toStatement(String value){
        // TODO Auto-generated method stub
        return null;
    }
    
    public TSStatement toStatement(int value){
        // TODO Auto-generated method stub
        return null;
    }
    
    public TSStatement toStatement(double value){
        // TODO Auto-generated method stub
        return null;
    }
}
