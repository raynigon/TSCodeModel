package com.raynigon.tscodemodel.types;

import java.util.ArrayList;
import java.util.List;

public class TSClassDef extends TSDefClassType implements TSClass{

    private String name;
    private TSClass extended;
    private List<TSInterface> implemented;
	private boolean abstractClass;
    
    public TSClassDef(TSModuleDef inModule, String inName){
        super(inModule);
        if(inName==null)
            throw new NullPointerException();
        name = inName;
        implemented = new ArrayList<>();
    }
    
    public void Extend(TSClass extension){
        if(extension==null)
            throw new NullPointerException();
        extended = extension;
    }
        
    public void Implement(TSInterface intf){
        if(intf==null)
            throw new NullPointerException();
        implemented.add(intf);
    }
    
    public TSMethod Constructor(){
        TSMethod tsmethod = new TSMethodCtor(this);
        methods.add(tsmethod);
        return tsmethod;
    }
    
    public String getName(){
        return name;
    }

	public TSClass getExtension() {
		return extended;
	}

	public List<TSInterface> getImplementations() {
		return implemented;
	}

	public boolean isAbstract() {
		return abstractClass;
	}
    
	public void setAbstract(boolean inAbstractClass) {
		abstractClass = inAbstractClass;
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

    @Override
    public TSMethod Method(String name, TSType returnType){
        if(name==null)
            throw new NullPointerException();
        TSMethod method = new TSMethodImpl(this, name, returnType);
        methods.add(method);
        return method;
    }
}
