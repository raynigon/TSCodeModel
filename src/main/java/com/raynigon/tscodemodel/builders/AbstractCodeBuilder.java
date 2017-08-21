package com.raynigon.tscodemodel.builders;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.raynigon.tscodemodel.types.TSDefType;
import com.raynigon.tscodemodel.types.TSModuleDef;
import com.raynigon.tscodemodel.types.TSSimpleType;
import com.raynigon.tscodemodel.types.TSType;

public abstract class AbstractCodeBuilder implements TSCodeBuilder{

    @Override
    public void buildModule(PrintStream ps, TSModuleDef item){
        List<TSDefType> declarations = item.getDeclarations();
        List<TSType> usages = new ArrayList<>();
        for(TSDefType decl : declarations){
            usages.addAll(decl.determineUsages());
        }
        usages = usages.stream().filter((inType)->{
            return !declarations.stream().anyMatch((declType)->{
               return inType.getName().equals(declType.getName());
            });
        }).collect(Collectors.toList());
        usages = usages.stream().parallel().filter((usage)->!(usage instanceof TSSimpleType)).collect(Collectors.toList());
        writeUsages(ps, usages);
        for(TSDefType decl : declarations){
            decl.buildSelf(ps);
        }
    }
    
    protected void writeUsages(PrintStream ps, List<TSType> usages){
        //TODO add all used types of the same module into one import
        for(TSType usage : usages){
            String modulePath = normalizeModulePath(usage.getModulePath());
            ps.println("import { "+usage.getName()+" } from \""+modulePath+"\";");
        }
    }

    private String normalizeModulePath(String modulePath){
        if(!modulePath.startsWith(".")){
            if(modulePath.startsWith("/")){
                modulePath = "."+modulePath;
            }else{
                modulePath = "./"+modulePath;
            }
        }else{
            if(!modulePath.startsWith("./")){
                modulePath = "./" + modulePath.substring(1);
            }
        }
        if(modulePath.endsWith("/"))
            modulePath = modulePath.substring(0, modulePath.length()-1);
        return modulePath;
    }
}
