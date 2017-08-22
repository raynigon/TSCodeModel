package com.raynigon.tscodemodel.builders;

import java.io.IOException;
import java.util.List;

import com.raynigon.tscodemodel.builders.classes.TSClassCodeBuilder;
import com.raynigon.tscodemodel.builders.interfaces.TSInterfaceCodeBuilder;
import com.raynigon.tscodemodel.builders.modules.TSModuleCodeBuilder;
import com.raynigon.tscodemodel.types.TSPackage;

public interface TSCodeBuilder{

	void build(List<TSPackage> packages) throws IOException;
	TSModuleCodeBuilder getModuleCodeBuilder();
	TSClassCodeBuilder getClassCodeBuilder();
	TSInterfaceCodeBuilder getInterfaceCodeBuilder();
}
