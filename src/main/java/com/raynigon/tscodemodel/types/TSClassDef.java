package com.raynigon.tscodemodel.types;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TSClassDef extends TSDefClassType implements TSClass{

    private TSModuleDef module;
    private String name;
    private TSClass extended;
    private List<TSInterface> implemented;
    
    public TSClassDef(TSModuleDef inModule, String inName){
        module = inModule;
        name = inName;
        implemented = new ArrayList<>();
    }
    
    public void Extend(TSClass extension){
        extended = extension;
    }
        
    public String getName(){
        return name;
    }
    
    public TSModuleDef getModule(){
        return module;
    }

    @Override
    public String getModulePath(){
        return module.getName();
    }

    @Override
    public void buildSelf(PrintStream ps){
        if(isExported())
            ps.print("export ");
        ps.print("class "+name+" ");
        if(extended!=null){
            ps.print("extends "+extended.getName()+" ");
        }
        if(implemented.size()>0){
            ps.print("implements ");
            List<String> names = implemented.stream().map((intf)->intf.getName()).collect(Collectors.toList());
            ps.print(String.join(",", names));
        }
        ps.println("{");
        buildAttributes(ps);
        //TODO Ctor and Methods 
        ps.println("}");
    }

    @Override
    public Set<TSType> determineUsages(){
        Set<TSType> types = new HashSet<>();
        if(extended!=null)
            types.add(extended);
        types.addAll(implemented);
        //TODO add all usages
        return types;
    }

    @Override
    public String getIndent(){
        return "";
    }

    @Override
    protected TSAttribute createAttribute(String name, TSType type){
        return new TSClassAttribute(this, name, type);
    }
    
}
