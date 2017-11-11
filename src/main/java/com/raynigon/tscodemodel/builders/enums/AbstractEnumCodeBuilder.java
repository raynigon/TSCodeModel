package com.raynigon.tscodemodel.builders.enums;

import java.io.PrintStream;
import java.util.ArrayList;

import com.raynigon.tscodemodel.TSCodeModel;
import com.raynigon.tscodemodel.builders.blocks.AbstractCodeBlockBuilder;
import com.raynigon.tscodemodel.builders.blocks.TSCodeBlockBuilder;
import com.raynigon.tscodemodel.types.TSEnumDef;
import com.raynigon.tscodemodel.types.TSEnumValue;

public abstract class AbstractEnumCodeBuilder implements TSEnumCodeBuilder{

    protected TSCodeModel        codeModel;
    protected TSCodeBlockBuilder expressionBuilder;

    public AbstractEnumCodeBuilder(TSCodeModel inCodeModel){
        codeModel = inCodeModel;
        expressionBuilder = new AbstractCodeBlockBuilder(inCodeModel){
        };
    }

    @Override
    public void buildEnum(PrintStream ps, TSEnumDef item, int indents){
        codeModel.getLogger().debug("Building Class %s", item.getName());
        String indent = TSCodeModel.getIndents(indents);
        ps.println(indent + createEnumHeader(item) + " {");
        String valueIndent = TSCodeModel.getIndents(indents + 1);
        ArrayList<TSEnumValue> enumValues = new ArrayList<>(item.getValues());
        for(TSEnumValue enumValue : enumValues){
            String seperator = enumValue == enumValues.get(enumValues.size() - 1) ? "" : ",";
            if(enumValue.getExpression() == null){
                ps.println(valueIndent + enumValue.getName() + seperator);
            }else{
                ps.println(valueIndent 
                        + enumValue.getName() 
                        + " = "
                        + expressionBuilder.buildExpression(enumValue.getExpression()) 
                        + seperator);
            }
        }
        ps.println(indent + "}");
    }

    @Override
    public String createEnumHeader(TSEnumDef item){
        String result = "";
        if(item.isExported())
            result = "export ";
        result += "enum " + item.getName();
        return result;
    }
}
