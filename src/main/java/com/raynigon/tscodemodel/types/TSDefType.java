package com.raynigon.tscodemodel.types;

import java.io.PrintStream;
import java.util.Set;

public interface TSDefType extends TSType{

  
    public void buildSelf(PrintStream ps);
  
    public Set<TSType> determineUsages();

    public String getIndent();
}
