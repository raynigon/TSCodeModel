package com.raynigon.tscodemodel.types;

import java.io.PrintStream;

import com.raynigon.tscodemodel.TSCodeModel;

public class TSInterfaceAttribute extends TSAttribute{

    public TSInterfaceAttribute(TSDefType inParent, String inName, TSType inType){
        super(inParent, inName, inType);
    }

    @Override
    public void buildSelf(PrintStream ps){
        String indent = TSCodeModel.getIndent() + getParent().getIndent();
        ps.println(indent+getName()+": "+getType().getName()+";");
    }
}