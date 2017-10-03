package com.raynigon.tscodemodel.types;

public interface TSStatement{
    
    TSStatement call(TSMethod method, TSVar... args);
    
    TSStatement call(String methodName, TSVar... args);
}
