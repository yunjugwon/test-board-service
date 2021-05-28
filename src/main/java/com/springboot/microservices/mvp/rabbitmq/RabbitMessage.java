package com.springboot.microservices.mvp.rabbitmq;

import lombok.Data;

@Data
public class RabbitMessage<T> {
	private T t;
}
