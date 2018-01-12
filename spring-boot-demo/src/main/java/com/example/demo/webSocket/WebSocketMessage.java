package com.example.demo.webSocket;

import com.alibaba.fastjson.JSON;

public class WebSocketMessage {
	
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
