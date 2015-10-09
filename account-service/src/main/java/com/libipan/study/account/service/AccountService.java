package com.libipan.study.account.service;

public interface AccountService {
	/**
	 * 生成验证码的唯一标识符
	 * @return
	 * @throws AccountServiceException
	 */
	String generateCaptchaKey() throws AccountServiceException;

	/**
	 * 根据标识符生成图片
	 * @param captchaKey
	 * @return
	 * @throws AccountServiceException
	 */
	byte[] generateCaptchaImage(String captchaKey) throws AccountServiceException;

	/**
	 * 注册
	 * @param signUpRequest
	 * @throws AccountServiceException
	 */
	void signUp(SignUpRequest signUpRequest) throws AccountServiceException;

	/**
	 * 激活
	 * @param activationNumber
	 * @throws AccountServiceException
	 */
	void activate(String activationNumber) throws AccountServiceException;

	/**
	 * 登录
	 * @param id
	 * @param password
	 * @throws AccountServiceException
	 */
	void login(String id, String password) throws AccountServiceException;
}
