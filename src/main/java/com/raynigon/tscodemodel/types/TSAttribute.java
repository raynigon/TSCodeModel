package com.raynigon.tscodemodel.types;

import java.util.Collections;
import java.util.Set;

import com.raynigon.tscodemodel.TSCodeModel;

public abstract class TSAttribute implements TSDefType{

    private TSDefType parent;
    private String name;
    private TSType type;

    public TSAttribute(TSDefType inParent, String inName, TSType inType){
        parent = inParent;
        name = inName;
        type = inType;
    }

    public String getName(){
        return name;
    }
    
    public TSType getType(){
        return type;
    }

    public TSDefType getParent(){
        return parent;
    }

    @Override
    public String getModulePath(){
        return parent.getModulePath();
    }

    @Override
    public Set<TSType> determineUsages(){
        return Collections.singleton(type);
    }

    @Override
    public String getIndent(){
        return TSCodeModel.getIndent() + parent.getIndent();
    }
}
