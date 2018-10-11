package com.maxaramos.springwstest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

//@EnableWs
//@Configuration
public class WsConfig {

//	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(context);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<>(servlet, "/services/*");
	}

//	@Bean(name = "UserService")
	public DefaultWsdl11Definition usersWsdl(@Qualifier("user") XsdSchema userXsd) {
		DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
		wsdl.setPortTypeName("UserService");
		wsdl.setTargetNamespace("http://springwstest.maxaramos.com/user/definition");
		wsdl.setLocationUri("/user");
		wsdl.setSchema(userXsd);
		return wsdl;
	}

//	@Bean(name = "AddressService")
	public DefaultWsdl11Definition addressesWsdl(@Qualifier("address") XsdSchema addressXsd) {
		DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
		wsdl.setPortTypeName("AddressService");
		wsdl.setTargetNamespace("http://springwstest.maxaramos.com/address/definition");
		wsdl.setLocationUri("/address");
		wsdl.setSchema(addressXsd);
		return wsdl;
	}

//	@Bean(name = "user")
	public SimpleXsdSchema userXsd() {
		return new SimpleXsdSchema(new ClassPathResource("/wsdl/user.xsd"));
	}

//	@Bean(name = "address")
	public SimpleXsdSchema addressXsd() {
		return new SimpleXsdSchema(new ClassPathResource("/wsdl/address.xsd"));
	}

}
