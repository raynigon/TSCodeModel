package com.raynigon.tscodemodel.types.hidden;

import com.raynigon.tscodemodel.types.TSClass;

public class TSRefClass implements TSClass{

    private String modulePath;
    private String name;
    
    public TSRefClass(String inModulePath, String inName){
        if(inModulePath==null || inName==null)
            throw new NullPointerException();
        modulePath = inModulePath;
        name = inName;
    }
    
    @Override
    public String getName(){
        return name;
    }
    
    @Override
    public String getModulePath(){
        return modulePath;
    }
}
