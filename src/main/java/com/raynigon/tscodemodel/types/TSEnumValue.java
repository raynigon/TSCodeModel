package com.raynigon.tscodemodel.types;

public class TSEnumValue{

    private TSEnumDef parent;
    private String name;
    private TSExpression expression;

    
    public TSEnumValue(TSEnumDef tsEnumDef, String name, TSExpression expr){
        this.parent = tsEnumDef;
        this.name = name;
        this.expression = expr;
    }

    public TSEnumDef getParent(){
        return parent;
    }
    
    public String getName(){
        return name;
    }
    
    public TSExpression getExpression(){
        return expression;
    }
}
