package com.libipan.study.account.email;

public interface AccountEmailService {

	/**
	 * 发送邮件
	 * @param to 接收地址
	 * @param subject 邮件主题
	 * @param htmlText 邮件内容
	 * @throws AccountEmailException
	 */
	public void sendMail(String to, String subject, String htmlText) throws AccountEmailException;
}
