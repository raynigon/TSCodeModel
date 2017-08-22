package com.raynigon.tscodemodel.builders;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.raynigon.tscodemodel.builders.FileCodeBuilder.ClassCodeBuilder;
import com.raynigon.tscodemodel.builders.FileCodeBuilder.InterfaceCodeBuilder;
import com.raynigon.tscodemodel.builders.classes.TSClassCodeBuilder;
import com.raynigon.tscodemodel.builders.interfaces.TSInterfaceCodeBuilder;
import com.raynigon.tscodemodel.builders.modules.AbstractModuleCodeBuilder;
import com.raynigon.tscodemodel.builders.modules.TSModuleCodeBuilder;
import com.raynigon.tscodemodel.types.TSModuleDef;
import com.raynigon.tscodemodel.types.TSPackage;

public class StringCodeBuilder extends AbstractCodeBuilder{

    private Map<String, ByteArrayOutputStream> streams; 
	private ModuleCodeBuilder moduleCodeBuilder;
	private ClassCodeBuilder classCodeBuilder;
	private InterfaceCodeBuilder intfCodeBuilder;
	
    public StringCodeBuilder(){
        streams = new HashMap<>();
		moduleCodeBuilder = new ModuleCodeBuilder();
		classCodeBuilder = new ClassCodeBuilder();
		intfCodeBuilder = new InterfaceCodeBuilder();
    }

    @Override
    public TSModuleCodeBuilder getModuleCodeBuilder() {
    	return moduleCodeBuilder;
    }
    
    @Override
    public TSClassCodeBuilder getClassCodeBuilder() {
    	return classCodeBuilder;
    }
    
    @Override
    public TSInterfaceCodeBuilder getInterfaceCodeBuilder() {
    	return intfCodeBuilder;
    }
    
    public String getModuleText(String pack, String module){
        ByteArrayOutputStream bos = streams.get("./"+pack+"/"+module);
        return new String(bos.toByteArray(), StandardCharsets.UTF_8);
    }
    
    public class ModuleCodeBuilder extends AbstractModuleCodeBuilder{

		@Override
		public void createPackage(TSPackage pack) throws IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public PrintStream createModule(TSModuleDef module) throws IOException {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
	        streams.put(PathConversionHelper.normalizeModulePath(module.getPackage().getName())+"/"+module.getName(), bos);
	        return new PrintStream(bos, true, StandardCharsets.UTF_8.name());
		}

		@Override
		public void flushModule(TSModuleDef module) throws IOException {
			// TODO Auto-generated method stub
			
		}    	
    }
}
