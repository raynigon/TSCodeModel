package com.raynigon.tscodemodel.codecreation;

import java.io.IOException;

import org.junit.Test;

import com.raynigon.tscodemodel.AbstractCodeGenerationTest;
import com.raynigon.tscodemodel.types.TSClassDef;
import com.raynigon.tscodemodel.types.TSInterface;
import com.raynigon.tscodemodel.types.TSPackage;
import com.raynigon.tscodemodel.types.TSSimpleType;

public class ImportTests extends AbstractCodeGenerationTest{

    
    @Test
    public void testMultipleUsages() throws IOException{
        TSPackage pack = tscm.Package("Root");
        TSClassDef userGroup = pack.Class("UserGroup");
        TSInterface iuser = tscm.ReferenceInterface(pack, "User", "IUser");
        userGroup.Attribute("administrator", iuser);
        userGroup.Attribute("users", TSSimpleType.createArray(iuser));
        checkResult("UserGroup");
    }
}
