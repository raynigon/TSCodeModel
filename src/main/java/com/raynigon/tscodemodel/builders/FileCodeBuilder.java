package com.raynigon.tscodemodel.builders;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import com.raynigon.tscodemodel.builders.classes.AbstractTSClassCodeBuilder;
import com.raynigon.tscodemodel.builders.classes.TSClassCodeBuilder;
import com.raynigon.tscodemodel.builders.interfaces.AbstractInterfaceCodeBuilder;
import com.raynigon.tscodemodel.builders.interfaces.TSInterfaceCodeBuilder;
import com.raynigon.tscodemodel.builders.modules.AbstractModuleCodeBuilder;
import com.raynigon.tscodemodel.builders.modules.TSModuleCodeBuilder;
import com.raynigon.tscodemodel.types.TSModuleDef;
import com.raynigon.tscodemodel.types.TSPackage;

public class FileCodeBuilder extends AbstractCodeBuilder{

    private Path root;
	private ModuleCodeBuilder moduleCodeBuilder;
	private ClassCodeBuilder classCodeBuilder;
	private InterfaceCodeBuilder intfCodeBuilder;
    
    public FileCodeBuilder(Path inRoot){
		moduleCodeBuilder = new ModuleCodeBuilder();
		classCodeBuilder = new ClassCodeBuilder();
		intfCodeBuilder = new InterfaceCodeBuilder();
        root = inRoot;
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
    
    public Path getRoot(){
        return root;
    }
    
    class ModuleCodeBuilder extends AbstractModuleCodeBuilder{

		@Override
		public void createPackage(TSPackage pack) throws IOException {
			Path folder = root.resolve(PathConversionHelper.normalizeModulePath(pack.getName()));
	        Files.createDirectories(folder);
		}

		@Override
		public PrintStream createModule(TSModuleDef module) throws IOException {
			Path packLoc = root.resolve(PathConversionHelper.normalizeModulePath(module.getPackage().getName()));
	        Path modulePath = packLoc.resolve(module+".ts");
	        Files.createFile(modulePath);
	        OutputStream os = new FileOutputStream(modulePath.toFile());
	        return new PrintStream(os, true, StandardCharsets.UTF_8.name());
		}

		@Override
		public void flushModule(TSModuleDef module) throws IOException {
			// TODO Auto-generated method stub
		}
    }
    
    public static class InterfaceCodeBuilder extends AbstractInterfaceCodeBuilder{}
    
    public static class ClassCodeBuilder extends AbstractTSClassCodeBuilder{}
}
