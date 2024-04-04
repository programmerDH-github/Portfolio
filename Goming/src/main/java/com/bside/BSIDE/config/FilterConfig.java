package com.bside.BSIDE.config;

import com.bside.BSIDE.filter.SameSiteFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<SameSiteFilter> sameSiteFilter() {
        FilterRegistrationBean<SameSiteFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SameSiteFilter());
        registrationBean.addUrlPatterns("/*"); // 필터가 적용될 URL 패턴 설정
        return registrationBean;
    }
}