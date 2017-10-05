package com.raynigon.tscodemodel.builders.interfaces;

import java.io.PrintStream;
import java.util.stream.Collectors;

import com.raynigon.tscodemodel.TSCodeModel;
import com.raynigon.tscodemodel.types.TSAttribute;
import com.raynigon.tscodemodel.types.TSInterfaceDef;
import com.raynigon.tscodemodel.types.TSMethod;
import com.raynigon.tscodemodel.types.TSParam;

public class AbstractInterfaceCodeBuilder implements TSInterfaceCodeBuilder {

	private static final String ATTRIBUTE_FORMAT = "%s%s: %s;";

	protected TSCodeModel codeModel;
    
    public AbstractInterfaceCodeBuilder(TSCodeModel inCodeModel){
        codeModel = inCodeModel;
    }
	
	@Override
	public void buildInterface(PrintStream ps, TSInterfaceDef item, int indents) {
	    codeModel.getLogger().debug("Building Interface %s", item.getName());
		String indent = TSCodeModel.getIndents(indents);
		String attrIndent = TSCodeModel.getIndents(indents+1);
		ps.println(indent+createInterfaceHeader(item)+" {");
		for(TSAttribute attr : item.getAttributes()){
		    codeModel.getLogger().debug("Add Attribute", attr.getName());
			ps.println(attrIndent+createAttribute(attr));
		}
		for(TSMethod method : item.getMethods()){
			ps.println(attrIndent+createMethod(method));
		}
		ps.println(indent+"}");
	}

	@Override
	public String createInterfaceHeader(TSInterfaceDef item) {
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
	
	@Override
	public String createMethod(TSMethod item) {
		StringBuilder params = new StringBuilder();
		for(TSParam param : item.getParams()){
				params.append(", ").append(param.getName()).append(": ").append(param.getType().getName());
		}
		return String.format("%s(%s): %s;", item.getName(), params.substring(2), item.getReturnType().getName());
	}

}
