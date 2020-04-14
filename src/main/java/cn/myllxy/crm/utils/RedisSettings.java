package cn.myllxy.crm.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author myllxy
 * @create 2020-04-12 21:29
 */
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "redis")
@Data
public class RedisSettings {
    private String host;
    private int port;
    private long timeout;
    private int maxidle;
    private int maxtotal;
    private long maxwaitmillis;
    private boolean testonborrow;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMaxidle() {
        return maxidle;
    }

    public void setMaxidle(int maxidle) {
        this.maxidle = maxidle;
    }

    public int getMaxtotal() {
        return maxtotal;
    }

    public void setMaxtotal(int maxtotal) {
        this.maxtotal = maxtotal;
    }

    public long getMaxwaitmillis() {
        return maxwaitmillis;
    }

    public void setMaxwaitmillis(long maxwaitmillis) {
        this.maxwaitmillis = maxwaitmillis;
    }

    public boolean isTestonborrow() {
        return testonborrow;
    }

    public void setTestonborrow(boolean testonborrow) {
        this.testonborrow = testonborrow;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public RedisSettings() {
    }
}
