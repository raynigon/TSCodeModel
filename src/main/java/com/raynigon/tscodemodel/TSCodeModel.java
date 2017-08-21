package com.raynigon.tscodemodel;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.raynigon.tscodemodel.builders.FileCodeBuilder;
import com.raynigon.tscodemodel.builders.TSCodeBuilder;
import com.raynigon.tscodemodel.types.TSModuleDef;
import com.raynigon.tscodemodel.types.TSPackage;
import com.raynigon.tscodemodel.types.TSType;
import com.raynigon.tscodemodel.types.hidden.TSRefClass;
import com.raynigon.tscodemodel.types.hidden.TSRefInterface;

public class TSCodeModel{

    private List<TSPackage> packs;
    
    public TSCodeModel(){
        packs = new ArrayList<>();
    }
    
    public TSPackage Package(String name){
        TSPackage pack = new TSPackage(this, name);
        packs.add(pack);
        return pack;
    }    
    
    public void build(Path root) throws IOException{
        System.out.println("Build Path");
        build(new FileCodeBuilder(root));
    }
    
    public void build(TSCodeBuilder codebuilder) throws IOException{
        for(TSPackage item : packs){
            buildPackage(codebuilder, item);
        }
    }
    
    private void buildPackage(TSCodeBuilder codebuilder, TSPackage item) throws IOException{
        codebuilder.createFolder(item.getName());
        System.out.println("Create Folder:"+item.getName());
        System.out.println("Modules:"+item.getModules().size());
        for(TSModuleDef module : item.getModules()){
            buildModule(codebuilder, item, module);    
        }
    }

    private void buildModule(TSCodeBuilder codebuilder, TSPackage pack, TSModuleDef item) throws IOException{
        PrintStream ps = codebuilder.createFile(pack.getName(), item.getName());
        System.out.println("Create Module:"+item.getName());
        codebuilder.buildModule(ps, item);
    }

    public static String getIndent(){
        return "    ";
    }

    public TSType ReferenceClass(TSPackage modelpack, String moduleName, String className){
        return new TSRefClass(modelpack.getName()+"/"+moduleName, className);
    }

    public TSType ReferenceInterface(TSPackage modelpack, String moduleName, String interfaceName){
        return new TSRefInterface(modelpack.getName()+"/"+moduleName, interfaceName);
    }
}
