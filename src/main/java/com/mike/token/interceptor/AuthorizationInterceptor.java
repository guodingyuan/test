package com.mike.token.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mike.token.annotation.IgnoreSecurity;
import com.mike.token.exception.TokenException;
import com.mike.token.manager.TokenManager;
import com.mike.util.WebUtil;

/**
 * 
 * 类名称：AuthorizationInterceptor<br>
 * 类描述：自定义拦截器，对请求进行身份验证<br>
 * 创建人：qinjiaxue<br>
 * 创建时间：2016年6月14日 下午2:19:34<br>
 * @version v1.0
 *
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    /**
     * 存放登录用户模型Key的Request Key
     */
    public static final String REQUEST_CURRENT_KEY = "REQUEST_CURRENT_KEY";

    /**
     * 管理身份验证操作的对象
     */
    private TokenManager tokenManager;

    /**
     * 存放用户授权信息的Request名称，默认是accessToken
     */
    private String httpAccessTokenName = "accessToken";

    public void setTokenManager(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        String uri = request.getRequestURI().replaceAll(request.getContextPath(), "");
        
        //忽略
        if (method.isAnnotationPresent(IgnoreSecurity.class) || StringUtils.startsWith(uri, "/v2/api-docs")) {
            return true;
        }

        //获取访问令牌token，不为空时，把token对应的userId放入request中
        String accessToken = request.getParameter(httpAccessTokenName);
        accessToken = StringUtils.isBlank(accessToken) ? request.getHeader(httpAccessTokenName) : accessToken;
        if (StringUtils.isNotBlank(accessToken)) {

            //验证token
            String key = tokenManager.getUserId(accessToken);

            if (StringUtils.isNotBlank(key) && tokenManager.checkToken(accessToken)) {

                //如果token验证成功，将token对应的用户id存在request中，便于之后注入
                request.setAttribute(REQUEST_CURRENT_KEY, key);

                return true;
            } else {
                if (!WebUtil.isAjax(request) && !uri.startsWith("/api/")) {
                    response.sendRedirect(request.getContextPath() + "/error/tokenFailure");
                } else {
                    String message = String.format("token [%s] is invalid", accessToken);
                    logger.error(message);
                    throw new TokenException(message);
                }
            }
        } else {
            if (!WebUtil.isAjax(request) && !WebUtil.isShuiDao(request)) {
                response.sendRedirect(request.getContextPath() + "/error/tokenFailure");
            } else {
                String message = String.format("token [%s] is invalid", accessToken);
                logger.error(message);
                throw new TokenException(message);
            }
        }

        //为了防止以恶意操作直接在REQUEST_CURRENT_KEY写入key，将其设为null
        request.setAttribute(REQUEST_CURRENT_KEY, null);

        return true;
    }
}
