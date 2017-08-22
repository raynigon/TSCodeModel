package com.raynigon.tscodemodel.builders.interfaces;

import java.io.PrintStream;

import com.raynigon.tscodemodel.types.TSAttribute;
import com.raynigon.tscodemodel.types.TSInterfaceDef;

public interface TSInterfaceCodeBuilder {

	void buildInterface(PrintStream ps, TSInterfaceDef type);

	public String createHeader(TSInterfaceDef item);
	
	public String createAttribute(TSAttribute item);
}
