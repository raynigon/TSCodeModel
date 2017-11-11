package com.raynigon.tscodemodel.builders.blocks;

import java.io.PrintStream;

import com.raynigon.tscodemodel.types.TSExpression;
import com.raynigon.tscodemodel.types.TSMethodBlock;

public interface TSCodeBlockBuilder{

	void buildCodeBlock(PrintStream ps, TSMethodBlock expr, int indents);

    String buildExpression(TSExpression expression);

}
