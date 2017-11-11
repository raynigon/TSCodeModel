package com.raynigon.tscodemodel.builders;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.raynigon.tscodemodel.TSCodeModel;
import com.raynigon.tscodemodel.builders.classes.AbstractTSClassCodeBuilder;
import com.raynigon.tscodemodel.builders.classes.TSClassCodeBuilder;
import com.raynigon.tscodemodel.builders.enums.AbstractEnumCodeBuilder;
import com.raynigon.tscodemodel.builders.enums.TSEnumCodeBuilder;
import com.raynigon.tscodemodel.builders.interfaces.AbstractInterfaceCodeBuilder;
import com.raynigon.tscodemodel.builders.interfaces.TSInterfaceCodeBuilder;
import com.raynigon.tscodemodel.builders.modules.AbstractModuleCodeBuilder;
import com.raynigon.tscodemodel.builders.modules.TSModuleCodeBuilder;
import com.raynigon.tscodemodel.types.TSModuleDef;
import com.raynigon.tscodemodel.types.TSPackage;

public class FileCodeBuilder extends AbstractCodeBuilder{

    private Path                 root;
    private ModuleCodeBuilder    moduleCodeBuilder;
    private ClassCodeBuilder     classCodeBuilder;
    private InterfaceCodeBuilder intfCodeBuilder;
    private EnumCodeBuilder      enumCodeBuilder;

    public FileCodeBuilder(Path inRoot, TSCodeModel inCodeModel){
        moduleCodeBuilder = new ModuleCodeBuilder(inCodeModel);
        classCodeBuilder = new ClassCodeBuilder(inCodeModel);
        intfCodeBuilder = new InterfaceCodeBuilder(inCodeModel);
        enumCodeBuilder = new EnumCodeBuilder(inCodeModel);
        root = inRoot;
    }

    @Override
    public TSModuleCodeBuilder getModuleCodeBuilder(){
        return moduleCodeBuilder;
    }

    @Override
    public TSClassCodeBuilder getClassCodeBuilder(){
        return classCodeBuilder;
    }

    @Override
    public TSInterfaceCodeBuilder getInterfaceCodeBuilder(){
        return intfCodeBuilder;
    }

    @Override
    public TSEnumCodeBuilder getEnumCodeBuilder(){
        return enumCodeBuilder;
    }
    
    public Path getRoot(){
        return root;
    }

    class ModuleCodeBuilder extends AbstractModuleCodeBuilder{

        private Map<TSModuleDef, OutputStream> osmap;

        public ModuleCodeBuilder(TSCodeModel inCodeModel){
            super(inCodeModel);
            osmap = new HashMap<>();
        }

        @Override
        public void createPackage(TSPackage pack) throws IOException{
            Path folder = root.resolve(PathConversionHelper.normalizeModulePath(pack.getName()));
            Files.createDirectories(folder);
        }

        @Override
        public PrintStream createModule(TSModuleDef module) throws IOException{
            codeModel.getLogger().debug("Create Module %s", module.getName());
            Path packLoc = root.resolve(module.getPackage().getName());
            Path modulePath = packLoc.resolve(module.getName() + ".ts");
            if(!Files.exists(modulePath)){
                codeModel.getLogger().debug("Create File %s", modulePath);
                Files.createFile(modulePath);
            }else{
                codeModel.getLogger().debug("File %s already exists", modulePath);
            }
            OutputStream os = new FileOutputStream(modulePath.toFile());
            osmap.put(module, os);
            return new PrintStream(os, true, StandardCharsets.UTF_8.name());
        }

        @Override
        public void flushModule(TSModuleDef module) throws IOException{
            codeModel.getLogger().debug("Finished Module %s", module.getName());
            if(!osmap.containsKey(module))
                return;
            OutputStream os = osmap.remove(module);
            os.close();
        }
    }

    public static class InterfaceCodeBuilder extends AbstractInterfaceCodeBuilder{

        public InterfaceCodeBuilder(TSCodeModel inCodeModel){
            super(inCodeModel);
        }
    }

    public static class ClassCodeBuilder extends AbstractTSClassCodeBuilder{

        public ClassCodeBuilder(TSCodeModel inCodeModel){
            super(inCodeModel);
        }
    }

    public static class EnumCodeBuilder extends AbstractEnumCodeBuilder{

        public EnumCodeBuilder(TSCodeModel inCodeModel){
            super(inCodeModel);
        }
    }
}
