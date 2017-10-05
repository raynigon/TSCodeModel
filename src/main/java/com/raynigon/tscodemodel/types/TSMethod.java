package com.raynigon.tscodemodel.types;

import java.util.Collection;

public interface TSMethod{

    String getName();
    
    TSType getReturnType();
    
    Collection<TSParam> getParams();
    
    void setVisbility(TSVisbility value);
    
    TSVisbility getVisbility();
    
    TSParam Parameter(String name, TSType type);
    
    TSDefClassType getParent();

    TSMethodBlock body();
}
