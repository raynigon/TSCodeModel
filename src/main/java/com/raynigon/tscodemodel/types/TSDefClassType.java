package com.raynigon.tscodemodel.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.openmbean.KeyAlreadyExistsException;

public abstract class TSDefClassType implements TSType{

    private TSModuleDef module;
    private boolean exported = false;
    private Map<String, TSAttribute> attributes;
    protected List<TSMethod> methods;
    
    
    public TSDefClassType(TSModuleDef inModule){
        attributes = new HashMap<>();
        methods = new ArrayList<>();
        module = inModule;
    }
    
    public TSAttribute Attribute(String name, TSType type){
        TSAttribute attr = new TSAttribute(this, name, type);
        if(attributes.containsKey(name))
            throw new KeyAlreadyExistsException("An Attribute with this name already exists");
        attributes.put(name, attr);
        return attr;
    }

    public abstract TSMethod Method(String name, TSType returnType);
    
    public void setExport(boolean value){
        exported = value;
    }
    
    public boolean isExported(){
        return exported;
    }
        
    public Collection<TSAttribute> getAttributes(){
        return attributes.values();
    }
    
    public TSModuleDef getModule(){
        return module;
    }
    
	public Collection<TSMethod> getMethods() {
		return new ArrayList<>(methods);
	}
}
