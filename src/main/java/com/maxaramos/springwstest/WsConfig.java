package com.maxaramos.springwstest;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;

@Configuration
public class WsConfig {

//	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(context);
		messageDispatcherServlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<>(messageDispatcherServlet, "/userService/*");
	}

	@Bean(name = "users")
	public DefaultWsdl11Definition userWsdl() {
		DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
		wsdl.setPortTypeName("UserPort");
		wsdl.setLocationUri("http://localhost:8080/services/");
		wsdl.setTargetNamespace("http://springwstest.maxaramos.com/swst");
		wsdl.setSchema(userSchema());
		return wsdl;
	}

	@Bean
	public SimpleXsdSchema userSchema() {
		return new SimpleXsdSchema(new ClassPathResource("/xsd/user.xsd"));
	}

}
