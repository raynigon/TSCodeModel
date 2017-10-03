package com.raynigon.tscodemodel.types;

import java.util.ArrayList;
import java.util.List;

public class TSInterfaceDef extends TSDefClassType implements TSInterface{

    private String name;
    private List<TSInterface> extensions;
    
    public TSInterfaceDef(TSModuleDef inModule, String inName){
        super(inModule);
        name = inName;
        extensions = new ArrayList<>();
    }
    
    public String getName(){
        return name;
    }
    
    @Override
    public String getModulePath(){
        return "./"+getModule().getPackage().getName()+"/"+getModule().getName();
    }
    
    @Override
    public TSMethod Method(String name, TSType returnType){
        return new TSMethodDef(this, name, returnType);
    }   
    
    public void Extend(TSInterface extension){
        extensions.add(extension);
    }

	public List<TSInterface> getExtensions() {
		return extensions;
	}
	
	@Override
	public boolean equals(Object obj) {
	   	if(!this.getClass().equals(obj.getClass()))
	   		return false;
	   	if(!getName().equals(((TSType) obj).getName()))
	   		return false;
	   	if(!getModulePath().equals(((TSType) obj).getModulePath()))
	   		return false;
	   	return true;
	}
}
