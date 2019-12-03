package com.inmobi.adinterface.web.controller;

import com.inmobi.adinterface.base.util.ResultModel;
import com.inmobi.adinterface.base.util.ResultTools;
import com.inmobi.adinterface.base.util.ServletUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.inmobi.adinterface.base.util.EncryptPassword.encryptPassword;

/**
 * @program: authority
 * @description: 登陆控制层
 * @author: Mr.Yan
 * @create: 2018-11-14 09:39
 **/
@RestController
public class LoginController {
	@Autowired
	SQLManager sqlManager;
    // 密码加密方式
    @Value("${shiro.user.hashAlgorithmName}")
    private String hashAlgorithmName;

    // 密码加密次数
    @Value("${shiro.user.hashIterations}")
    private Integer hashIterations;


	@GetMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 如果是Ajax请求，返回Json字符串。
		if (ServletUtils.isAjaxRequest(request)) {
			return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
		}
		return "login";
	}

	@PostMapping("/login")
	@ResponseBody
	public ResultModel ajaxLogin(String username, String password) {

		return ResultTools.result(0, "", null);

	}
}
