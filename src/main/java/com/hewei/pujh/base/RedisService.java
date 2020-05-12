package com.hewei.pujh.base;

public interface RedisService {
    /**
     * 创建一个token关联上指定用户
     *
     * @param userId 指定用户的id
     * @return 生成的token
     */
    String createToken(Long userId);

    /**
     * 根据token获取userId
     * @param token token
     * @return userId
     */
    Long getUserId(String token);

    /**
     * 获取token 这个用于不让用户多处登录
     *
     * @param userId 指定用户的id
     * @return 生成的token
     */
    String getTokenByUserId(Long userId);

    /**
     * 检查token是否有效
     *
     * @param token 令牌
     * @return 是否有效
     */
    boolean checkToken(String token);

    /**
     * 删除token
     * @param token token
     */
    void deleteToken(String token);


}
