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
import com.raynigon.tscodemodel.types.TSPackage;
import com.raynigon.tscodemodel.types.TSSimpleType;

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
    public void test() throws IOException{
        TSCodeModel tscm = new TSCodeModel();
        TSPackage pack = tscm.Package("Root");
        TSClassDef modelClazz = pack.Class("Model");
        modelClazz.Attribute("autoId", TSSimpleType.NUMBER);
        modelClazz.Attribute("name", TSSimpleType.STRING);
        tscm.build(scb);
        List<String> result = Arrays.asList(scb.getModuleText("Root", "Model").split("\n"));
        List<String> expected = readResource("/Model.ts");
        assertEquals(expected.size(), result.size());
        for(int i=0;i<expected.size();i++){
            assertEquals(expected.get(i), result.get(i));
        }
    }

    private List<String> readResource(String string) throws IOException{
        InputStream is = TSCodeModelTest.class.getResourceAsStream(string);
        String text = IOUtils.toString(is, StandardCharsets.UTF_8);
        return Arrays.asList(text.split("\n"));
    }

}
