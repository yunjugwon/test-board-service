package com.springboot.microservices.mvp.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({ DevProfile.class, ProdProfile.class})
@Configuration
public class ProfileConfig {

}
