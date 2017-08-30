package com.raynigon.tscodemodel;

public interface ILogger{

    public void trace(CharSequence msg);
    public void trace(CharSequence msg, Throwable t);
    public void trace(CharSequence msg, Object... p);

    public void debug(CharSequence msg);
    public void debug(CharSequence msg, Throwable t);
    public void debug(CharSequence msg, Object... p);

    public void info(CharSequence msg);
    public void info(CharSequence msg, Throwable t);
    public void info(CharSequence msg, Object... p);

    public void warn(CharSequence msg);
    public void warn(CharSequence msg, Throwable t);
    public void warn(CharSequence msg, Object... p);

    public void error(CharSequence msg);
    public void error(CharSequence msg, Throwable t);
    public void error(CharSequence msg, Object... p);
}
