package com.raynigon.tscodemodel.codecreation;

import java.io.IOException;

import org.junit.Test;

import com.raynigon.tscodemodel.AbstractCodeGenerationTest;
import com.raynigon.tscodemodel.expressions.TSExpr;
import com.raynigon.tscodemodel.types.TSAttribute;
import com.raynigon.tscodemodel.types.TSClassDef;
import com.raynigon.tscodemodel.types.TSMethod;
import com.raynigon.tscodemodel.types.TSMethodBlock;
import com.raynigon.tscodemodel.types.TSPackage;
import com.raynigon.tscodemodel.types.TSParam;
import com.raynigon.tscodemodel.types.TSSimpleType;

public class ClassCreationTests extends AbstractCodeGenerationTest {

	
	@Test
    public void testConstructor() throws IOException{
        TSPackage pack = tscm.Package("Root");
        TSClassDef flight = pack.Class("Flight");
        TSAttribute airline = flight.Attribute("airline", TSSimpleType.STRING);
        TSAttribute scheduled = flight.Attribute("scheduled", TSSimpleType.STRING);
        TSAttribute inbound = flight.Attribute("inbound", TSSimpleType.BOOLEAN);
        TSMethod ctor = flight.Constructor();
        TSParam pAirline = ctor.Parameter("airline", TSSimpleType.STRING);
        TSParam pScheduled = ctor.Parameter("scheduled", TSSimpleType.STRING);
        TSParam pInbound = ctor.Parameter("inbound", TSSimpleType.BOOLEAN);
        TSMethodBlock block = ctor.body();
        block.assign(airline, pAirline);
        block.assign(scheduled, pScheduled);
        block.assign(inbound, pInbound);
        checkResult("Flight");
    }
	
	@Test
    public void testSimpleMethodImpl() throws IOException{
        TSPackage pack = tscm.Package("Root");
        TSClassDef mathlib = pack.Class("MathLib");
        TSMethod addMethod = mathlib.Method("add", TSSimpleType.NUMBER);
        TSParam paramX = addMethod.Parameter("x", TSSimpleType.NUMBER);
        TSParam paramY = addMethod.Parameter("y", TSSimpleType.NUMBER);
        addMethod.body().Return(TSExpr.Add(paramX, paramY));
        checkResult("MathLib");
    }
}
