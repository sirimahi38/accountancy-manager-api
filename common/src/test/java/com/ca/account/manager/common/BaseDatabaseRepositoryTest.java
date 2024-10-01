package com.ca.account.manager.common;

import com.ca.account.manager.common.datasource.master.IndexDatabaseRepository;
import com.ca.account.manager.common.repository.event.WorkflowEventLogRepository;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.flywaydb.core.Flyway;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("IndexDatabase repository tests.")
@ActiveProfiles("test")
@Testcontainers
@ContextConfiguration(classes = CommonAdapterTestConfig.class, initializers = BaseDatabaseRepositoryTest.DataSourceInitializer.class)
@Transactional("indexTransactionManager")
public abstract class BaseDatabaseRepositoryTest {

    @Container
    protected final static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.4")
            .waitingFor((Wait.defaultWaitStrategy()));

    private final static String TENANT_DB_SCRIPTS = "db/tenant";

    private static String tenantDatabase = "pulsewsc5a2fefc168142378135b3eb62366e6a";
    private static String tenant = "66a78c5f4e062979ccd04f83";
    @Autowired
    protected IndexDatabaseRepository indexDatabaseRepository;

    @Autowired
    protected WorkflowEventLogRepository workflowEventLogRepository;

    protected static void setupTenantDatabases() {
        try (Connection conn = DriverManager.getConnection(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword());
             Statement stmt = conn.createStatement()
        ) {
            String sql = String.format("CREATE DATABASE %s", String.format("\"%s\"", tenantDatabase));

            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static class DataSourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(@NotNull ConfigurableApplicationContext applicationContext) {
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                    applicationContext,
                    "spring.test.database.replace=none",
                    "spring.datasource.url=" + postgres.getJdbcUrl(),
                    "spring.datasource.username=" + postgres.getUsername(),
                    "spring.datasource.password=" + postgres.getPassword(),
                    "spring.datasource.driverClassName=org.postgresql.Driver",
                    "flyway.locations=classpath:db/migration",
                    "spring.jpa.hibernate.ddl-auto=create"
            );
            setupTenantDatabases();
        }
    }

    @PostConstruct
    public void createAndPopulateTenantDatabase() {
        String newURL = StringUtils.replace(postgres.getJdbcUrl(), "test", tenantDatabase);
        Flyway.configure().locations(TENANT_DB_SCRIPTS).schemas(tenant).dataSource(DataSourceBuilder.create().url(newURL).password(postgres.getPassword()).username(postgres.getUsername()).build()).load().migrate();
    }
}
