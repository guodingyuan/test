package com.mike.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 
 * 类名称：ExceptionUtil<br>
 * 类描述： 关于异常的工具类<br>
 * 创建人：qinjiaxue<br>
 * 创建时间：2015年12月17日 上午9:35:16<br>  
 * @version v1.0
 *
 */
public class ExceptionUtil {

    /**
     * 将CheckedException转换为UncheckedException
     * @param e
     * @return
     */
    public static RuntimeException unchecked(Exception e) {
        if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        } else {
            return new RuntimeException(e);
        }
    }

    /**
     * 将ErrorStack转化为String
     * @param e
     * @return
     */
    public static String getStackTraceAsString(Exception e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return "\r\n" + stringWriter.toString() + "\r\n";
    }

    /**
     * 判断异常是否由某些底层的异常引起
     * @param ex
     * @param causeExceptionClasses
     * @return
     */
    @SuppressWarnings("unchecked")
	public static boolean isCausedBy(Exception ex, Class<? extends Exception>... causeExceptionClasses) {
        Throwable cause = ex.getCause();
        while (cause != null) {
            for (Class<? extends Exception> causeClass : causeExceptionClasses) {
                if (causeClass.isInstance(cause)) { return true; }
            }
            cause = cause.getCause();
        }
        return false;
    }
}
