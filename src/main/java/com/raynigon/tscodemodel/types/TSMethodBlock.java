package com.raynigon.tscodemodel.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.raynigon.tscodemodel.expressions.TSAssignExpression;
import com.raynigon.tscodemodel.expressions.TSCallExpression;
import com.raynigon.tscodemodel.expressions.TSReturnExpression;

public class TSMethodBlock{

    private TSMethod parent;
    private List<TSExpression> expressions;

    public TSMethodBlock(TSMethod parent){
        this.parent = parent;
        expressions = new ArrayList<>();
    }
    
    public TSExpression Call(TSMethod method, TSVar... args){
        TSExpression result = new TSCallExpression(new TSThisType((TSClassDef) parent.getParent()), method, args);
        Expression(result);
        return result;
    }

    public TSExpression Call(String methodName, TSVar... args){
        TSExpression result = new TSCallExpression(new TSThisType((TSClassDef) parent.getParent()), methodName, args);
        Expression(result);
        return result;
    }
    
    public TSExpression assign(TSVar var, TSExpression statment){
        TSExpression result = new TSAssignExpression(var, statment);
        Expression(result);
        return result;
    }
    
	public void Return(TSExpression value) {
		TSExpression result = new TSReturnExpression(value);
		Expression(result);
	}
        
    public void Expression(TSExpression statment){
        expressions.add(statment);
    }

	public Collection<TSExpression> getExpressions() {
		return expressions;
	}
}
