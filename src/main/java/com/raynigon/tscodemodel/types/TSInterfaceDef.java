package com.raynigon.tscodemodel.types;

import java.util.ArrayList;
import java.util.List;

public class TSInterfaceDef extends TSDefClassType implements TSInterface{

    private TSModuleDef module;
    private String name;
    private List<TSInterface> extensions;
    
    public TSInterfaceDef(TSModuleDef inModule, String inName){
        module = inModule;
        name = inName;
        extensions = new ArrayList<>();
    }
    
    public TSModuleDef getModule(){
        return module;
    }
    
    public String getName(){
        return name;
    }
    
    @Override
    public String getModulePath(){
        return "./"+module.getPackage().getName()+"/"+module.getName();
    }

    /*@Override
    public void buildSelf(PrintStream ps){
        if(isExported())
            ps.print("export ");
        ps.print("interface "+name+" ");
        if(extensions.size()>0){
            ps.print("extends ");
            List<String> names = extensions.stream().map((intf)->intf.getName()).collect(Collectors.toList());
            ps.print(String.join(",", names));
        }
        ps.println("{");
        buildAttributes(ps);
        //TODO Methods
        ps.println("}");
    }*/
    
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
