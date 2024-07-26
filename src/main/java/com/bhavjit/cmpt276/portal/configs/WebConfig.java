package com.bhavjit.cmpt276.portal.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
        // TODO: change allowed origins
            .allowedOrigins("http://localhost:5173", 
                            "http://aws-yyc-app-client.s3-website-us-west-2.amazonaws.com",
                            "https://dd06gmwf4nvya.cloudfront.net", 
                            "https://1nuu606qd3.execute-api.us-west-2.amazonaws.com/default/LambdaStack-us-west-2-regionFunction0D786950-A7tCAv9V8Mr0",
                            "cmpt276.bhavjit.com")
            .allowedMethods("*")
            .allowedHeaders("*")
            .allowCredentials(true);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/data/**")
                .addResourceLocations("classpath:/static/data/");
    }

}