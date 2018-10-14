package com.maxaramos.springwstest;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

//@EnableWs
@Configuration
public class WsConfig extends WsConfigurerAdapter {

	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors) {
		interceptors.add(securityInterceptor());
	}

	@Bean
	public Wss4jSecurityInterceptor securityInterceptor() {
		Wss4jSecurityInterceptor securityInterceptor = new Wss4jSecurityInterceptor();
		securityInterceptor.setValidationActions("UsernameToken");
		securityInterceptor.setValidationCallbackHandler(passwordValidationCallbackHandler());
		return securityInterceptor;
	}

	@Bean
    public SimplePasswordValidationCallbackHandler passwordValidationCallbackHandler(){
		Properties users = new Properties();
		users.setProperty("mramos", "changeit");
        SimplePasswordValidationCallbackHandler callbackHandler = new SimplePasswordValidationCallbackHandler();
        callbackHandler.setUsers(users);
        return callbackHandler;
    }

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
