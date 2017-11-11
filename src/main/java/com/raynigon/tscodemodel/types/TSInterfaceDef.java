package com.raynigon.tscodemodel.types;

import java.util.ArrayList;
import java.util.List;

public class TSInterfaceDef extends TSDefClassType implements TSInterface{

    private String name;
    private List<TSInterface> extensions;
    
    public TSInterfaceDef(TSModuleDef inModule, String inName){
        super(inModule);
        if(inName==null)
            throw new NullPointerException();
        name = inName;
        extensions = new ArrayList<>();
    }
    
    public String getName(){
        return name;
    }
    
    @Override
    public TSMethod Method(String name, TSType returnType){
        TSMethod result = new TSMethodDef(this, name, returnType);
        methods.add(result);
        return result;
    }   
    
    public void Extend(TSInterface extension){
        if(extension==null)
            throw new NullPointerException();
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
