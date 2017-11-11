package com.raynigon.tscodemodel.types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.raynigon.tscodemodel.TSCodeModel;

public class TSPackage{

    private TSCodeModel codeModel;
    private String name;
    private List<TSModuleDef> modules;
    
    public TSPackage(TSCodeModel inCodeModel, String inName){
        if(inName==null)
            throw new NullPointerException();
        codeModel = inCodeModel;
        name = inName;
        modules = new ArrayList<>();
    }

    public TSPackage Package(String name){
        return new TSPackage(codeModel, getName()+"/"+name);
    }    
    
    public TSModuleDef Module(String name){
        TSModuleDef module = new TSModuleDef(this, name);
        modules.add(module);
        return module;
    }
    
    public TSClassDef Class(String name){
        TSModuleDef module = Module(name);
        TSClassDef clazz = module.Class(name);
        clazz.setExport(true);
        return clazz;
    }
    
    public TSInterfaceDef Interface(String name){
        TSModuleDef module = Module(name);
        TSInterfaceDef intf = module.Interface(name);
        intf.setExport(true);
        return intf;
    }
    
    public TSEnumDef Enum(String name){
        TSModuleDef module = Module(name);
        TSEnumDef tsenum = module.Enum(name);
        tsenum.setExport(true);
        return tsenum;
    }
    
    public String getName(){
        return name;
    }

    public List<TSModuleDef> getModules(){
        return Collections.unmodifiableList(modules);
    }


}
