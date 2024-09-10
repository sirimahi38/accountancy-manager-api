package com.ca.account.manager.common.datasource;

import com.ca.account.manager.common.repos.domain.IndexDatabase;
import com.ca.account.manager.common.repos.IndexDatabaseRepository;
import com.ca.account.manager.common.utils.DataSourceUtil;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Configuration
public class DataSourceBasedMultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {
    private static final String DEFAULT_TENANT_ID = "pulse";
    @Autowired
    private IndexDatabaseRepository indexDatabaseRepository;

    private Map<String, DataSource> dataSourcesMtApp = new TreeMap<>();

    boolean init = false;

    @Override
    protected DataSource selectAnyDataSource() {
        if (dataSourcesMtApp.isEmpty()) {
            List<IndexDatabase> masterTenants = indexDatabaseRepository.findAll();
            for (IndexDatabase masterTenant : masterTenants) {
                dataSourcesMtApp.put(masterTenant.getIdschema(),
                        DataSourceUtil.createAndConfigureDataSource(masterTenant));
            }
        }
        return this.dataSourcesMtApp.values().iterator().next();
    }

    @Override
    protected DataSource selectDataSource(Object tenantIdentifier) {
        tenantIdentifier = initializeTenantIfLost(tenantIdentifier);

        if (!this.dataSourcesMtApp.containsKey(tenantIdentifier)) {
            List<IndexDatabase> masterTenants = indexDatabaseRepository.findAll();
            for (IndexDatabase masterTenant : masterTenants) {
                if (this.dataSourcesMtApp.containsKey(masterTenant.getIdschema())) {
                    continue;
                }
                dataSourcesMtApp.put(masterTenant.getIdschema(), DataSourceUtil.createAndConfigureDataSource(masterTenant));
            }
        }

        if (!this.dataSourcesMtApp.containsKey(tenantIdentifier)) {
            throw new UsernameNotFoundException(
                    String.format(
                            "Tenant not found after rescan, "
                                    + " tenant=%s",
                            tenantIdentifier));
        }
        return this.dataSourcesMtApp.get(tenantIdentifier);
    }

    private Object initializeTenantIfLost(Object tenantIdentifier) {
        return tenantIdentifier;
    }
}
