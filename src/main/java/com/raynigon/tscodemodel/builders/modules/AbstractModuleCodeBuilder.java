package com.raynigon.tscodemodel.builders.modules;

import java.io.PrintStream;
import java.util.List;

import com.raynigon.tscodemodel.TSCodeModel;
import com.raynigon.tscodemodel.builders.PathConversionHelper;
import com.raynigon.tscodemodel.types.TSArray;
import com.raynigon.tscodemodel.types.TSModuleDef;
import com.raynigon.tscodemodel.types.TSSimpleType;
import com.raynigon.tscodemodel.types.TSType;

public abstract class AbstractModuleCodeBuilder implements TSModuleCodeBuilder{

    protected TSCodeModel codeModel;
    
    public AbstractModuleCodeBuilder(TSCodeModel inCodeModel){
        codeModel = inCodeModel;
    }
    
	@Override
	public void createImports(List<TSType> usages, TSModuleDef item, PrintStream ps) {
		//TODO add all used types of the same module into one import
	    usages.stream().map((in)->mapTypes(in, item)).distinct().
	    forEach((usage)->addImport(usage, item, ps));
	}

	private TSType mapTypes(TSType in, TSModuleDef item){
	    if(in instanceof TSArray)
	        in = ((TSArray) in).getArrayType();
	    if(in instanceof TSSimpleType)
	        throw new IllegalArgumentException("Incoming Type is a TSSimpleType");
	    String modulePath = PathConversionHelper.normalizeModulePath(in.getModulePath());
        modulePath = PathConversionHelper.convertToRelativePath(modulePath, item);
        return new TSTypeImpl(modulePath, in.getName());
	}
	
	private void addImport(TSType usage, TSModuleDef item, PrintStream ps){
        codeModel.getLogger().debug("Add Import %s from ", usage.getName(), usage.getModulePath());
        ps.println("import { "+usage.getName()+" } from '"+usage.getModulePath()+"';");
	}
	
	class TSTypeImpl implements TSType{

        private String modulePath;
        private String name;

        public TSTypeImpl(String modulePath, String name){
            this.modulePath = modulePath;
            this.name = name;
        }

        @Override
        public String getName(){
            return name;
        }

        @Override
        public String getModulePath(){
            return modulePath;
        }
	    
        @Override
        public int hashCode(){
            return (modulePath.hashCode()+name.hashCode())/2;
        }
        
        @Override
        public boolean equals(Object obj){
            if(!(obj instanceof TSType))
                return false;
            TSType other = (TSType) obj;
            if(!other.getModulePath().equals(modulePath))
                return false;
            if(!other.getName().equals(name))
                return false;
            return true;
        }
	}
}
