package com.raynigon.tscodemodel.builders;

import java.util.Arrays;

import com.raynigon.tscodemodel.types.TSModuleDef;

public class PathConversionHelper {

	public static String convertToRelativePath(String modulePath, TSModuleDef item) {	    
		String localModuleName = item.getPackage().getName()+"/"+item.getName();
		localModuleName = normalizeModulePath(localModuleName);
		
		if(!modulePath.startsWith("./"))
		    modulePath = normalizeModulePath(modulePath);
		String[] other = modulePath.split("/");
		String[] self = localModuleName.split("/");
		int max = Math.min(other.length, self.length);
		int index = 0;
		for(int i=0;i<max;i++){
			if(other[i].equalsIgnoreCase(self[i]))
				continue;
			index = i;
			break;
		}
		
		String[] nother = new String[other.length-index];
		String[] nself = new String[self.length-index];
		System.arraycopy(other, index, nother, 0, nother.length);
		System.arraycopy(self, index, nself, 0, nself.length-1);

		String result = "";
		if(nself.length>1){
			String[] parts = new String[nself.length-1];
			Arrays.fill(parts, "../");
			result += String.join("", parts);
		}else{
			result = "./";
		}
		
		for(String part : nother){
			result += part+"/";
		}
		
		return result.substring(0, result.length()-1);
	}

	public static String normalizeModulePath(String modulePath){
		if(modulePath.startsWith("./"))
			return modulePath;
        if(modulePath.startsWith("/"))
        	return "."+modulePath;
        return "./"+modulePath;
    }
}
