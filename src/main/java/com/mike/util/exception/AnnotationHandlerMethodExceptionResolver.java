package com.mike.util.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.mike.util.ExceptionUtil;
import com.mike.util.Response;
import com.mike.util.WebUtil;

/**
 * 
 * 类名称：AnnotationHandlerMethodExceptionResolver<br>
 * 类描述：spring-mvc全局异常处理<br>
 * 创建人：qinjiaxue<br>
 * 创建时间：2015年12月15日 上午8:52:51<br>
 * @version v1.0
 *
 */
public class AnnotationHandlerMethodExceptionResolver implements HandlerExceptionResolver {

    protected Logger logger = LoggerFactory.getLogger(AnnotationHandlerMethodExceptionResolver.class);

    private String defaultErrorView;

    public String getDefaultErrorView() {
        return defaultErrorView;
    }

    public void setDefaultErrorView(String defaultErrorView) {
        this.defaultErrorView = defaultErrorView;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 除了莫名其妙的ClientAbortException，一定要把异常记录下来，否则就被吞了。
        if ("ClientAbortException".equals(ex.getClass().getSimpleName())) {
            logger.error(request.getRequestURI());
            return null;
        } else {
            logger.error("", ex);
        }
        if (WebUtil.isAjax(request)) {
            return handleAjaxException(request, response, ex);
        }
        return handlePageException(request, ex);
    }

    private ModelAndView handlePageException(HttpServletRequest request, Exception ex) {
        ModelAndView mav = new ModelAndView();
        logger.debug(request.getRequestURI());
        String exMsg = ex.getMessage();
        if (isChineseStr(exMsg)) {
            mav.addObject("exMsg", exMsg);
        }
        mav.addObject("stackTrace", ExceptionUtil.getStackTraceAsString(ex));
        mav.addObject("ex", ex.getClass().getName());
        mav.addObject("uiRoot", "/taxui");
        mav.setViewName(defaultErrorView);
        return mav;
    }

    private ModelAndView handleAjaxException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        String contentType = "text/plain;";
        // 判断返回的contentType
        if (request.getHeader("accept").indexOf("application/json") >= 0) {
            contentType = "text/json;";
        }
        response.setContentType(contentType + "charset=UTF-8");
        try {
            PrintWriter writer = response.getWriter();
            String msg = ex.getMessage();
            if (!isChineseStr(msg)) {
                msg = "请求失败";
            }
            writer.write(JSON.toJSONString(new Response().failure(ex.getMessage())));
            writer.flush();
            writer.close();
        } catch (IOException e) {

        }
        return null;
    }

    private boolean isChineseChar(char c) {
        if ((c >= 0x4e00) && (c <= 0x9fbb)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断第一个字符是否为中文字符
     * @param str
     * @return
     */
    private boolean isChineseStr(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        return isChineseChar(str.charAt(0));
    }
}
