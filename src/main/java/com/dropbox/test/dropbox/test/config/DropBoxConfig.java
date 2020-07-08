package com.dropbox.test.dropbox.test.config;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DropBoxConfig {

    @Value("${app.drop.box.token}")
    private String token;

    @Bean
    public DbxClientV2 getDbxClientV2() {
        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial");
        return new DbxClientV2(config, token);
    }
}
