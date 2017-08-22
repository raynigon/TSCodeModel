package com.raynigon.tscodemodel;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.raynigon.tscodemodel.builders.StringCodeBuilder;
import com.raynigon.tscodemodel.types.TSClassDef;
import com.raynigon.tscodemodel.types.TSInterfaceDef;
import com.raynigon.tscodemodel.types.TSModuleDef;
import com.raynigon.tscodemodel.types.TSPackage;
import com.raynigon.tscodemodel.types.TSSimpleType;
import com.raynigon.tscodemodel.types.TSVisbility;

public class TSCodeModelTest{

    private StringCodeBuilder scb;
    
    @Before
    public void setUp() throws Exception{
        scb = new StringCodeBuilder();
    }

    @After
    public void tearDown() throws Exception{
        scb = null;
    }

    @Test
    public void testSimpleInterface() throws IOException{
        TSCodeModel tscm = new TSCodeModel();
        TSPackage pack = tscm.Package("Root");
        TSInterfaceDef modelClazz = pack.Interface("IModel");
        modelClazz.Attribute("autoId", TSSimpleType.NUMBER);
        
        checkResult(tscm, "IModel");
    }

    @Test
    public void testInterfacesInheritance() throws IOException{
        TSCodeModel tscm = new TSCodeModel();
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
        
        checkResult(tscm, "User");
    }
    
    private List<String> readResource(String string) throws IOException{
        InputStream is = TSCodeModelTest.class.getResourceAsStream(string);
        String text = IOUtils.toString(is, StandardCharsets.UTF_8);
        return Arrays.asList(text.split("\n"));
    }
    
	private void checkResult(TSCodeModel tscm, String moduleName) throws IOException {
		tscm.build(scb);
		String moduleContent = scb.getModuleText("Root", moduleName);
		System.out.println("TS-Output:\n"+moduleContent+"\n");
		List<String> result = Arrays.asList(moduleContent.split("\n"));
        List<String> expected = readResource("/"+moduleName+".ts");
        assertEquals(expected.size(), result.size());
        for(int i=0;i<expected.size();i++){
            assertEquals(expected.get(i).trim(), result.get(i).trim());
        }
	}


}
