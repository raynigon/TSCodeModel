package com.raynigon.tscodemodel.types;

import java.util.Collection;
import java.util.List;

public class TSMethodDef implements TSMethod{

    private TSInterfaceDef parent;
    private String name;
    private TSType returnType;
    private List<TSParam> params;

    public TSMethodDef(TSInterfaceDef parent, String name, TSType returnType){
        this.parent = parent;
        this.name = name;
        this.returnType = returnType;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public TSType getReturnType(){
        return returnType;
    }

    @Override
    public Collection<TSParam> getParams(){
        return params;
    }

    @Override
    public void setVisbility(TSVisbility value){
        if(!value.equals(TSVisbility.PUBLIC))
            throw new IllegalArgumentException("Interface Methods have to be Public");
        return;
    }

    @Override
    public TSVisbility getVisbility(){
        return TSVisbility.PUBLIC;
    }

    @Override
    public TSParam Parameter(String name, TSType type){
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TSDefClassType getParent(){
        return parent;
    }

    @Override
    public TSBlock body(){
        return null;
    }

}
