package com.springboot.microservices.mvp.model;

import lombok.Data;

@Data
public class RabbitMsg {
	private String msg;
	private String id;
}
