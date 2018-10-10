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

	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(context);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<>(servlet, "/user/*");
	}

	@Bean(name = "users")
	public DefaultWsdl11Definition usersWsdl() {
		DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
		wsdl.setPortTypeName("UserService");
		wsdl.setTargetNamespace("http://springwstest.maxaramos.com/user/definition");
		wsdl.setLocationUri("/user");
		wsdl.setSchema(userXsd());
		return wsdl;
	}

	@Bean
	public SimpleXsdSchema userXsd() {
		return new SimpleXsdSchema(new ClassPathResource("/wsdl/user.xsd"));
	}

}
