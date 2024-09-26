package com.ca.account.manager.common.interceptor;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver<String> {

    @Override
    public String resolveCurrentTenantIdentifier() {
        String defaultTenant = "public";
        return (TenantContext.getCurrentTenant() != null) ? TenantContext.getCurrentTenant() : defaultTenant;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}

