package com.raynigon.tscodemodel.builders;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class StringCodeBuilder extends AbstractCodeBuilder{

    private Map<String, ByteArrayOutputStream> streams; 
    
    public StringCodeBuilder(){
        streams = new HashMap<>();
    }
    
    @Override
    public void createFolder(String name) throws IOException{}

    @Override
    public PrintStream createFile(String pack, String module) throws IOException{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        streams.put(pack+"/"+module, bos);
        return new PrintStream(bos, true, StandardCharsets.UTF_8.name());
    }

    public String getModuleText(String pack, String module){
        ByteArrayOutputStream bos = streams.get(pack+"/"+module);
        return new String(bos.toByteArray(), StandardCharsets.UTF_8);
    }

}
