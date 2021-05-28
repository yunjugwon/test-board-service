package com.springboot.microservices.mvp.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile(value="prod")
@PropertySource({"classpath:profiles/prod/application.yml"})
public class ProdProfile {

}
