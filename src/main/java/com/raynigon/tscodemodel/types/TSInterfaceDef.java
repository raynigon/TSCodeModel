package com.raynigon.tscodemodel.types;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        return module.getName();
    }

    @Override
    public void buildSelf(PrintStream ps){
        if(isExported())
            ps.print("export ");
        ps.print("interface "+name+" ");
        if(extensions.size()>0){
            ps.print("implements ");
            List<String> names = extensions.stream().map((intf)->intf.getName()).collect(Collectors.toList());
            ps.print(String.join(",", names));
        }
        ps.println("{");
        buildAttributes(ps);
        //TODO Methods
        ps.println("}");
    }

    @Override
    public Set<TSType> determineUsages(){
        Set<TSType> types = new HashSet<>();
        types.addAll(extensions);
        Set<TSType> attrTypes = getAttributes().stream().map((attr)->attr.getType()).collect(Collectors.toSet());
        types.addAll(attrTypes);
        return types;
    }

    @Override
    public String getIndent(){
        return "";
    }

    @Override
    protected TSAttribute createAttribute(String name, TSType type){
        return new TSInterfaceAttribute(this, name, type);
    }
   
}
