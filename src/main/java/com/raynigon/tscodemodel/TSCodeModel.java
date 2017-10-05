package com.raynigon.tscodemodel;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.raynigon.tscodemodel.builders.FileCodeBuilder;
import com.raynigon.tscodemodel.builders.TSCodeBuilder;
import com.raynigon.tscodemodel.types.TSClass;
import com.raynigon.tscodemodel.types.TSInterface;
import com.raynigon.tscodemodel.types.TSPackage;
import com.raynigon.tscodemodel.types.hidden.DefaultLogger;
import com.raynigon.tscodemodel.types.hidden.TSRefClass;
import com.raynigon.tscodemodel.types.hidden.TSRefInterface;

public class TSCodeModel{

    private List<TSPackage> packs;
    private ILogger logger;
    
    public TSCodeModel(){
        this(new DefaultLogger());
    }
    
    public TSCodeModel(ILogger inLogger){
        packs = new ArrayList<>();
        logger = new DefaultLogger();
    }
    
    public TSPackage Package(String name){
        TSPackage pack = new TSPackage(this, name);
        packs.add(pack);
        return pack;
    }    
    
    public void build(Path root) throws IOException{
        build(new FileCodeBuilder(root, this));
    }
    
    public void build(TSCodeBuilder codebuilder) throws IOException{
    	codebuilder.build(packs);
    }
    
    public static String getIndent(){
        return "    ";
    }

    public TSClass ReferenceClass(TSPackage modelpack, String moduleName, String className){
        return new TSRefClass(modelpack.getName()+"/"+moduleName, className);
    }

    public TSInterface ReferenceInterface(TSPackage modelpack, String moduleName, String interfaceName){
        return new TSRefInterface(modelpack.getName()+"/"+moduleName, interfaceName);
    }
    
    public ILogger getLogger(){
        return logger;
    }

	public static String getIndents(int indents) {
		String result = "";
		for(int i=0;i<indents;i++){
			result += getIndent();
		}
		return result;
	}
}
