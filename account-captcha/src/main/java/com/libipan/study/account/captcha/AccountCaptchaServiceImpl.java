package com.libipan.study.account.captcha;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.InitializingBean;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

public class AccountCaptchaServiceImpl implements AccountCaptchaService,InitializingBean {
	private DefaultKaptcha producer;
	private Map<String, String> captchaMap = new HashMap<String, String>();// 将验证码主键和验证码图片的值绑定的集合
	private List<String> preDefinedTexts;
	private int textCount = 0;

	/**
	 * SpringFramework初始化对象的时候调用
	 */
	public void afterPropertiesSet() throws Exception {
		producer = new DefaultKaptcha();

		producer.setConfig(new Config(new Properties()));
	}

	/**
	 * 生成随机的验证码主键。
	 * 同时将验证码图片的值与该主键绑定，放入集合中
	 */
	public String generateCaptchaKey() {
		String key = RandomGenerator.getRandomString();
		String value = getCaptchaText();
		// 放入集合
		captchaMap.put(key, value);

		return key;
	}

	/**
	 * 获取预定义图片的值
	 */
	public List<String> getPreDefinedTexts() {
		return preDefinedTexts;
	}
	
	/**
	 * 设置预定义图片的值
	 */
	public void setPreDefinedTexts(List<String> preDefinedTexts) {
		this.preDefinedTexts = preDefinedTexts;
	}

	/**
	 * 获取验证码图片的值
	 * @return
	 */
	private String getCaptchaText() {
		// 若预定义值不存在或为空时，就用验证码生成器生成一个随机的字符串；
		// 反之，就顺序地循环该字符串列表读取值。(测试用)
		if (preDefinedTexts != null && !preDefinedTexts.isEmpty()) {
			String text = preDefinedTexts.get(textCount);
			// 测试用
			textCount = (textCount + 1) % preDefinedTexts.size();

			return text;
		} else {
			return producer.createText();
		}
	}

	/**
	 * 根据验证码主键生成图片
	 */
	public byte[] generateCaptchaImage(String captchaKey) throws AccountCaptchaException {
		// 图片的值
		String text = captchaMap.get(captchaKey);
		if (text == null) {
			throw new AccountCaptchaException("Captch key '" + captchaKey + "' not found!");
		}

		BufferedImage image = producer.createImage(text);

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			ImageIO.write(image, "jpg", out);
		} catch (IOException e) {
			throw new AccountCaptchaException("Failed to write captcha stream!", e);
		}

		return out.toByteArray();
	}

	/**
	 * 校验
	 */
	public boolean validateCaptcha(String captchaKey, String captchaValue) throws AccountCaptchaException {
		// 主键对应真实的图片的值
		String text = captchaMap.get(captchaKey);
		if (text == null) {
			throw new AccountCaptchaException("Captch key '" + captchaKey + "' not found!");
		}
		
		// 如果校验成功，返回true，同时解除该主键与图片值的绑定
		if (text.equals(captchaValue)) {
			captchaMap.remove(captchaKey);

			return true;
		} else {
			return false;
		}
	}
}
