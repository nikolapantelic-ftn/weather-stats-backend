package com.github.nikolapantelicftn.weatherstatsbackend.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

@Component
@Order(1)
public class ApplicationRequestLoggingFilter extends AbstractRequestLoggingFilter {

    private static final Logger log = LoggerFactory.getLogger(ApplicationRequestLoggingFilter.class);

    public ApplicationRequestLoggingFilter() {
        super.setIncludeHeaders(true);
        super.setIncludeQueryString(true);
    }

    @Override
    protected void beforeRequest(HttpServletRequest httpServletRequest, String s) {
        log.info(s);
    }

    @Override
    protected void afterRequest(HttpServletRequest httpServletRequest, String s) {
        log.info(s);
    }

}
