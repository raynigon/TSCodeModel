package com.raynigon.tscodemodel.types.hidden;

import com.raynigon.tscodemodel.ILogger;

public class DefaultLogger implements ILogger{

    @Override
    public void trace(CharSequence msg){}

    @Override
    public void trace(CharSequence msg, Throwable t){}

    @Override
    public void trace(CharSequence msg, Object... p){}

    @Override
    public void debug(CharSequence msg){}

    @Override
    public void debug(CharSequence msg, Throwable t){}

    @Override
    public void debug(CharSequence msg, Object... p){}

    @Override
    public void info(CharSequence msg){}

    @Override
    public void info(CharSequence msg, Throwable t){}

    @Override
    public void info(CharSequence msg, Object... p){}

    @Override
    public void warn(CharSequence msg){
        System.out.println("[WARN] TSCodeModel: "+msg);
    }

    @Override
    public void warn(CharSequence msg, Throwable t){
        System.out.println("[WARN] TSCodeModel: "+msg);
        t.printStackTrace(System.out);
    }

    @Override
    public void warn(CharSequence msg, Object... p){
        System.out.printf("[WARN] TSCodeModel: "+msg+"\n", p);
    }

    @Override
    public void error(CharSequence msg){
        System.err.println("[WARN] TSCodeModel: "+msg);

    }

    @Override
    public void error(CharSequence msg, Throwable t){
        System.err.println("[WARN] TSCodeModel: "+msg);
        t.printStackTrace(System.out);
    }

    @Override
    public void error(CharSequence msg, Object... p){
        System.err.printf("[WARN] TSCodeModel: "+msg+"\n", p);
    }

}
