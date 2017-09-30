package com.tw.api;

import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class DozerConfig {

    @Bean
    public DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean() throws Exception {
        DozerBeanMapperFactoryBean factoryBean = new DozerBeanMapperFactoryBean();
        Resource[] resources = new Resource[]{new ClassPathResource("dozer-mappings.xml")};
        factoryBean.setMappingFiles(resources);
        return factoryBean;
    }
}
