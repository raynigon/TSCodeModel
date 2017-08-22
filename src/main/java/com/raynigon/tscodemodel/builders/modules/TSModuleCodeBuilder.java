package com.raynigon.tscodemodel.builders.modules;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import com.raynigon.tscodemodel.types.TSModuleDef;
import com.raynigon.tscodemodel.types.TSPackage;
import com.raynigon.tscodemodel.types.TSType;

public interface TSModuleCodeBuilder {

	public void createPackage(TSPackage pack) throws IOException;
	
	public PrintStream createModule(TSModuleDef module) throws IOException;
	
	public void flushModule(TSModuleDef module) throws IOException;

	public void createImports(List<TSType> usages, TSModuleDef item, PrintStream ps);
}
