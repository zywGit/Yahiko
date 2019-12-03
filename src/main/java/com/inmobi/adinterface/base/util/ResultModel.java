package com.inmobi.adinterface.base.util;
 
import lombok.Data;

import java.util.Map;

@Data
public class ResultModel {
 
	private int errcode;// 返回码
	private String errmsg;// 返回消息
	private Map<String, Object> data;// 数据源
}
