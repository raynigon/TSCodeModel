package com.raynigon.tscodemodel.builders;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.raynigon.tscodemodel.types.TSArray;
import com.raynigon.tscodemodel.types.TSAttribute;
import com.raynigon.tscodemodel.types.TSClassDef;
import com.raynigon.tscodemodel.types.TSDefClassType;
import com.raynigon.tscodemodel.types.TSEnumDef;
import com.raynigon.tscodemodel.types.TSInterfaceDef;
import com.raynigon.tscodemodel.types.TSMethod;
import com.raynigon.tscodemodel.types.TSMethodBlock;
import com.raynigon.tscodemodel.types.TSModuleDef;
import com.raynigon.tscodemodel.types.TSPackage;
import com.raynigon.tscodemodel.types.TSType;

public abstract class AbstractCodeBuilder implements TSCodeBuilder{
		
	@Override
	public void build(List<TSPackage> packages) throws IOException {
		//TODO sort Packages
		for(TSPackage pack : packages){
			getModuleCodeBuilder().createPackage(pack);
			buildModules(pack.getModules());
		}
	}
	
    private void buildModules(List<TSModuleDef> modules) throws IOException {
    	for(TSModuleDef module : modules){
			PrintStream ps = getModuleCodeBuilder().createModule(module);
			buildImports(module, ps);
			buildClassTypes(module.getDeclarations(), ps);
			
			getModuleCodeBuilder().flushModule(module);
		}
	}

	private void buildImports(TSModuleDef module, PrintStream ps) {
		List<TSDefClassType> declarations = module.getDeclarations();
        List<TSType> usages = new ArrayList<>();
        for(TSDefClassType decl : declarations){
            usages.addAll(determineUsages(decl));
        }
        usages = usages.stream()
                .map(this::mapArrayTypes)
                .filter((inType)->isDuplicated(inType, declarations))
                .filter(FilterHelper::isNotSimpleType)
                .distinct()
                .sorted(FilterHelper::compareTypes)
                .collect(Collectors.toList());
        getModuleCodeBuilder().createImports(usages, module, ps);
		if(!module.getDeclarations().isEmpty() && !usages.isEmpty())
			ps.println();
	}
	
	private TSType mapArrayTypes(TSType in){
	    if(in instanceof TSArray){
	        return mapArrayTypes(((TSArray) in).getArrayType());
	    }
	    return in;
	}

	private boolean isDuplicated(TSType inType, List<TSDefClassType> declarations){
        return !declarations.stream().anyMatch((declType)->{
            return inType.getName().equals(declType.getName());
         });
     }
	
	private Collection<TSType> determineUsages(TSDefClassType decl) {
		List<TSType> usages = new ArrayList<>();
		for(TSAttribute attr : decl.getAttributes())
			usages.add(attr.getType());
		for(TSMethod method : decl.getMethods())
			usages.addAll(determineUsages(method));
		if(decl instanceof TSClassDef){
			TSClassDef clazz = (TSClassDef) decl;
			if(clazz.getExtension()!=null)
				usages.add(clazz.getExtension());
			if(clazz.getImplementations().size()>0)
				usages.addAll(clazz.getImplementations());
		}else if(decl instanceof TSInterfaceDef){
			TSInterfaceDef clazz = (TSInterfaceDef) decl;
			if(clazz.getExtensions().size()>0)
				usages.addAll(clazz.getExtensions());
		}else if(decl instanceof TSEnumDef){
		    //TSEnumDef tsenum = (TSEnumDef) decl;
		    // TODO there should not be any usages by enums!
		}else{
			throw new RuntimeException("Unknown Type");
		}
		return usages;
	}

	private Collection<TSType> determineUsages(TSMethod method) {
		List<TSType> types = new ArrayList<>();
		types.add(method.getReturnType());
		types.addAll(method.getParams().stream().map((item)->item.getType()).collect(Collectors.toList()));
		if(method.body()!=null)
			types.addAll(determineUsages(method.body()));
		return types;
	}

	private Collection<TSType> determineUsages(TSMethodBlock body) {
		List<TSType> types = new ArrayList<>();
		//TODO
		return types;
	}

	private void buildClassTypes(List<TSDefClassType> declarations, PrintStream ps) {
		Collections.sort(declarations, FilterHelper::compareClassTypes);
		for(TSDefClassType type : declarations){
			if(type instanceof TSClassDef){
				getClassCodeBuilder().buildClass(ps, (TSClassDef) type, 0);
			}else if(type instanceof TSInterfaceDef){
				getInterfaceCodeBuilder().buildInterface(ps, (TSInterfaceDef) type, 0);
			}else if(type instanceof TSEnumDef){
			    getEnumCodeBuilder().buildEnum(ps, (TSEnumDef) type, 0);
			}else{
				throw new RuntimeException("Unknown Class Type");
			}
			if(declarations.get(declarations.size()-1)!=type)
				ps.println();				
		}
	}
}
