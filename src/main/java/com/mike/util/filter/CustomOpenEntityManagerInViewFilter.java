package com.mike.util.filter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

/**
 * 
 * 类名称：CustomOpenEntityManagerInViewFilter<br>
 * 类描述：扩展OpenEntityManagerInViewFilter，可针对指定URL不开启EntityManager<br>
 * 创建人：qinjiaxue<br>
 * 创建时间：2015年10月26日 上午10:23:50<br>
 * @version v1.0
 *
 */
public class CustomOpenEntityManagerInViewFilter extends OpenEntityManagerInViewFilter {

    /**
     * 不开启EntityManager的URL前缀，如/cometd
     */
    private String notFilteredPrefix;

    /**
     * 不开启EntityManager的URL后缀，如.css,.js,.jpg等
     */
    private String notFilteredExts;

    public String getNotFilteredPrefix() {
        return notFilteredPrefix;
    }

    public void setNotFilteredPrefix(String notFilteredPrefix) {
        this.notFilteredPrefix = notFilteredPrefix;
    }

    public String getNotFilteredExts() {
        return notFilteredExts;
    }

    public void setNotFilteredExts(String notFilteredExts) {
        this.notFilteredExts = notFilteredExts;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String contextPath = request.getContextPath();
        String requestUri = request.getRequestURI();
        if (isNotFilteredPrefix(contextPath, requestUri) || isNotFilteredExt(requestUri)) {
            return true;
        } else {
            return super.shouldNotFilter(request);
        }
    }

    /**
     * 是否属于不需要开启session的前缀
     * @param contextPath
     * @param requestUri
     * @return
     */
    private boolean isNotFilteredPrefix(String contextPath, String requestUri) {
        boolean notFilter = false;
        String prefix = requestUri.substring(contextPath.length());
        if (notFilteredPrefix != null) {
            String[] notFilteredPrefixArr = notFilteredPrefix.split(",");
            for (String notFilteredPre : notFilteredPrefixArr) {
                if (prefix.startsWith(notFilteredPre)) {
                    notFilter = true;
                    break;
                }
            }
        }
        return notFilter;
    }

    /**
     * 是否属于不需要开启session的后缀
     * @param requestUri
     * @return
     */
    private boolean isNotFilteredExt(String requestUri) {
        boolean notFilter = false;
        int indexOfPeriod = requestUri.lastIndexOf(".");
        if (indexOfPeriod > -1) {
            String ext = requestUri.substring(indexOfPeriod);
            // 有后缀，可能是REST请求，需进一步处理
            if (notFilteredExts != null && notFilteredExts.indexOf(ext) > -1) {
                notFilter = true;
            }
        }
        return notFilter;
    }
}
