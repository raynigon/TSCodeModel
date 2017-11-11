package com.raynigon.tscodemodel.builders.enums;

import java.io.PrintStream;

import com.raynigon.tscodemodel.types.TSEnumDef;

public interface TSEnumCodeBuilder{

    void buildEnum(PrintStream ps, TSEnumDef type, int indents);

    String createEnumHeader(TSEnumDef item);

}
