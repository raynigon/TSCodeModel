package com.raynigon.tscodemodel.builders;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.raynigon.tscodemodel.types.TSModuleDef;
import com.raynigon.tscodemodel.types.TSPackage;

public class PathConversionHelperTest {
	
	@Test
	public void normalizePathTest() {
		assertEquals("./ABC/A", PathConversionHelper.normalizeModulePath("/ABC/A"));
		assertEquals("./ABC/A", PathConversionHelper.normalizeModulePath("./ABC/A"));
		assertEquals("./ABC/A", PathConversionHelper.normalizeModulePath("ABC/A"));
		//assertEquals("./ABC/A", codeBuilder.normalizeModulePath("/ABC/A/"));
	}

	@Test
	public void relativePathConversionTest(){
		TSModuleDef md = new TSModuleDef(new TSPackage(null, "PACK0/PACK1/PACK2"), "MODULE.A");
		assertEquals("./MODULE.B", PathConversionHelper.convertToRelativePath("./PACK0/PACK1/PACK2/MODULE.B", md));
		assertEquals("./MODULE.B", PathConversionHelper.convertToRelativePath("PACK0/PACK1/PACK2/MODULE.B", md));
		assertEquals("../PACK3/MODULE.B", PathConversionHelper.convertToRelativePath("./PACK0/PACK1/PACK3/MODULE.B", md));
	}
	
}
