package com.walkerChen.estore.commonUtils;

import java.io.PrintStream;
import java.io.PrintWriter;
@SuppressWarnings("all")
public class SecurityException extends RuntimeException {
	@Override
	public void setStackTrace(StackTraceElement[] stackTraceElements) {
		super.setStackTrace(stackTraceElements);
	}

	public SecurityException() {
		super();
	}

	public SecurityException(String s) {
		super(s);
	}

	public SecurityException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public SecurityException(Throwable throwable) {
		super(throwable);
	}

	protected SecurityException(String s, Throwable throwable, boolean b, boolean b1) {
		super(s, throwable, b, b1);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

	@Override
	public String getLocalizedMessage() {
		return super.getLocalizedMessage();
	}

	@Override
	public synchronized Throwable getCause() {
		return super.getCause();
	}

	@Override
	public synchronized Throwable initCause(Throwable throwable) {
		return super.initCause(throwable);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public void printStackTrace() {
		super.printStackTrace();
	}

	@Override
	public void printStackTrace(PrintStream printStream) {
		super.printStackTrace(printStream);
	}

	@Override
	public void printStackTrace(PrintWriter printWriter) {
		super.printStackTrace(printWriter);
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return super.fillInStackTrace();
	}

	@Override
	public StackTraceElement[] getStackTrace() {
		return super.getStackTrace();
	}
}
