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
public class WebServiceConfig {

//	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(applicationContext);
		messageDispatcherServlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<>(messageDispatcherServlet, "/services/*");
	}

//	@Bean(name = "UserService")
	public DefaultWsdl11Definition userWsdlDefinition(@Qualifier("user") XsdSchema userXsdSchema) {
		DefaultWsdl11Definition wsdlDefinition = new DefaultWsdl11Definition();
		wsdlDefinition.setPortTypeName("UserService");
		wsdlDefinition.setTargetNamespace("http://springwstest.maxaramos.com/user");
		wsdlDefinition.setLocationUri("/services");
		wsdlDefinition.setSchema(userXsdSchema);
		return wsdlDefinition;
	}

//	@Bean(name = "AddressService")
	public DefaultWsdl11Definition addressWsdlDefinition(@Qualifier("address") XsdSchema addressXsdSchema) {
		DefaultWsdl11Definition wsdlDefinition = new DefaultWsdl11Definition();
		wsdlDefinition.setPortTypeName("AddressService");
		wsdlDefinition.setTargetNamespace("http://springwstest.maxaramos.com/address");
		wsdlDefinition.setLocationUri("/services");
		wsdlDefinition.setSchema(addressXsdSchema);
		return wsdlDefinition;
	}

//	@Bean(name = "user")
	public XsdSchema userXsdSchema() {
		return new SimpleXsdSchema(new ClassPathResource("/xsd/user.xsd"));
	}

//	@Bean(name = "address")
	public XsdSchema addressXsdSchema() {
		return new SimpleXsdSchema(new ClassPathResource("/xsd/address.xsd"));
	}

}
