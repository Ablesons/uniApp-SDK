package top.ykcloud.locationplugin.entity;

import java.io.Serializable;

public class AuthParams implements Serializable {
    // 网络货运企业APP的唯一标识
    private String appId;
    // 网络货运企业在省平台申请的接入安全码
    private String appSecurity;
    // 网络货运企业在省平台申请的企业发送代码
    private String enterpriseSenderCode;
    // 环境:“debug”接入测试环境，“release”接入正式环境。
    private String environment;

    public String getAppId() {
        return appId;
    }

    public String getAppSecurity() {
        return appSecurity;
    }

    public String getEnterpriseSenderCode() {
        return enterpriseSenderCode;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setAppSecurity(String appSecurity) {
        this.appSecurity = appSecurity;
    }

    public void setEnterpriseSenderCode(String enterpriseSenderCode) {
        this.enterpriseSenderCode = enterpriseSenderCode;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}
