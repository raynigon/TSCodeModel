package com.raynigon.tscodemodel.builders.blocks;

import java.io.PrintStream;

import com.raynigon.tscodemodel.TSCodeModel;
import com.raynigon.tscodemodel.expressions.TSDirectExpression;
import com.raynigon.tscodemodel.expressions.TSMathExpression;
import com.raynigon.tscodemodel.expressions.TSReturnExpression;
import com.raynigon.tscodemodel.types.TSExpression;
import com.raynigon.tscodemodel.types.TSMethodBlock;
import com.raynigon.tscodemodel.types.TSVar;

public abstract class AbstractCodeBlockBuilder implements TSCodeBlockBuilder{

	private TSCodeModel codemodel;
	
	public AbstractCodeBlockBuilder(TSCodeModel codemodel) {
		this.codemodel = codemodel;
	}

	@Override
	public void buildCodeBlock(PrintStream ps, TSMethodBlock block, int indents){
		String ident = TSCodeModel.getIndents(indents);
		for(TSExpression expr : block.getExpressions()){
			String result = evaluateExpression(expr);
			ps.println(ident+result+";");
			if(result.startsWith("return"))
				break;
		}
	}

	private String evaluateExpression(TSExpression expr) {
		if(expr instanceof TSReturnExpression){
			return createReturnExpression((TSReturnExpression) expr) + 
					evaluateExpression(((TSReturnExpression) expr).getReturnValue());
		}else if(expr instanceof TSMathExpression){
			String first = evaluateExpression(((TSMathExpression) expr).getFirst());
			String second = evaluateExpression(((TSMathExpression) expr).getSecond());
			String operation = ((TSMathExpression) expr).getOperation();
			return String.format("(%s %s %s)", first, operation, second);
		}else if(expr instanceof TSDirectExpression){
			return ((TSDirectExpression) expr).getValue();
		}else if(expr instanceof TSVar){
			return ((TSVar) expr).getName();
		}
		codemodel.getLogger().error("Unknown Expression: "+expr.getClass().getSimpleName());
		return null;
	}

	private String createReturnExpression(TSReturnExpression expr) {
		return "return ";
	}
}
