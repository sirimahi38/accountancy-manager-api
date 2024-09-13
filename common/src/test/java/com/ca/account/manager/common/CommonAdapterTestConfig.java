package com.ca.account.manager.common;

import com.ca.account.manager.common.CommonConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CommonConfig.class})
public class CommonAdapterTestConfig {
}
