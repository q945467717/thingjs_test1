package com.zl.thingjs;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@EntityScan("com.zl.model")
@ComponentScan(basePackages = {"com.zl.controller","com.zl.service","com.zl.configuration","com.zl.security","com.zl.Util"})
@MapperScan("com.zl.mapper")
@SpringBootApplication
public class ThingjsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThingjsApplication.class, args);
    }

//    @Bean
//    public ServletWebServerFactory servletContainer() {
//
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
//
//            @Override
//            protected void postProcessContext(Context context) {
//
//                SecurityConstraint securityConstraint = new SecurityConstraint();
//                securityConstraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection = new SecurityCollection();
//                collection.addPattern("/*");
//                securityConstraint.addCollection(collection);
//                context.addConstraint(securityConstraint);
//            }
//        };
//        tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
//        return tomcat;
//    }
//
//    /**
//     * 让我们的应用支持HTTP是个好想法，但是需要重定向到HTTPS，
//     * 但是不能同时在application.properties中同时配置两个connector，
//     * 所以要以编程的方式配置HTTP connector，然后重定向到HTTPS connector
//     * @return Connector
//     */
//    private Connector initiateHttpConnector() {
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setScheme("http");
//        connector.setPort(8888); // http端口
//        connector.setSecure(false);
//        connector.setRedirectPort(1029); // application.properties中配置的https端口
//        return connector;
//    }

}
