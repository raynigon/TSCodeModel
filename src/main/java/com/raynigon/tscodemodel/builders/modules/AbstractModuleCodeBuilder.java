package com.raynigon.tscodemodel.builders.modules;

import java.io.PrintStream;
import java.util.List;

import com.raynigon.tscodemodel.TSCodeModel;
import com.raynigon.tscodemodel.builders.PathConversionHelper;
import com.raynigon.tscodemodel.types.TSModuleDef;
import com.raynigon.tscodemodel.types.TSType;

public abstract class AbstractModuleCodeBuilder implements TSModuleCodeBuilder{

    protected TSCodeModel codeModel;
    
    public AbstractModuleCodeBuilder(TSCodeModel inCodeModel){
        codeModel = inCodeModel;
    }
    
	@Override
	public void createImports(List<TSType> usages, TSModuleDef item, PrintStream ps) {
		//TODO add all used types of the same module into one import
        for(TSType usage : usages){
            String modulePath = PathConversionHelper.normalizeModulePath(usage.getModulePath());
            modulePath = PathConversionHelper.convertToRelativePath(modulePath, item);
            codeModel.getLogger().debug("Add Import %s from ", usage.getName(), modulePath);
            ps.println("import { "+usage.getName()+" } from '"+modulePath+"';");
        }
	}

}
