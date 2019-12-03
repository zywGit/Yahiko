package com.inmobi.adinterface.base.propertor;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取项目相关配置
 *
 * @author ruoyi
 */
@Component
@ConfigurationProperties(prefix = "authority")
public class AuthorityConfig {

	/**
	 * 项目名称
	 */
	private String name;

	/**
	 * 版本
	 */
	private String version;

	/**
	 * 版权年份
	 */
	private String copyrightYear;

	/**
	 * 获取地址开关
	 */
	private static boolean addressEnabled;

	/**
	 * 获取自定义账号
	 **/
	private String userName;

	/**
	 * 获取自定义密码
	 **/
	private String passWord;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCopyrightYear() {
		return copyrightYear;
	}

	public void setCopyrightYear(String copyrightYear) {
		this.copyrightYear = copyrightYear;
	}

	public static boolean isAddressEnabled() {
		return addressEnabled;
	}

	public void setAddressEnabled(boolean addressEnabled) {
		AuthorityConfig.addressEnabled = addressEnabled;
	}

}
