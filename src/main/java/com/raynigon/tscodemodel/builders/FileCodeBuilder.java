package com.raynigon.tscodemodel.builders;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileCodeBuilder extends AbstractCodeBuilder{

    private Path root;
    
    public FileCodeBuilder(Path inRoot){
        root = inRoot;
    }
    
    public Path getRoot(){
        return root;
    }

    @Override
    public void createFolder(String name) throws IOException{
        Path folder = root.resolve(name);
        Files.createDirectories(folder);
    }

    @Override
    public PrintStream createFile(String pack, String module) throws IOException{
        Path packLoc = root.resolve(pack);
        Path modulePath = packLoc.resolve(module+".ts");
        Files.createFile(modulePath);
        OutputStream os = new FileOutputStream(modulePath.toFile());
        return new PrintStream(os, true, StandardCharsets.UTF_8.name());
    }
}
