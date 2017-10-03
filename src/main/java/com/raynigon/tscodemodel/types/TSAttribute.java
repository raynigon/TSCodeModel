package com.raynigon.tscodemodel.types;

public class TSAttribute implements TSType, TSVar{

    private TSDefClassType parent;
    private String name;
    private TSType type;
	private TSVisbility visbility;
	private boolean readonly;
	private boolean staticValue;

    public TSAttribute(TSDefClassType inParent, String inName, TSType inType){
        parent = inParent;
        name = inName;
        type = inType;
        visbility = TSVisbility.PUBLIC;
        readonly = false;
    }

    @Override
    public String getName(){
        return name;
    }
    
    @Override
    public TSType getType(){
        return type;
    }

    public TSDefClassType getParent(){
        return parent;
    }

    @Override
    public String getModulePath(){
        return parent.getModulePath();
    }

	public TSVisbility getVisibility() {
		return visbility;
	}
	
	public void setVisbility(TSVisbility inVisbility) {
		visbility = inVisbility;
	}

	public boolean isReadonly() {
		return readonly;
	}
	
	public void setReadonly(boolean inReadonly) {
		readonly = inReadonly;
	}

	public boolean isStatic() {
		return staticValue;
	}
	
	public void setStatic(boolean value) {
		staticValue = value;
	}
	
	@Override
	public boolean equals(Object obj) {
	   	if(!this.getClass().equals(obj.getClass()))
	   		return false;
	   	if(!getName().equals(((TSType) obj).getName()))
	   		return false;
	   	if(!getParent().equals(((TSAttribute) obj).getParent()))
	   		return false;
	   	return true;
	}

    @Override
    public TSStatement call(TSMethod method, TSVar... args){
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TSStatement call(String methodName, TSVar... args){
        // TODO Auto-generated method stub
        return null;
    }
}
