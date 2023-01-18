package com.emsi.pfa.feign;

import com.emsi.pfa.security.SecurityUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class FeignRequestInterceptor implements RequestInterceptor {
    @Autowired
    HttpServletRequest request;
    @Autowired
    SecurityUtils securityUtils;
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Authorization","Bearer "+securityUtils.generateCurrentUserToken(request));
    }
}
