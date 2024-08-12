package io.github.lucklike.luckyclient.api.util.token;

import javax.annotation.PostConstruct;

public abstract class TokenManager<T> {

    private T token;

    /**
     * 获取Token
     *
     * @return Token
     */
    public synchronized T getToken() {
        if (token == null) {
            token = loadToken();
        }
        return token;
    }

    /**
     * 加载Token数据
     *
     * @return Token数据
     */
    protected abstract T loadToken();

    /**
     * 保存Token数据
     *
     * @param token Token数据
     */
    protected abstract void saveToken(T token);

    /**
     * 检查Token是否已经过期
     *
     * @param token Token数据
     * @return 是否已经过期
     */
    protected abstract boolean isExpires(T token);

    /**
     * 获取Token对象的类型
     *
     * @return Token对象的类型
     */
    protected abstract Class<T> getTokenClass();

}
