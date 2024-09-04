package com.ca.account.manager.common.datasource;

import com.ca.account.manager.common.domain.IndexDatabase;
import com.ca.account.manager.common.repos.IndexDatabaseRepository;
import com.zaxxer.hikari.hibernate.HikariConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.AbstractMultiTenantConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class TenantConnectionProvider extends AbstractMultiTenantConnectionProvider {

    private final ConcurrentHashMap<String, ConnectionProvider> connectionProviderMap;
    private DataSource dataSource;

    private final IndexDatabaseRepository indexDatabaseRepository;

    public TenantConnectionProvider(IndexDatabaseRepository indexDatabaseRepository) {
        this.connectionProviderMap = new ConcurrentHashMap<>();
        this.indexDatabaseRepository = indexDatabaseRepository;
    }


    @Override
    protected ConnectionProvider getAnyConnectionProvider() {
        return null;
    }

    @Override
    protected ConnectionProvider selectConnectionProvider(Object tenantIdentifier) {
        return connectionProviderMap.get(tenantIdentifier);
    }

    @Override
    public Connection getAnyConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public Connection getConnection(Object tenantIdentifier) throws SQLException {
        assert (tenantIdentifier != null) : "The tenant Identifier can't be null";
        Connection connection = selectConnectionProvider(tenantIdentifier).getConnection();
        return (Connection) Objects.requireNonNullElseGet(connection, () -> indexDatabaseRepository.findById((String) tenantIdentifier)); // need to correct
    }

    @Override
    public void releaseConnection(Object tenantIdentifier, Connection connection) throws SQLException {
        connection.createStatement().execute(String.format("SET SCHEMA \"%s\";", tenantIdentifier));
        releaseAnyConnection(connection);
    }

    /**
     * Initialise a database
     */
    synchronized private void initializeDatabase(final String tenantIdentifier, Map properties) {
     //   indexDatabaseRepository.findAll().stream().map(indexDatabase -> indexDatabase.getIdurl()).collect(Collectors.toMap(config ->config.getKey ));
        HikariConnectionProvider hikariConnectionProvider = new HikariConnectionProvider();
        hikariConnectionProvider.configure(properties);
        connectionProviderMap.put(tenantIdentifier, hikariConnectionProvider);
    }

}
