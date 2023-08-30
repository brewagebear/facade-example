package io.github.brewagebear.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "encryptor")
public class EncryptorConfig {
    private String secret;

    private CharSequence salt;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public CharSequence getSalt() {
        return salt;
    }

    public void setSalt(CharSequence salt) {
        this.salt = salt;
    }
}
