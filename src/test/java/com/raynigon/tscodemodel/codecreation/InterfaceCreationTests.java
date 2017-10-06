package com.raynigon.tscodemodel.codecreation;

import java.io.IOException;

import org.junit.Test;

import com.raynigon.tscodemodel.AbstractCodeGenerationTest;
import com.raynigon.tscodemodel.types.TSClassDef;
import com.raynigon.tscodemodel.types.TSInterfaceDef;
import com.raynigon.tscodemodel.types.TSMethod;
import com.raynigon.tscodemodel.types.TSModuleDef;
import com.raynigon.tscodemodel.types.TSPackage;
import com.raynigon.tscodemodel.types.TSSimpleType;
import com.raynigon.tscodemodel.types.TSVisbility;

public class InterfaceCreationTests extends AbstractCodeGenerationTest {

	@Test
    public void testSimpleInterface() throws IOException{
        TSPackage pack = tscm.Package("Root");
        TSInterfaceDef modelClazz = pack.Interface("IModel");
        modelClazz.Attribute("autoId", TSSimpleType.NUMBER);
        
        checkResult("IModel");
    }
	
    @Test
    public void testInterfacesInheritance() throws IOException{
        TSPackage pack = tscm.Package("Root");
        TSModuleDef module = pack.Module("User");
        TSInterfaceDef modelIntf = module.Interface("IUser");
        modelIntf.setExport(true);
        modelIntf.Extend(tscm.ReferenceInterface(pack, "IModel", "IModel"));
        modelIntf.Attribute("name", TSSimpleType.STRING);
        TSClassDef modelClazz = module.Class("User");
        modelClazz.setExport(true);
        modelClazz.Implement(modelIntf);
        modelClazz.Attribute("autoId", TSSimpleType.NUMBER).setVisbility(TSVisbility.PUBLIC);
        modelClazz.Attribute("name", TSSimpleType.STRING).setVisbility(TSVisbility.PUBLIC);
        
        checkResult("User");
    }
    
    @Test
    public void testInterfaceMethodDeclaration() throws IOException{
        TSPackage pack = tscm.Package("Root");
        TSInterfaceDef mathlib = pack.Interface("IMathLib");
        TSMethod addMethod = mathlib.Method("add", TSSimpleType.NUMBER);
        addMethod.Parameter("x", TSSimpleType.NUMBER);
        addMethod.Parameter("y", TSSimpleType.NUMBER);
        TSMethod subMethod = mathlib.Method("substract", TSSimpleType.NUMBER);
        subMethod.Parameter("x", TSSimpleType.NUMBER);
        subMethod.Parameter("y", TSSimpleType.NUMBER);
        TSMethod multiplyMethod = mathlib.Method("multiply", TSSimpleType.NUMBER);
        multiplyMethod.Parameter("x", TSSimpleType.NUMBER);
        multiplyMethod.Parameter("y", TSSimpleType.NUMBER);
        TSMethod divideMethod = mathlib.Method("divide", TSSimpleType.NUMBER);
        divideMethod.Parameter("x", TSSimpleType.NUMBER);
        divideMethod.Parameter("y", TSSimpleType.NUMBER);
        checkResult("IMathLib");
    }
}
