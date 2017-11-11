package com.raynigon.tscodemodel.types.hidden;

import com.raynigon.tscodemodel.types.TSInterface;

public class TSRefInterface implements TSInterface{

    private String modulePath;
    private String name;
    
    public TSRefInterface(String inModulePath, String inName){
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
