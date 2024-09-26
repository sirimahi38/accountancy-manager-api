package com.ca.account.manager.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class RequestInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object object) throws Exception {
        LOGGER.info("In preHandle we are Intercepting the Request");
        LOGGER.info("____________________________________________");
        String requestURI = request.getRequestURI();
        String tenantID = request.getHeader("X-TenantID");
        LOGGER.info("RequestURI::" + requestURI + " || Search for X-TenantID  :: " + tenantID);
        LOGGER.info("____________________________________________");
        if (tenantID == null) {
            response.getWriter().write("X-TenantID not present in the Request Header");
            response.setStatus(400);
            return false;
        }
        TenantContext.setCurrentTenant(tenantID);
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        TenantContext.clear();
    }

}
