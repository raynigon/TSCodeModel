package com.raynigon.tscodemodel.builders.classes;

import java.io.PrintStream;

import com.raynigon.tscodemodel.types.TSAttribute;
import com.raynigon.tscodemodel.types.TSClassDef;
import com.raynigon.tscodemodel.types.TSMethod;

public interface TSClassCodeBuilder {

	public void buildClass(PrintStream ps, TSClassDef item, int indents);
	
	public String createClassHeader(TSClassDef item);
	
	public String createAttribute(TSAttribute item);
	
	public void buildMethod(PrintStream ps, TSMethod item, int indents);
	
	//public String createAccessor(TSAccessor item);
}
