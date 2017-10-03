package com.raynigon.tscodemodel.builders.classes;

import java.io.PrintStream;

import com.raynigon.tscodemodel.types.TSAttribute;
import com.raynigon.tscodemodel.types.TSClassDef;
import com.raynigon.tscodemodel.types.TSMethod;

public interface TSClassCodeBuilder {

	public void buildClass(PrintStream ps, TSClassDef item);
	
	public String createHeader(TSClassDef item);
	
	public String createAttribute(TSAttribute item);
	
	public void buildMethod(PrintStream ps, TSMethod item);
	
	//public String createAccessor(TSAccessor item);
}
