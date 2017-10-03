package com.raynigon.tscodemodel.builders.classes;

import java.io.PrintStream;
import java.util.stream.Collectors;

import com.raynigon.tscodemodel.TSCodeModel;
import com.raynigon.tscodemodel.types.TSAttribute;
import com.raynigon.tscodemodel.types.TSClassDef;
import com.raynigon.tscodemodel.types.TSInterface;
import com.raynigon.tscodemodel.types.TSInterfaceDef;
import com.raynigon.tscodemodel.types.TSMethod;

public abstract class AbstractTSClassCodeBuilder implements TSClassCodeBuilder {

	private static final String ATTRIBUTE_DEF_FORMAT = "%s %s%s: %s;";
	//private static final String ATTRIBUTE_INIT_FORMAT = "%s %s%s: %s = %s;";

	protected TSCodeModel codeModel;
	
	public AbstractTSClassCodeBuilder(TSCodeModel inCodeModel){
        codeModel = inCodeModel;
    }
	
	@Override
	public void buildClass(PrintStream ps, TSClassDef item) {
	    codeModel.getLogger().debug("Building Class %s", item.getName());
	    checkInterfaces(item);	    
		String indent = TSCodeModel.getIndent();
		ps.println(createHeader(item)+" {");
		for(TSAttribute attr : item.getAttributes()){
		    codeModel.getLogger().debug("Add Attribute", attr.getName());
			ps.println(indent+createAttribute(attr));
		}
		/*for(TSMethod method : item.getMethods()){
			ps.println(createMethod(method));
		}*/
		ps.println("}");
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
	public String createHeader(TSClassDef item) {
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
	public String createMethodHeader(TSMethod item){
	    // TODO Auto-generated method stub
	    return null;
	}
}
