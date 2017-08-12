package com.raynigon.tscodemodel.builders;

import java.io.IOException;
import java.io.PrintStream;

import com.raynigon.tscodemodel.types.TSModuleDef;

public interface TSCodeBuilder{

    void createFolder(String name) throws IOException;

    PrintStream createFile(String pack, String module) throws IOException;

    void buildModule(PrintStream os, TSModuleDef item);
}
