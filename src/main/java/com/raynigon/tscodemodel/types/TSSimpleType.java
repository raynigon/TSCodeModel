package com.raynigon.tscodemodel.types;

public class TSSimpleType implements TSType{

    public static final TSSimpleType BOOLEAN = new TSSimpleType("boolean");
    public static final TSSimpleType NUMBER = new TSSimpleType("number");
    public static final TSSimpleType STRING = new TSSimpleType("string");
    public static final TSSimpleType ANY = new TSSimpleType("any");
    public static final TSSimpleType VOID = new TSSimpleType("void");
    
    private String name;
    
    private TSSimpleType(String inName){
        name = inName;
    }
    
    @Override
    public String getName(){
        return name;
    }

    @Override
    public String getModulePath(){
        return null;
    }

    @Override
    public boolean equals(Object obj) {
    	if(!this.getClass().equals(obj.getClass()))
    		return false;
    	if(!getName().equals(((TSSimpleType) obj).getName()))
    		return false;
    	return true;
    }
}
