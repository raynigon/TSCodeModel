package com.raynigon.tscodemodel;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;

import com.raynigon.tscodemodel.builders.StringCodeBuilder;

public class AbstractCodeGenerationTest {

    protected TSCodeModel tscm;
    protected StringCodeBuilder scb;
    
    @Before
    public void setUp() throws Exception{
        tscm = new TSCodeModel();
        scb = new StringCodeBuilder(tscm);
    }

    @After
    public void tearDown() throws Exception{
        tscm = null;
        scb = null;
    }
    
    protected List<String> readResource(String string) throws IOException{
        InputStream is = AbstractCodeGenerationTest.class.getResourceAsStream(string);
        String text = IOUtils.toString(is, StandardCharsets.UTF_8);
        return Arrays.asList(text.split("\n"));
    }
    
    protected void checkResult(String moduleName) throws IOException {
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
