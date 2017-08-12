package com.raynigon.tscodemodel.types;

import java.io.PrintStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class TSDefClassType implements TSDefType{

    private boolean exported = false;
    private Map<String, TSAttribute> attributes;
    
    public TSDefClassType(){
        attributes = new HashMap<>();
    }
    
    public TSAttribute Attribute(String name, TSType type){
        TSAttribute attr = createAttribute(name, type);
        attributes.put(name, attr);
        return attr;
    }

    public void setExport(boolean value){
        exported = value;
    }
    
    public boolean isExported(){
        return exported;
    }
    
    protected void buildAttributes(PrintStream ps){
        for(TSAttribute attr : attributes.values()){
            attr.buildSelf(ps);
        }
    }
    
    public Collection<TSAttribute> getAttributes(){
        return attributes.values();
    }
    
    protected abstract TSAttribute createAttribute(String name, TSType type);
    //protected abstract TSMethod createMethod(String name, TSType type);
}
