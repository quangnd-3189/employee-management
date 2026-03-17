package com.learnjiava.employee_management.common;

public class LogUtil {

    public static String formatException(Exception ex) {

        StackTraceElement element = ex.getStackTrace()[0];

        return String.format(
                "%s.%s:%d - %s",
                element.getClassName(),
                element.getMethodName(),
                element.getLineNumber(),
                ex.getMessage()
        );
    }
}
