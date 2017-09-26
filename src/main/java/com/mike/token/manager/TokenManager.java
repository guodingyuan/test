package com.mike.token.manager;

/**
 * 
 * 类名称：TokenManager<br>
 * 类描述：令牌管理器<br>
 * 创建人：qinjiaxue<br>
 * 创建时间：2015年10月27日 上午11:05:22<br>
 * @version v1.0
 *
 */
public interface TokenManager {

    /**
     * 创建令牌
     * @param username
     * @return
     */
    public String createToken(String username);

    /**
     * 创建令牌
     * @param username
     * @param appId
     * @param source
     * @return
     */
    public String createToken(String username, String appId, int source);

    /**
     * 移除令牌
     * @param token
     */
    public void removeToken(String token);

    /**
     * 检查令牌
     * @param token
     * @return
     */
    public boolean checkToken(String token);

    /**
     * 检查令牌
     * @param token
     * @param userId
     * @return
     */
    public boolean checkToken(String token, String userId);

    /**
     * 通过token获取用户id
     * @param token
     * @return
     */
    public String getUserId(String token);

    /**
     * 通过应用id、用户id获取对应的token
     * @param appId 应用id
     * @param userId 用户id
     * @return
     */
    public String getToken(String appId, String userId);

    /**
     * 获取token对应的设备
     * @param token
     * @return
     */
    public int getTokenDevice(String token);
}
