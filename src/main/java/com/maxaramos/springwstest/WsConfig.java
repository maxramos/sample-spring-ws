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
		return new ServletRegistrationBean<>(messageDispatcherServlet, "/services/*");
	}

	@Bean(name = "users")
	public DefaultWsdl11Definition usersWsdl() {
		DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
		wsdl.setPortTypeName("UserService");
//		wsdl.setLocationUri("/services/");
		wsdl.setSchema(userXsd());
		return wsdl;
	}

	@Bean
	public SimpleXsdSchema userXsd() {
		return new SimpleXsdSchema(new ClassPathResource("/wsdl/user.xsd"));
	}

}
