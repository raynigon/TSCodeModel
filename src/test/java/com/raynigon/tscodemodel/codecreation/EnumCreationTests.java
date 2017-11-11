package com.raynigon.tscodemodel.codecreation;

import java.io.IOException;

import org.junit.Test;

import com.raynigon.tscodemodel.AbstractCodeGenerationTest;
import com.raynigon.tscodemodel.expressions.TSExpr;
import com.raynigon.tscodemodel.types.TSEnumDef;
import com.raynigon.tscodemodel.types.TSPackage;

public class EnumCreationTests extends AbstractCodeGenerationTest {

	
	@Test
    public void testConstructor() throws IOException{
        TSPackage pack = tscm.Package("Root");
        TSEnumDef flBound = pack.Enum("FlightBound");
        flBound.Value("INBOUND", TSExpr.Lit("inbound"));
        flBound.Value("OUTBOUND", TSExpr.Lit("outbound"));
        checkResult("FlightBound");
    }
}
