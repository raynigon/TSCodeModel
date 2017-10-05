package com.raynigon.tscodemodel.types;

import java.util.ArrayList;
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
        this.params = new ArrayList<>();
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
        TSParam param = new TSParam(name, type, this);
        params.add(param);
        return param;
    }

    @Override
    public TSDefClassType getParent(){
        return parent;
    }

    @Override
    public TSMethodBlock body(){
        return null;
    }

}
