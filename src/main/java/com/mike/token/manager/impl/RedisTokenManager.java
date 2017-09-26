package com.mike.token.manager.impl;

import org.apache.commons.lang3.StringUtils;
import com.mike.token.manager.TokenManager;
import com.mike.util.CastUtil;
import com.mike.util.CodecUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 
 * 类名称：RedisTokenManager<br>
 * 类描述：基于 Redis 的令牌管理器<br>
 * 创建人：qinjiaxue<br>
 * 创建时间：2015年10月28日 下午12:38:19<br>
 * @version v1.0
 *
 */
public class RedisTokenManager implements TokenManager {

    /**
     * Redis中Key的前缀
     */
    private static final String REDIS_KEY_PREFIX = "AUTHORIZATION_KEY_";

    /**
     * Redis中Token的前缀
     */
    private static final String REDIS_TOKEN_PREFIX = "AUTHORIZATION_TOKEN_";
    
    
    /**
     * Redis中Token对应的设备的前缀
     */
    private static final String REDIS_TOKEN_DEVICE_PREFIX = "AUTHORIZATION_TOKEN_DEVICE_";

    /**
     * token失效时间，默认7天
     */
    private static final long DEFAULT_SECONDS = 7 * 24 * 3600;

    private JedisPool jedisPool;
    private long seconds = DEFAULT_SECONDS;

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public void setSeconds(long seconds) {
        this.seconds = seconds;
    }

    /*
     * (non-Javadoc)
     * @see com.yhsoft.framework.core.security.manager.TokenManager#createToken(java.lang.String)
     */
    public String createToken(String username) {
        String token = CodecUtil.createUUID();

        String oldToken = get(formatKey(username));
        if (oldToken != null) {
            delete(formatToken(oldToken));
        }
        set(formatToken(token), username, seconds);
        set(formatKey(username), token, seconds);

        return token;
    }

    /*
     * (non-Javadoc)
     * @see com.yhsoft.framework.core.security.manager.TokenManager#createToken(java.lang.String, java.lang.String, int)
     */
    public String createToken(String userId, String appId, int source) {
        String token = CodecUtil.createUUID();

        String oldToken = get(formatKey(userId + "_" + appId));
        if (oldToken != null) {
            delete(formatToken(oldToken));
        }
        set(formatToken(token), userId, seconds);
        set(formatKey(userId + "_" + appId), token, seconds);

        return token;
    }

    /*
     * (non-Javadoc)
     * @see com.yhsoft.framework.core.security.manager.TokenManager#removeToken(java.lang.String)
     */
    public void removeToken(String token) {
        delete(formatToken(token));
    }

    /*
     * (non-Javadoc)
     * @see com.yhsoft.framework.core.security.manager.TokenManager#checkToken(java.lang.String)
     */
    public boolean checkToken(String token) {
        if (StringUtils.isBlank(token)) {
            return false;
        }
        boolean result = exists(formatToken(token));
        if (result) {
            expire(formatToken(token), seconds);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * @see com.yhsoft.framework.core.security.manager.TokenManager#checkToken(java.lang.String, java.lang.String)
     */
    public boolean checkToken(String token, String userId) {
        if (StringUtils.isBlank(token)) {
            return false;
        }

        boolean result = exists(formatToken(token));

        if (!userId.equals(get(formatToken(token)))) {
            result = false;
        } else {
            expire(formatToken(token), seconds);
        }

        return result;
    }

    /*
     * (non-Javadoc)
     * @see com.yhsoft.framework.core.security.manager.TokenManager#getUserId(java.lang.String)
     */
    public String getUserId(String token) {
        return get(formatToken(token));
    }

    /*
     * (non-Javadoc)
     * @see com.yhsoft.framework.core.security.manager.TokenManager#getToken(java.lang.String, java.lang.String)
     */
    public String getToken(String appId, String userId) {
        return get(formatKey(userId + "_" + appId));
    }
    
    /*
     * (non-Javadoc)
     * @see com.yhsoft.framework.core.security.manager.TokenManager#getTokenDevice(java.lang.String)
     */
    public int getTokenDevice(String token) {
        return CastUtil.castInt(get(formatTokenDevice(token)), -1);
    }

    private String get(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.get(key);
        }
    }

    private String set(String key, String value, long expireSeconds) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.setex(key, CastUtil.castInt(expireSeconds), value);
        }
    }

    private boolean exists(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.exists(key);
        }
    }

    private void expire(String key, long seconds) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.expire(key, CastUtil.castInt(seconds));
        }
    }

    private void delete(String... keys) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.del(keys);
        }
    }

    private String formatKey(String key) {
        return REDIS_KEY_PREFIX.concat(key);
    }

    private String formatToken(String token) {
        return REDIS_TOKEN_PREFIX.concat(token);
    }
    
    private String formatTokenDevice(String token) {
        return REDIS_TOKEN_DEVICE_PREFIX.concat(token);
    }
}
