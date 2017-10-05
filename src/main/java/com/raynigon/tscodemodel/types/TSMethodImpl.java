package com.raynigon.tscodemodel.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.management.openmbean.KeyAlreadyExistsException;

public class TSMethodImpl implements TSMethod{

    private TSClassDef parent;
    private String name;
    private TSType returnType;
    private List<TSParam> params;
    private TSVisbility visbility;
    private TSMethodBlock codeSequence;
    
    public TSMethodImpl(TSClassDef parent, String name, TSType returnType){
        this.parent = parent;
        this.name = name;
        this.params = new ArrayList<>();
        this.returnType = returnType;
        this.visbility = TSVisbility.PUBLIC;
        this.codeSequence = new TSMethodBlock(this);
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
        return new ArrayList<>(params);
    }

    @Override
    public TSVisbility getVisbility(){
        return visbility;
    }

    @Override
    public void setVisbility(TSVisbility value){
        visbility = value;
    }

    @Override
    public TSParam Parameter(String name, TSType type){
        TSParam param = new TSParam(name, type, this);
        if(params.stream().anyMatch((item)->item.getName().equals(param.getName())))
            throw new KeyAlreadyExistsException("An Parameter with this name already exists");
        params.add(param);
        return param;
    }

    @Override
    public TSClassDef getParent(){
        return parent;
    }

    @Override
    public TSMethodBlock body(){
        return codeSequence;
    }

}
