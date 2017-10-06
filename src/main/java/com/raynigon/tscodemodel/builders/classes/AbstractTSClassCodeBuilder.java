package com.raynigon.tscodemodel.builders.classes;

import java.io.PrintStream;
import java.util.stream.Collectors;

import com.raynigon.tscodemodel.TSCodeModel;
import com.raynigon.tscodemodel.builders.blocks.AbstractCodeBlockBuilder;
import com.raynigon.tscodemodel.builders.blocks.TSCodeBlockBuilder;
import com.raynigon.tscodemodel.types.TSAttribute;
import com.raynigon.tscodemodel.types.TSClassDef;
import com.raynigon.tscodemodel.types.TSInterface;
import com.raynigon.tscodemodel.types.TSInterfaceDef;
import com.raynigon.tscodemodel.types.TSMethod;
import com.raynigon.tscodemodel.types.TSMethodCtor;
import com.raynigon.tscodemodel.types.TSParam;

public abstract class AbstractTSClassCodeBuilder implements TSClassCodeBuilder {

	private static final String ATTRIBUTE_DEF_FORMAT = "%s %s%s: %s;";
	//private static final String ATTRIBUTE_INIT_FORMAT = "%s %s%s: %s = %s;";

	protected TSCodeModel codeModel;
	protected TSCodeBlockBuilder expressionBuilder;
	
	public AbstractTSClassCodeBuilder(TSCodeModel inCodeModel){
        codeModel = inCodeModel;
        expressionBuilder = new AbstractCodeBlockBuilder(inCodeModel) {};
    }
	
	@Override
	public void buildClass(PrintStream ps, TSClassDef item, int indents) {
	    codeModel.getLogger().debug("Building Class %s", item.getName());
	    checkInterfaces(item);	    
		String indent = TSCodeModel.getIndents(indents);
		ps.println(indent+createClassHeader(item)+" {");
		ps.println();
		String attrIndent = TSCodeModel.getIndents(indents+1);
		item.getAttributes().stream()
			.sorted((attr0, attr1)->attr0.getName().compareTo(attr1.getName()))
			.forEach((attr)->{
				codeModel.getLogger().debug("Add Attribute", attr.getName());
				ps.println(attrIndent+createAttribute(attr));
			});
		if(!item.getAttributes().isEmpty() && !item.getMethods().isEmpty())
			ps.println();
		//TODO Sort Methods
		for(TSMethod method : item.getMethods()){
			buildMethod(ps, method, indents+1);
			ps.println();
		}
		ps.println(indent+"}");
	}

	private void checkInterfaces(TSClassDef item){
	    if(item.isAbstract())
	        return;
        for(TSInterface intf : item.getImplementations()){
            if(!(intf instanceof TSInterfaceDef))
                continue;
            TSInterfaceDef tsintf = (TSInterfaceDef) intf;
            for(TSAttribute intfAttr : tsintf.getAttributes()){
                boolean found = item.getAttributes().stream().anyMatch((attr)->attr.getName().equals(intfAttr.getName()));
                if(found)
                    continue;
                codeModel.getLogger().error("The property "+intfAttr.getName()+" is missing in Class "+item.getName());
            }
        }
    }

    @Override
	public String createClassHeader(TSClassDef item) {
		String result = "";
		if(item.isExported())
			result = "export ";
		if(item.isAbstract())
			result = "abstract ";
		result += "class "+item.getName();
		if(item.getExtension()!=null)
			result += " extends "+item.getExtension().getName();
		if(item.getImplementations().size()>0){
			result += " implements ";
			result += item.getImplementations().stream().map((intf)->intf.getName()).collect(Collectors.joining(","));
		}
		return result;
	}
	
	@Override
	public String createAttribute(TSAttribute item) {
		String prefix = "";
		if(item.isReadonly())
			prefix += "readonly ";
		if(item.isStatic())
			prefix += "static ";
		String visbility = item.getVisibility().toString();
		/*
		 * if(false)
		 * return String.format(ATTRIBUTE_INIT_FORMAT, visbility, readonly, item.getName(), item.getType().getName(), item.getValue());
		 * else*/
		return String.format(ATTRIBUTE_DEF_FORMAT, visbility, prefix, item.getName(), item.getType().getName());
	}
	
	@Override
	public void buildMethod(PrintStream ps, TSMethod item, int indents){
		String indent = TSCodeModel.getIndents(indents);
	    ps.println(indent+createMethodHeader(item));
	    expressionBuilder.buildCodeBlock(ps, item.body(), indents+1);
	    ps.println(indent+"}");
	    return;
	}
	
	protected String createMethodHeader(TSMethod item){
		String visibilityStr = "public";
		switch(item.getVisbility()){
		case PRIVATE:
			visibilityStr = "private";
			break;
		case PROTECTED:
			visibilityStr = "protected";
			break;
		case PUBLIC:
		default:
			visibilityStr = "public";
		}
		StringBuilder params = new StringBuilder();
		for(TSParam param : item.getParams()){
			params.append(", ").append(param.getName()).append(": ").append(param.getType().getName());
		}
		String paramsStr = item.getParams().isEmpty() ? "" : params.substring(2);
		if(item instanceof TSMethodCtor){
			return String.format("%s %s(%s) {", visibilityStr, item.getName(), paramsStr);
		}
		return String.format("%s %s(%s): %s {", visibilityStr, item.getName(), paramsStr, item.getReturnType().getName());
	}
}
