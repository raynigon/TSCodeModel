package com.raynigon.tscodemodel.builders.interfaces;

import java.io.PrintStream;

import com.raynigon.tscodemodel.types.TSAttribute;
import com.raynigon.tscodemodel.types.TSInterfaceDef;
import com.raynigon.tscodemodel.types.TSMethod;

public interface TSInterfaceCodeBuilder {

	void buildInterface(PrintStream ps, TSInterfaceDef type, int indents);

	public String createInterfaceHeader(TSInterfaceDef item);
	
	public String createAttribute(TSAttribute item);

	String createMethod(TSMethod item);
}
