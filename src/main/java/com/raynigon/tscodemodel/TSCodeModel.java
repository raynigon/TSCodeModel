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
        build(new FileCodeBuilder(root));
    }
    
    public void build(TSCodeBuilder codebuilder) throws IOException{
        for(TSPackage item : packs){
            buildPackage(codebuilder, item);
        }
    }
    
    private void buildPackage(TSCodeBuilder codebuilder, TSPackage item) throws IOException{
        codebuilder.createFolder(item.getName());
        for(TSModuleDef module : item.getModules()){
            buildModule(codebuilder, item, module);    
        }
    }

    private void buildModule(TSCodeBuilder codebuilder, TSPackage pack, TSModuleDef item) throws IOException{
        PrintStream ps = codebuilder.createFile(pack.getName(), item.getName());
        codebuilder.buildModule(ps, item);
    }

    public static String getIndent(){
        return "    ";
    }
}
