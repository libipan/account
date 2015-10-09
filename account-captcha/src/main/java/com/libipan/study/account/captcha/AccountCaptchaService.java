package com.libipan.study.account.captcha;

import java.util.List;

public interface AccountCaptchaService
{
	/**
	 * 生成随机的验证码主键
	 * @return
	 * @throws AccountCaptchaException
	 */
    String generateCaptchaKey() throws AccountCaptchaException;

    /**
     * 根据验证码主键生成验证码图片
     * @param captchaKey
     * @return
     * @throws AccountCaptchaException
     */
    byte[] generateCaptchaImage( String captchaKey ) throws AccountCaptchaException;

    /**
     * 校验验证码
     * @param captchaKey 验证码主键
     * @param captchaValue 验证码图片的值
     * @return
     * @throws AccountCaptchaException
     */
    boolean validateCaptcha( String captchaKey, String captchaValue ) throws AccountCaptchaException;

    /**
     * 获取预定义图片的内容（为了测试）
     * @return
     */
    List<String> getPreDefinedTexts();

    /**
     * 设置预定义图片的内容（为了测试）
     * @param preDefinedTexts
     */
    void setPreDefinedTexts( List<String> preDefinedTexts );
}
