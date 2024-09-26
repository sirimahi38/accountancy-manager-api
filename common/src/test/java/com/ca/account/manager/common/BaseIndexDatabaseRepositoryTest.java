package com.ca.account.manager.common;

import com.ca.account.manager.common.repos.IndexDatabaseRepository;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
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
@ContextConfiguration(classes = CommonAdapterTestConfig.class, initializers = BaseIndexDatabaseRepositoryTest.DataSourceInitializer.class)
@Transactional("indexTransactionManager")
public abstract class BaseIndexDatabaseRepositoryTest {

    @Container
    protected final static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.4")
            .waitingFor((Wait.defaultWaitStrategy()));
    @Autowired
    protected IndexDatabaseRepository indexDatabaseRepository;

    @BeforeAll
    static void init() {
        try (Connection conn = DriverManager.getConnection(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword());
             Statement stmt = conn.createStatement();
        ) {
            String sql = "CREATE DATABASE tenant1";
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
                    "spring.jpa.hibernate.ddl-auto=create"
            );
        }
    }


}