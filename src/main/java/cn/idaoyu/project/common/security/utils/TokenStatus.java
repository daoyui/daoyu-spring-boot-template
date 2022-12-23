package cn.idaoyu.project.common.security.utils;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author 一条秋刀鱼zz
 * @className TokenStatus
 * @description 用户 token 状态
 * @date 2022/12/24 1:34
 */
@Data
@Component
public class TokenStatus {

    public final ThreadLocal<TokenStatus> threadLocal = new ThreadLocal<>();

    /**
     * 用户 token 是否有效
     */
    private Boolean valid;

    /**
     * 用户 token 是否过期
     */
    private Boolean expire;

    /**
     * 获取 token 状态
     *
     * @return TokenStatus
     */
    public TokenStatus getTokenStatus() {
        TokenStatus tokenStatus = threadLocal.get();
        threadLocal.remove();
        return tokenStatus;
    }

    /**
     * 设置 token 状态
     *
     * @param isValid  是否有效
     * @param isExpire 是否过期
     */
    public void setTokenStatus(Boolean isValid, Boolean isExpire) {
        threadLocal.set(new TokenStatus(isValid, isExpire));
    }

    private TokenStatus() {
    }

    private TokenStatus(Boolean valid, Boolean expire) {
        this.valid = valid;
        this.expire = expire;
    }
}
