package com.raynigon.tscodemodel.types;

import java.util.ArrayList;
import java.util.List;

public class TSModuleDef implements TSModule{

    private TSPackage pack;
    private String name;
    private List<TSDefClassType> defTypes;
    
    public TSModuleDef(TSPackage inPackage, String inName){
        pack = inPackage;
        if(inName==null)
            throw new NullPointerException();
        name = inName;
        defTypes = new ArrayList<>();
    }
    
    public String getName(){
        return name;
    }
    
    public TSPackage getPackage(){
        return pack;
    }

    public TSClassDef Class(String name){
        if(name==null)
            throw new NullPointerException();
        if(defTypes.stream().anyMatch((item)->item.getName().equals(name)))
            throw new IllegalArgumentException("A Type with the given Name already exists in this Module");
        TSClassDef clazz = new TSClassDef(this, name);
        defTypes.add(clazz);
        return clazz;
    }
    
    public TSInterfaceDef Interface(String name){
        if(name==null)
            throw new NullPointerException();
        if(defTypes.stream().anyMatch((item)->item.getName().equals(name)))
            throw new IllegalArgumentException("A Type with the given Name already exists in this Module");
        TSInterfaceDef intf = new TSInterfaceDef(this, name);
        defTypes.add(intf);
        return intf;
    }

    public TSEnumDef Enum(String name){
        if(name==null)
            throw new NullPointerException();
        TSEnumDef result = new TSEnumDef(this, name);
        defTypes.add(result);
        return result;
    }
    
    public List<TSDefClassType> getDeclarations(){
        return new ArrayList<>(defTypes);
    }
    
    @Override
    public int hashCode(){
        String s = getPackage().getName()+"#"+getName();
        return s.hashCode();
    }

}
