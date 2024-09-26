package com.ca.account.manager.common;

import org.mockito.Spy;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CommonConfig.class})
public class CommonAdapterTestConfig {

    @Spy
    private DataSourceProperties dataSourceProperties;


}
