package com.raynigon.tscodemodel.types;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TSEnumDef extends TSDefClassType implements TSEnum{

    private String name;
    private Map<String, TSEnumValue> values;
    
    public TSEnumDef(TSModuleDef inModule, String name){
        super(inModule);
        this.name = name;
        this.values = new HashMap<>();
    }

    @Override
    public String getName(){
        return name;
    }

    public TSEnumValue Value(String name){
        return Value(name, null);
    }
    
    public TSEnumValue Value(String name, TSExpression expr){
        if(values.containsKey(name))
            throw new IllegalArgumentException("EnumValue already exists");
        TSEnumValue value = new TSEnumValue(this, name, expr);
        values.put(name, value);
        return value;
    }
    
    @Override
    public TSAttribute Attribute(String name, TSType type){
        throw new IllegalArgumentException("Not Supported for Enums");
    }
    
    @Override
    public TSMethod Method(String name, TSType returnType){
        throw new IllegalArgumentException("Not Supported for Enums");
    }

    public Collection<TSEnumValue> getValues(){
        return values.values();
    }

}
