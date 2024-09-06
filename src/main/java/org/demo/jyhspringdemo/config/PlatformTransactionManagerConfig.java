package org.demo.jyhspringdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

/**
 * @author goufj
 * @date 2024-08-24
 */
//@Configuration
public class PlatformTransactionManagerConfig {

    @Bean
    public PlatformTransactionManager platformTransactionManager(){
        return new JtaTransactionManager();
    }
}
