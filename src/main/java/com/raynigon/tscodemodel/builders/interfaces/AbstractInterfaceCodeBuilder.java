package com.raynigon.tscodemodel.builders.interfaces;

import java.io.PrintStream;
import java.util.stream.Collectors;

import com.raynigon.tscodemodel.TSCodeModel;
import com.raynigon.tscodemodel.types.TSAttribute;
import com.raynigon.tscodemodel.types.TSInterfaceDef;

public class AbstractInterfaceCodeBuilder implements TSInterfaceCodeBuilder {

	private static final String ATTRIBUTE_FORMAT = "%s%s: %s;";

	@Override
	public void buildInterface(PrintStream ps, TSInterfaceDef item) {
		String indent = TSCodeModel.getIndent();
		ps.println(createHeader(item)+"{");
		for(TSAttribute attr : item.getAttributes()){
			ps.println(indent+createAttribute(attr));
		}
		/*for(TSMethod method : item.getMethods()){
			ps.println(createMethod(method));
		}*/
		ps.println("}");
	}

	@Override
	public String createHeader(TSInterfaceDef item) {
		String result = "";
		if(item.isExported())
			result += "export ";
		result += "interface "+item.getName();
		if(item.getExtensions().size()>0){
			result += " extends ";
            result += item.getExtensions().stream().map((intf)->intf.getName()).collect(Collectors.joining(","));
		}
		return result;
	}

	@Override
	public String createAttribute(TSAttribute item) {
		String prefix = "";
		if(item.isReadonly())
			prefix += "readonly ";
		return String.format(ATTRIBUTE_FORMAT, prefix, item.getName(), item.getType().getName());
	}

}
