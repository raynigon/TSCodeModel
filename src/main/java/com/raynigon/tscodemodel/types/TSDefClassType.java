package com.raynigon.tscodemodel.types;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class TSDefClassType implements TSType{

    private boolean exported = false;
    private Map<String, TSAttribute> attributes;
    
    public TSDefClassType(){
        attributes = new HashMap<>();
    }
    
    public TSAttribute Attribute(String name, TSType type){
        TSAttribute attr = new TSAttribute(this, name, type);
        attributes.put(name, attr);
        return attr;
    }

    public void setExport(boolean value){
        exported = value;
    }
    
    public boolean isExported(){
        return exported;
    }
        
    public Collection<TSAttribute> getAttributes(){
        return attributes.values();
    }
}
