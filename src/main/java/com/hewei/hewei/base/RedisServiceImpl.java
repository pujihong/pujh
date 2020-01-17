package com.hewei.hewei.base;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DigestUtils;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/*
 通过Redis存储和验证token的实现类
*/

@Component
public class RedisServiceImpl implements RedisService {

    private RedisTemplate<String, String> redis;

    @Autowired
    public void setRedis(RedisTemplate<String, String> redis) {
        this.redis = redis;
        redis.setKeySerializer(new StringRedisSerializer());
    }

    public String createToken(Long userId) {
        //使用uuid作为源token
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String token = DigestUtils.sha1DigestAsHex(uuid + userId + Constant.SALT);
        //存储到redis并设置过期时间
        redis.boundValueOps(token).set(String.valueOf(userId), Constant.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        // 用于不让用户多处登录
        redis.boundValueOps(Constant.TOKEN_PREFIX + userId).set(token, Constant.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return token;
    }

    @Override
    public Long getUserId(String token) {
        if (token == null) {
            return -1L;
        }
        String userId = redis.boundValueOps(token).get();
        if (userId == null) {
            return -1L;
        }
        return Long.parseLong(userId);
    }

    @Override
    public String getTokenByUserId(Long userId) {
        return redis.boundValueOps(Constant.TOKEN_PREFIX + userId).get();
    }

    @Override
    public boolean checkToken(String token) {
        if (token == null) {
            return false;
        }
        String userId = redis.boundValueOps(token).get();
        if (userId == null) {
            return false;
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        redis.boundValueOps(token).expire(Constant.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        // 用于不让用户多处登录
        redis.boundValueOps(Constant.TOKEN_PREFIX + userId).expire(Constant.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return true;
    }

    @Override
    public void deleteToken(String token) {
        if (StringUtils.isNotBlank(token)) {
            String userId = redis.boundValueOps(token).get();
            if (userId != null) {
                redis.delete(Constant.TOKEN_PREFIX + userId);
            }
        }
        redis.delete(token);
    }
}
