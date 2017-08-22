package com.raynigon.tscodemodel.builders;

import com.raynigon.tscodemodel.types.TSDefClassType;
import com.raynigon.tscodemodel.types.TSInterfaceDef;
import com.raynigon.tscodemodel.types.TSSimpleType;
import com.raynigon.tscodemodel.types.TSType;

public class FilterHelper {

	public static boolean isNotSimpleType(TSType usage){
		return !(usage instanceof TSSimpleType);
	}

	public static int compareClassTypes(TSDefClassType a, TSDefClassType b) {
		if(a instanceof TSInterfaceDef && !(b instanceof TSInterfaceDef))
    		return -10;
    	else if(!(a instanceof TSInterfaceDef) && b instanceof TSInterfaceDef)
    		return 10;
    	return a.getName().compareTo(b.getName());
	}
	
	public static int compareTypes(TSType a, TSType b){
		return a.getName().compareTo(b.getName());
	}
}
