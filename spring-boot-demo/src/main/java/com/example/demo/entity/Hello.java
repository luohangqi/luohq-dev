package com.example.demo.entity;

import com.alibaba.fastjson.JSON;

public class Hello {

	private String name;

	private String from;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
