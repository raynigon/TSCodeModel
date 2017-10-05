package com.raynigon.tscodemodel.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.management.openmbean.KeyAlreadyExistsException;

public class TSMethodCtor implements TSMethod{

    private TSClassDef parent;
    private List<TSParam> params;
    private TSMethodBlock codeSequence;

    public TSMethodCtor(TSClassDef parent){
        this.parent = parent;
        params = new ArrayList<>();
        codeSequence = new TSMethodBlock(this);
    }
    
    @Override
    public String getName(){
        return "constructor";
    }

    @Override
    public TSType getReturnType(){
        return parent;
    }

    @Override
    public Collection<TSParam> getParams(){
        return params;
    }

    @Override
    public void setVisbility(TSVisbility value){
        if(!value.equals(TSVisbility.PUBLIC))
            throw new IllegalArgumentException("The Constructor has to be public");
        return;
    }

    @Override
    public TSVisbility getVisbility(){
        return TSVisbility.PUBLIC;
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
