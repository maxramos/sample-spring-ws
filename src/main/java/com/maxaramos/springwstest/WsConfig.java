package com.maxaramos.springwstest;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;

@Configuration
public class WsConfig {

//	@Bean(name = "users")
	public DefaultWsdl11Definition userWsdl() {
		DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
		wsdl.setPortTypeName("UserPort");
		wsdl.setLocationUri("/userService/");
		wsdl.setTargetNamespace("http://springwstest.maxaramos.com/swst");
		wsdl.setSchema(userSchema());
		return wsdl;
	}

//	@Bean
	public SimpleXsdSchema userSchema() {
		return new SimpleXsdSchema(new ClassPathResource("/xsd/user.xsd"));
	}

}
