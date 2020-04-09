package cn.myllxy.crm.vo.resp;


import lombok.Data;

/**
 * @author myllxy
 * @create 2020-03-21 15:58
 */
@Data
public class LogoutInfoVo {
    private String accessToken;
    private String refreshToken;
    private String username;

    public LogoutInfoVo() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "LogoutInfoVo{" +
                "accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
