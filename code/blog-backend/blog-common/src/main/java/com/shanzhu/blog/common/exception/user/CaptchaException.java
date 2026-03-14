package com.shanzhu.blog.common.exception.user;

/**
 * 验证码错误异常类
 *
 * @author: ShanZhu
 * @date: 2023-12-09yi
 */
public class CaptchaException extends UserException
{
    private static final long serialVersionUID = 1L;

    public CaptchaException()
    {
        super("user.jcaptcha.error", null);
    }
}
