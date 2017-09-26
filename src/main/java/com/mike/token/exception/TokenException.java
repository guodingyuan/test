package com.mike.token.exception;

/**
 * 
 * 类名称：TokenException<br>
 * 类描述：令牌异常<br>
 * 创建人：qinjiaxue<br>
 * 创建时间：2015年10月27日 上午11:05:01<br>
 * @version v1.0
 *
 */
public class TokenException extends RuntimeException {

    private static final long serialVersionUID = 8391869486329200571L;

    /**
     * 
     * 创建一个新的实例 TokenException.     
     * @param message
     */
    public TokenException(String message) {
        super(message);
    }
}
