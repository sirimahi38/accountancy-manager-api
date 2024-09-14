package com.ca.account.manager.common.repos;

import com.ca.account.manager.common.CommonConfig;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("IndexDatabase repository tests.")
@ContextConfiguration(classes = CommonConfig.class)
@Transactional("tenantTransactionManager")
public abstract class BaseTenantDatabaseRepositoryTest implements PostgreSQLContainerInitializer {

}
