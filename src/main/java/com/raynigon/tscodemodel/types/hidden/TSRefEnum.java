package com.raynigon.tscodemodel.types.hidden;

import com.raynigon.tscodemodel.types.TSEnum;

public class TSRefEnum implements TSEnum{

    private String modulePath;
    private String name;
    
    public TSRefEnum(String inModulePath, String inName){
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
