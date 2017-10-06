package com.raynigon.tscodemodel;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.raynigon.tscodemodel.codecreation.ClassCreationTests;
import com.raynigon.tscodemodel.codecreation.ImportTests;
import com.raynigon.tscodemodel.codecreation.InterfaceCreationTests;

@RunWith(Suite.class)
@SuiteClasses({ ClassCreationTests.class, ImportTests.class, InterfaceCreationTests.class })
public class CodeCreationTests {

}
