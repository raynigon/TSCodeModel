package com.raynigon.tscodemodel.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TSModuleDef implements TSModule{

    private TSPackage pack;
    private String name;
    private Map<String, TSDefClassType> defTypes;
    
    public TSModuleDef(TSPackage inPackage, String inName){
        pack = inPackage;
        name = inName;
        defTypes = new HashMap<>();
    }
    
    public String getName(){
        return name;
    }
    
    public TSPackage getPackage(){
        return pack;
    }

    public TSClassDef Class(String name){
        if(defTypes.containsKey(name))
            throw new IllegalArgumentException("A Type with the given Name already exists in this Module");
        TSClassDef clazz = new TSClassDef(this, name);
        defTypes.put(name, clazz);
        return clazz;
    }
    
    public TSInterfaceDef Interface(String name){
        if(defTypes.containsKey(name))
            throw new IllegalArgumentException("A Type with the given Name already exists in this Module");
        TSInterfaceDef intf = new TSInterfaceDef(this, name);
        defTypes.put(name, intf);
        return intf;
    }

    public List<TSDefClassType> getDeclarations(){
        return new ArrayList<>(defTypes.values());
    }
    
    @Override
    public int hashCode(){
        String s = getPackage().getName()+"#"+getName();
        return s.hashCode();
    }
}
