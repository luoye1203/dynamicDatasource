package com.xzst.modi.app.hConfig;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "frontend-config")
public class FrontendConfigPorperties {
    private  String toShuMiaoUrl;

    public String getToShuMiaoUrl() {
        return toShuMiaoUrl;
    }

    public void setToShuMiaoUrl(String toShuMiaoUrl) {
        this.toShuMiaoUrl = toShuMiaoUrl;
    }
}
