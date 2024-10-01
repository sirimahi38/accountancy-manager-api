package com.ca.account.manager.common.datasource;

import com.ca.account.manager.common.datasource.master.IndexDatabase;
import com.ca.account.manager.common.datasource.master.IndexDatabaseRepository;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Configuration
public class DataSourceBasedMultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl<String> {
    private final Map<String, DataSource> TENANT_DATASOURCE_CACHE = new TreeMap<>();
    @Autowired
    private IndexDatabaseRepository indexDatabaseRepository;

    private static HikariDataSource getHikariDataSource(IndexDatabase masterTenant) {
        return DataSourceBuilder.create().driverClassName(masterTenant.getIddriver()).username(masterTenant.getIdusername())
                .password(masterTenant.getIdusername()).url(masterTenant.getIdurl()).type(HikariDataSource.class).build();
    }

    @Override
    protected DataSource selectAnyDataSource() {
        if (TENANT_DATASOURCE_CACHE.isEmpty()) {
            List<IndexDatabase> masterTenants = indexDatabaseRepository.findAll();
            for (IndexDatabase masterTenant : masterTenants) {
                TENANT_DATASOURCE_CACHE.put(masterTenant.getIdschema(), getHikariDataSource(masterTenant));
            }
        }
        return this.TENANT_DATASOURCE_CACHE.values().iterator().next();
    }

    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {

        if (!this.TENANT_DATASOURCE_CACHE.containsKey(tenantIdentifier)) {
            List<IndexDatabase> masterTenants = indexDatabaseRepository.findAll();
            for (IndexDatabase masterTenant : masterTenants) {
                if (this.TENANT_DATASOURCE_CACHE.containsKey(masterTenant.getIdschema())) {
                    continue;
                }
                TENANT_DATASOURCE_CACHE.put(masterTenant.getIdschema(), getHikariDataSource(masterTenant));
            }
        }

        if (!this.TENANT_DATASOURCE_CACHE.containsKey(tenantIdentifier)) {
            throw new UsernameNotFoundException(String.format("Tenant not found after rescan, " + " tenant=%s", tenantIdentifier));
        }
        return this.TENANT_DATASOURCE_CACHE.get(tenantIdentifier);
    }

}
